<template>
  <el-main  style="background-color: #FFFFFF;" >
    <el-form class="demo-form-inline" style="margin-top: 10px;background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
      <el-form-item label="状态">
        <el-select v-model="queryParams.state" filterable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
             >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间">
        <el-input v-model="queryParams.applyTime" placeholder="请输入时间">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
      </el-form-item>
    </el-form>

    <!-- 数据表格-->
    <el-table :data="dataList"  height="440" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column  prop="id" label="ID" min-width="1">
      </el-table-column>
      <el-table-column prop="state" label="状态" min-width="3">
      </el-table-column>
      <el-table-column prop="auditTime" label="审核时间" min-width="3">
      </el-table-column>
      <el-table-column prop="applyTime" label="申请时间" min-width="3">
      </el-table-column>
      <el-table-column prop="auditorId" label="审核人" min-width="3">
      </el-table-column>
      <el-table-column prop="membersId" label="申请人" min-width="3">
      </el-table-column>
      <el-table-column prop="score" label="材料得分" min-width="3">
      </el-table-column>
      <el-table-column prop="file" label="审核人id" min-width="3">
      </el-table-column>
      <el-table-column label="操作" min-width="3">
        <template slot-scope="scope">
          <el-button @click="" type="text" size="small" @click="certified(scope.$index, scope.row)" v-if=" scope.row.state == 1">认证</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                   :total="queryParams.total">
    </el-pagination>



    <!--这是 添加 修改 弹出 -->
    <el-dialog top="20vh" width="50%" :title="dialogTitle" :visible="dialogFormVisible" @close="doCancel">
      <el-form :model="tableForm" :rules="rules" ref="tableForm">
        <el-form-item label="用户名" prop="membersId" :label-width="formLabelWidth" disabled="false">
          <el-input v-model="tableForm.membersId" autocomplete="off" disabled="false"></el-input>
        </el-form-item>
        <el-form-item label="材料得分" prop="score" :label-width="formLabelWidth">
          <el-input v-model="tableForm.score" autocomplete="off" disabled="false"></el-input>
        </el-form-item>
        <el-form-item label="文件路径" prop="file" :label-width="formLabelWidth">
          <el-input v-model="tableForm.file" autocomplete="off" disabled="false"></el-input>
        </el-form-item>
        <el-form-item label="资料图片" :label-width="formLabelWidth">
          <div class="demo-image__preview">
            <el-image
              style="width: 100px; height: 100px"
              :src="tableForm.image"
              :preview-src-list="srcList">
            </el-image>
          </div>
        </el-form-item>
        <el-form-item label="审核备注" prop="remark" :label-width="formLabelWidth">
          <el-input v-model="tableForm.remark" autocomplete="off" disabled="false"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :plain="true" @click="doSubmit">审核成功</el-button>
        <el-button :plain="true" @click="Submit">审核失败</el-button>

      </div>
    </el-dialog>
  </el-main>
</template>

<script>

    export default {
        name: "DataDictionary",
        data:function(){
            return {
                srcList: [
                ],
                //这是下拉框 数据动态绑定
                options: [{
                    value: '0',
                    label: '审核成功'
                }, {
                    value: '1',
                    label: '待审核'
                }, {
                    value: '2',
                    label: '审核失败'
                }],
                //这是查询参数
                queryParams: {
                    //定义搜索维度
                    id:null,
                    state:null,
                    applyTime:null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },

                //这是表格是否占时加载动画
                loading:false,
                //这书数据对象（用于表格占时）
                dataList:[],
                //这是新值和修改弹出是否占时
                dialogFormVisible: false,
                //这是新值修改弹出
                dialogTitle:null,
                //这是文本框中文字说明的长度
                formLabelWidth: "100px",

                //弹出 form 数据动态绑定 用于添加和修改提交
                tableForm:{
                    id:null,
                    membersId:null,
                    score:null,
                    file:null,
                    image:null,
                    remark:null,
                },
                //表单验证
                rules:{
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
            }
        },
        methods:{
            onQuery() {
                this.queryParams.page = 1;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.MEMBERS_MAT_QUERYPAGER;
                let params = this.queryParams;
                //查询动画
                this.loading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.dataList = response.data.data
                    this.queryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.loading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //添加前期调用方法，打开太弹出
            handleAdd: function() {
                this.dialogTitle = "添加"
                this.dialogFormVisible = true;
            },
            //修改前期调用方法，给表单回显对应数据 并打开弹出
            certified: function(id, row) {
                this.tableForm.id = row.membersId;
                this.tableForm.membersId = row.membersId;
                this.tableForm.score = row.score;
                this.tableForm.file = row.file;
                this.tableForm.image = row.image;
                this.tableForm.remark = row.remark;

                this.dialogFormVisible = true;
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
            //这是修改状态的方法
            doSubmit: function(row) {
                this.$refs['tableForm'].validate((valid) => {
                    var url = null;
                    url = this.axios.urls.MEMBERS_MAT_UPDATE;
                    //发送请求
                    this.axios.post(url,{
                        id: this.tableForm.id,
                        state: 0
                    }).then(response => {
                        //这里是操作成功
                        this.doClearForm();
                        this.dialogFormVisible = false;
                        //重新查找
                        this.search();
                        //打印成功信息
                        this.$message({
                            message: '恭喜认证成功了',
                            type: 'success'
                        });
                    }).catch(function(error) {
                        console.log(error);
                    });

                });
            },
            Submit: function(row) {
                debugger
                this.$refs['tableForm'].validate((valid) => {
                    var url = null;
                    url = this.axios.urls.MEMBERS_MAT_UPDATE;


                    //发送请求
                    this.axios.post(url, {
                        id: this.tableForm.id,
                        state: 2
                    }).then(response => {
                        //这里是操作成功
                        this.doClearForm();
                        this.dialogFormVisible = false;
                        //重新查找
                        this.search();
                        //打印成功信息
                        this.$message({
                            message: '错了哦认证失败',
                            type: 'warning'
                        });
                    }).catch(function(error) {
                        console.log(error);
                    });

                });
            },

            //清空表单是数据
            doClearForm: function() {
                this.$refs['tableForm'].resetFields();
                this.tableForm.membersId = null;
                this.tableForm.score = null;
                this.tableForm.file = null;
                this.tableForm.image = null;
                this.tableForm.remark = null;

            },
            //清空表单数据，并且关闭弹出
            doCancel: function() {
                this.doClearForm();
                //关闭弹出
                this.dialogFormVisible = false;
            },
            showSysdictItem: function(id, row){
                this.drawer = true;
                this.dictItemQueryParams.parentid = row.id;
                this.tableForm.address = "1";

            },
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
