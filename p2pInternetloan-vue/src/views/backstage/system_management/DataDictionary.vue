<template>
  <div  style="padding: 20px;">
    <el-form class="demo-form-inline" style="margin-top: 10px;background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
      <el-form-item label="字典名称">
        <el-input v-model="queryParams.title" placeholder="请输入字典名称">
        </el-input>
      </el-form-item>
      <el-form-item label="字典编号">
        <el-input v-model="queryParams.sn" placeholder="请输入字典编号">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增字典数据</el-button>
      </el-form-item>
    </el-form>

    <!-- 数据表格-->
    <el-table :data="dictList"  height="440" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column  prop="id" label="#" min-width="1">
      </el-table-column>
      <el-table-column prop="title" label="菜单名称" min-width="3">
      </el-table-column>
      <el-table-column prop="sn" label="字典编号" min-width="3">
      </el-table-column>
      <el-table-column prop="description" label="描述" min-width="3">
      </el-table-column>
      <el-table-column label="操作" min-width="3">
        <template slot-scope="scope">
          <el-button @click="" type="text" size="small"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="showSysdictItem(scope.$index, scope.row)">字典配置</el-button>
          <el-button type="text" size="small"  @click="del(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                   :total="queryParams.total">
    </el-pagination>


    <!--这是添加和修改弹出-->
    <el-dialog top="25vh" width="45%" :title="dictDialogTitle" :visible.visible="dictDialogFormVisible" @close="doCancel">
      <el-form :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-form-item label="字典名称" prop="title" :label-width="formLabelWidth">
          <el-input v-model="dictForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="字段编码" prop="sn" :label-width="formLabelWidth">
          <el-input v-model="dictForm.sn" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description" :label-width="formLabelWidth">
          <el-input v-model="dictForm.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel">关闭</el-button>
        <el-button type="primary" @click="doSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!--这是字典配置的抽屉-->
    <el-drawer
      title="字典列表"
      :visible.sync="drawer"
      :direction="rtl"
      custom-class="sys-dict-item-drawer"
      size="50%"
      :before-close="handleCloseDrawer">
      <hr>
      <div style="margin: 10px;">
        <!-- 搜索维度 start -->
        <el-form class="demo-form-inline" style="margin-top: 10px;background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
          <el-form-item label="名称" size="30">
            <el-input v-model="dictItemQueryParams.title" placeholder="请输入名称">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onQueryDictItem">查询</el-button>
            <el-button type="primary" @click="handleAddDictItem">添加</el-button>
          </el-form-item>
        </el-form>
        <!-- 搜索维度 end -->

        <!--数据表格 start-->
        <el-table :data="dictItemList"  height="500"  :fit="true" :show-header="true" v-loading="dictItemloading" style="margin-top: 20px">
          <el-table-column prop="title" label="名称" min-width="3">
          </el-table-column>
          <el-table-column prop="value" label="数据值" min-width="3">
          </el-table-column>
          <el-table-column label="操作" min-width="3">
            <template slot-scope="scope">
              <el-button @click="" type="text" size="small"  @click="handleEditDictItem(scope.$index, scope.row)">编辑</el-button>
              <el-button type="text" size="small"  @click="delDictItem(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination style="margin: 15px;" background @size-change="handleSizeChangeDictItem" @current-change="handleCurrentChangeDictItem"
                       :current-page="dictItemQueryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="dictItemQueryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                       :total="dictItemQueryParams.total">
        </el-pagination>

        <!--数据表格 end-->
      </div>
    </el-drawer>


    <!--这是字典项添加和修改弹出-->
    <el-dialog top="20vh" width="50%" :title="dictItemDialogTitle" :visible="dictItemDialogFormVisible" @close="doCancelDictItem">
      <el-form :model="dictItemForm" :rules="dictItemRules" ref="dictItemForm">
        <el-form-item label="名称" prop="title" :label-width="formLabelWidthDictItem">
          <el-input v-model="dictItemForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据值" prop="value" :label-width="formLabelWidthDictItem">
          <el-input v-model="dictItemForm.value" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description" :label-width="formLabelWidthDictItem">
          <el-input v-model="dictItemForm.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="排序值" prop="sequence" :label-width="formLabelWidthDictItem">
          <el-input-number v-model="dictItemForm.sequence" controls-position="right" :min="1" :max="10"></el-input-number>&nbsp;&nbsp;&nbsp;值越小越靠前
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancelDictItem">关闭</el-button>
        <el-button type="primary" @click="doSubmitDictItem">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

    export default {
        name: "DataDictionary",
        data:function(){
            return {
                //这是查询阐述
                queryParams: {
                    //定义搜索维度
                    title: null,
                    sn: null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是表格是否占时加载动画
                loading:false,
                //这书数据对象（用于表格占时）
                dictList:[],
                //这是新值修改弹出
                dictDialogTitle:null,
                //这是新值和修改弹出是否占时
                dictDialogFormVisible: false,
                //这是文本框中文字说明的长度
                formLabelWidth: "100px",
                //弹出 form 数据动态绑定
                dictForm:{
                    id:null,
                    sn:null,
                    title:null,
                    description:null
                },
                //表单验证
                dictRules:{
                  sn:[{
                    required: true,
                    message: '请输入字典编码',
                    trigger: 'blur'
                  }],
                  title:[{
                    required: true,
                    message: '请输入字典名称',
                    trigger: 'blur'
                  }]

                },
                /////////这是字典项操作的所有属性 都在此处了//////////////
                //填充是否显示
                drawer: false,
                //从那边弹出
                direction: 'rtl',
                //这是查询参数分装
                dictItemQueryParams:{
                    parentid:null,
                    title:null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是表格是否占时加载动画
                dictItemLoading:false,
                //这书数据对象（用于表格占时）
                dictItemList:[],
                //这是新值修改弹出
                dictItemDialogTitle:null,
                //这是文本框中文字说明的长度
                formLabelWidthDictItem: "100px",
                //这是新值和修改弹出是否占时
                dictItemDialogFormVisible: false,
                //弹出 form 数据动态绑定
                dictItemForm:{
                    id:null,
                    parentid:null,
                    title:null,
                    value:null,
                    sequence:null,
                    description:null
                },
                //表单验证
                dictItemRules:{
                    title:[{
                        required: true,
                        message: '请输入名称',
                        trigger: 'blur'
                    }],
                    value:[{
                        required: true,
                        message: '请输入数据值',
                        trigger: 'blur'
                    }]
                }
            }
        },
        methods:{
            onQuery() {
                this.queryParams.page = 1;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.SYSTEM_DICT_QUERYPAGER;
                let params = this.queryParams;
                //查询动画
                this.loading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.dictList = response.data.data
                    this.queryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.loading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //添加前期调用方法，打开太弹出
            handleAdd: function() {
                this.dictDialogTitle = "添加";
                this.dictDialogFormVisible = true;
            },
            //修改前期调用方法，给表单回显对应数据 并打开弹出
            handleEdit: function(id, row) {
                this.dictDialogTitle = "修改";
                this.dictForm.id = row.id;
                this.dictForm.sn = row.sn;
                this.dictForm.title = row.title;
                this.dictForm.description = row.description;
                this.dictDialogFormVisible = true;
            },
            //一页的数量发送变化的时候调用此方法
            handleSizeChange: function(rwos) {
                this.queryParams.page = 1;
                this.queryParams.rows = rwos;
                this.search();
            },
            //当前页面发送变化的时候调用
            handleCurrentChange: function(page) {
                this.queryParams.page = page;
                this.search();
            },
            //这是提交就调用的方法【添加、修改通用】
            doSubmit: function() {
                //this.$refs['articleForm'].validate就是表单验证是否通过，
                //valid为true即为通过
                this.$refs['dictForm'].validate((valid) => {
                    if (valid) {

                        var url = null;
                        //判断时添加还是修改
                        if (null != this.dictList.id) {
                            url = this.axios.urls.SYSTEM_DICT_UPDATE;
                        } else {
                            url = this.axios.urls.SYSTEM_DICT_ADD;
                        }
                        //发送请求
                        this.axios.post(url, this.dictForm).then(response => {
                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                });
                            } else {
                                //这里是操作成功
                                this.doClearForm();
                                this.dictDialogFormVisible = false;
                                //重新查找
                                this.search();
                                //打印成功信息
                                this.$message({
                                    message: response.data.msg,
                                    type: 'success'
                                });
                            }

                        }).catch(function(error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //这是删除的方法
            del:function(id, row){
                this.$confirm('你确定要哦删除吗?', '警告', {
                    //定义两个按钮 确定和取消
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除
                    var url = this.axios.urls.SYSTEM_DICT_DEl + row.id;
                    this.axios.post(url, {}).then(response => {
                        if (response.data.code == 500) {
                            this.$message({
                                message: response.data.msg,
                                type: 'warning'
                            });
                        } else {
                            this.$message({
                                message: response.data.msg,
                                type: 'success'
                            });
                            this.search();
                        }

                    }).catch(function(error) {
                        console.log(error);
                    });
                }).catch(() => {});
            },
            //清空表单是数据
            doClearForm: function() {
                this.dictForm.id = null;
                this.dictForm.sn = null;
                this.dictForm.title = null;
                this.dictForm.description = null;
                //清空表单验证
                this.$refs['dictForm'].resetFields();
            },
            //清空表单数据，并且关闭弹出
            doCancel: function() {
                this.doClearForm();
                //关闭弹出
                this.dictDialogFormVisible = false;
            },
            /////////这是字典项操作的所有方法  都在此处了//////////////
            showSysdictItem: function(id, row){
                this.drawer = true;
                this.dictItemQueryParams.parentid = row.id;
                this.onQueryDictItem();
            },
            onQueryDictItem() {
                this.dictItemQueryParams.page = 1;
                this.searchDictItem();
            },
            //这是搜索的方法
            searchDictItem: function() {
                let url = this.axios.urls.SYSTEM_DICT_ITEM_QUERYPAGER;
                let params = this.dictItemQueryParams;
                //查询动画
                this.dictItemLoading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.dictItemList = response.data.data
                    this.dictItemQueryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.dictItemLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //添加前期调用方法，打开太弹出
            handleAddDictItem: function() {
                this.dictItemDialogTitle = "添加";
                this.dictItemDialogFormVisible = true;
                this.doClearFormDictItem();
            },
            //修改前期调用方法，给表单回显对应数据 并打开弹出
            handleEditDictItem: function(id, row) {
                this.dictItemDialogTitle = "修改";
                this.dictItemForm.id = row.id;
                this.dictItemForm.parentid = row.parentid;
                this.dictItemForm.title = row.title;
                this.dictItemForm.value = row.value;
                this.dictItemForm.sequence = row.sequence;
                this.dictItemForm.description = row.description;
                this.dictItemDialogFormVisible = true;
            },
            //一页的数量发送变化的时候调用此方法
            handleSizeChangeDictItem: function(rwos) {
                this.dictItemQueryParams.page = 1;
                this.dictItemQueryParams.rows = rwos;
                this.searchDictItem();
            },
            //当前页面发送变化的时候调用
            handleCurrentChangeDictItem: function(page) {
                this.dictItemQueryParams.page = page;
                this.searchDictItem();
            },
            //这是提交就调用的方法【添加、修改通用】  --------------------
            doSubmitDictItem: function() {
                //this.$refs['articleForm'].validate就是表单验证是否通过，
                //valid为true即为通过
                this.$refs['dictItemForm'].validate((valid) => {
                    if (valid) {
                        var url = null;
                        //判断时添加还是修改
                        if (null != this.dictItemForm.id) {
                            url = this.axios.urls.SYSTEM_DICT_ITEM_UPDATE;
                        } else {
                            url = this.axios.urls.SYSTEM_DICT_ITEM_ADD;
                        }
                        this.dictItemForm.parentid = this.dictItemQueryParams.parentid;

                        //发送请求
                        this.axios.post(url, this.dictItemForm).then(response => {
                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                });
                            } else {
                                //这里是操作成功
                                this.doCancelDictItem();
                                this.dictDialogFormVisible = false;
                                //重新查找
                                this.searchDictItem()
                                //打印成功信息
                                this.$message({
                                    message: response.data.msg,
                                    type: 'success'
                                });
                            }

                        }).catch(function(error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //这是删除的方法
            delDictItem:function(id, row){
                this.$confirm('你确定要哦删除吗?', '警告', {
                    //定义两个按钮 确定和取消
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除
                    var url = this.axios.urls.SYSTEM_DICT_ITEM_DEl + row.id;
                    this.axios.post(url, {}).then(response => {
                        if (response.data.code == 500) {
                            this.$message({
                                message: response.data.msg,
                                type: 'warning'
                            });
                        } else {
                            this.$message({
                                message: response.data.msg,
                                type: 'success'
                            });
                            this.onQueryDictItem();
                        }

                    }).catch(function(error) {
                        console.log(error);
                    });
                }).catch(() => {});
            },
            //清空表单是数据
            doClearFormDictItem: function() {
                //清空表单验证
                this.$refs['dictItemForm'].resetFields();
                this.dictItemForm.id = null;
                this.dictItemForm.title = null;
                this.dictItemForm.value = null;
                this.dictItemForm.sequence = null;
                this.dictItemForm.description = null;
            },
            //清空表单数据，并且关闭弹出
            doCancelDictItem: function() {
                this.doClearFormDictItem();
                //关闭弹出
                this.dictItemDialogFormVisible = false;
            },
            //这是当抽屉要关闭了
            handleCloseDrawer:function(done){
                this.dictItemQueryParams.title = null;
                this.dictItemQueryParams.page = 1;
                done();
            }
        },
        created() {
            this.onQuery();
        }
    }
</script>

<style scoped>
  .sys-dict-item-drawer{
    width: 900px;
  }
</style>
