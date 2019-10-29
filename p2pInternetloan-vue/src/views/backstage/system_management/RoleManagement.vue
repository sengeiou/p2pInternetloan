<template>
  <el-main  style="background-color: #FFFFFF;" >
    <el-form class="demo-form-inline" style="background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
      <el-form-item label="角色名">
        <el-input v-model="queryParams.roleName" placeholder="请输入角色名称">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 数据表格-->
    <el-table :data="tableList"  height="360" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column  prop="roleId" label="#" width="50">
      </el-table-column>
      <el-table-column class="column" prop="roleName" label="角色名称" width="110" align="center">
      </el-table-column>
      <el-table-column class="column" prop="roleCoding" label="角色编码"  width="110" align="center">
      </el-table-column>
      <el-table-column class="column" prop="roleDesc" label="备注" align="center">
      </el-table-column>
      <el-table-column class="column" prop="creationTime" label="创建时间" align="center">
      </el-table-column>
      <el-table-column class="column" prop="updateTime" label="更新时间" align="center">
      </el-table-column>
      <el-table-column class="column" label="操作" align="center">
        <template slot-scope="scope">
          <el-button @click="" type="text" size="small"  @click="handleEdit(scope.row, 'auForm', 'dialogFormVisible', 'dialogTitle')">编辑</el-button>
          <el-button @click="" type="text" size="small"  @click="showAuthorization(scope.row)">授权</el-button>
          <el-button @click="" type="text" size="small"  @click="del(scope.id, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                   :total="queryParams.total">
    </el-pagination>


    <!--新增和修改弹出-->
    <el-dialog top="20vh" width="50%" :title="dialogTitle" :visible="dialogFormVisible" @close="doCancel('dialogFormVisible', 'auForm')">
      <el-form :model="auForm" :rules="rules" ref="auForm">
        <el-form-item label="角色名" prop="roleName" :label-width="formLabelWidth">
          <el-input v-model="auForm.roleName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色编码" prop="roleCoding" :label-width="formLabelWidth">
          <el-input v-model="auForm.roleCoding" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="roleDesc" :label-width="formLabelWidth">
          <el-input v-model="auForm.roleDesc" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel('dialogFormVisible', 'auForm')">关闭</el-button>
        <el-button type="primary" @click="doSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 授权 -->
    <el-drawer title="角色权限配置" :before-close="handleClose" :visible.sync="dialog" direction="rtl" custom-class="demo-drawer"
               ref="drawer">
      <div class="demo-drawer__content">
        <div style="height: 480px;">
          <!-- 树形控件-->
          <el-tree id="jurisdictionData" :data="jurisdictionData" show-checkbox getHalfCheckedKeys ref="jurisdictionData"
                   node-key="id" :default-checked-keys="[]" :props="defaultProps" @check="currentChecked">
          </el-tree>
        </div>
        <div style="margin-left: 220px;">
          <el-button @click="dialogClose">取 消</el-button>
          <el-button type="primary" @click="submitAuthorization()">确 定</el-button>
        </div>
      </div>
    </el-drawer>

  </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "RoleManagement",
        data:function(){
            return {
                //这是查询参数
                queryParams: {
                    //定义搜索维度
                    roleName: null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是表格是否占时加载动画
                loading:false,
                //表格数据对象
                tableList:[],
                //这是新值修改弹出 的标题
                dialogTitle:null,
                //这是新值和修改弹出是否占时
                dialogFormVisible: false,
                //这是文本框中文字说明的长度
                formLabelWidth: "100px",
                //弹出 form 数据动态绑定
                auForm:{
                    roleId:null,
                    roleName:null,
                    roleCoding:null,
                    roleDesc:null
                },
                //表单验证
                rules:{
                    roleName:[{
                        required: true,
                        message: '请输角色名',
                        trigger: 'blur'
                    }],
                    roleCoding:[{
                        required: true,
                        message: '请输入角色编码',
                        trigger: 'blur'
                    }]
                },
                //这是和授权操作相关的
                dialog: false,
                jurisdictionData:[],
                defaultProps: {
                    children: 'modules',
                    label: 'text'
                },
                //当前授权角色id
                currentRoleId:null,
                defaultJurData: [],
                hang: "fa fa-hand-o-right fa-1x",
                jurColor: "success"
            }
        },
        methods:{
            onQuery() {
                this.queryParams.page = 1;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.SYSTEM_ROLE_QUERY;
                let params = this.queryParams;
                //查询动画
                this.loading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.tableList = response.data.data
                    this.queryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.loading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //添加前期调用方法，打开太弹出
            handleAdd: function() {
                this.dialogTitle = "添加";
                this.dialogFormVisible = true;
            },
            //这是修改前期调用
            handleEdit: function(row, formDataName, showDialogDataName, dialogTitleDataName) {
                commonUtils.handleEdit(row , formDataName, showDialogDataName, dialogTitleDataName)
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
                //valid为true即为通过
                this.$refs['auForm'].validate((valid) => {
                    if (valid) {
                        var url = null;
                        //判断时添加还是修改
                        if (null != this.auForm.roleId) {
                            url = this.axios.urls.SYSTEM_ROLE_EDIT;
                        } else {
                            url = this.axios.urls.SYSTEM_ROLE_ADD;
                        }

                        //发送请求
                        this.axios.post(url, this.auForm).then(response => {
                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                });
                            } else {
                                //这里是操作成功
                                this.doCancel('dialogFormVisible', 'auForm')
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
            handleCommand:function(command, row){
              console.log(command)
              console.log(row);
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
                    var url = this.axios.urls.SYSTEM_ROLE_DEL + row.roleId;
                    //这是调用请求的方法
                    commonUtils.doAjaxPost(url, {}, this.search);
                }).catch(() => {});
            },
            //清空表单数据，并且关闭弹出
            doCancel: function(closeDialogDataName, refForm) {
                this[closeDialogDataName] = false;
                //表单对象
                var formObj = this.$refs[refForm] ;
                //表单数据对象
                var formData = formObj.model
                //清空数据
                for(var name in formData){
                    formData[name] = null;
                }
                //清空表单验证
                formObj.resetFields();
            },
            //和授权操作想干
            handleClose: function() {
                this.hang = "fa fa-hand-o-right fa-1x";
                this.jurColor = "success";
                this.dialog = false;
            },
            //占时授权框
            showAuthorization(row){
                this.doDrawer();
                //定位当前权限
                this.currentRoleId = row.roleId;
                //加载当前角色所拥有的权限
                commonUtils.doAjaxGet(this.axios.urls.SYSTEM_PERMISSION_QUERY_ID_BY_ROLE,{roleId:row.roleId},(data)=>{
                    //权限树赋值
                    this.$nextTick(() => {
                        var checkedkeys = [];
                        for(var cid in data){
                            var childNodes =  this.$refs.jurisdictionData.getNode(data[cid]).childNodes;
                            if(childNodes == null || childNodes.length == 0){
                                checkedkeys.push(data[cid]);
                            }
                        }
                        //设置选中
                        this.$refs.jurisdictionData.setCheckedKeys([]);
                        this.$refs.jurisdictionData.setCheckedKeys(checkedkeys);
                    },1000);
                });
            },
            //提交授权
            submitAuthorization(){
                //获取当前选中的节点
                /* 获取选中的节点getCheckedKeys
                   获取半选中的节点 getHalfCheckedKeys
                */
                let checkedKeys = this.$refs.jurisdictionData.getCheckedKeys();
                let halfCheckedKeys = this.$refs.jurisdictionData.getHalfCheckedKeys();
                let auth = [];
                auth = checkedKeys;
                /* 循环加入auth 数组*/
                for (var i = 0; i < halfCheckedKeys.length; i++) {
                    auth.push(halfCheckedKeys[i])
                }
                let permissionIds = commonUtils.arrToStr(auth)
                //授权
                commonUtils.doAjaxPost(
                    this.axios.urls.SYSTEM_ROLE_AUTHORIZATION,
                    {roleId:this.currentRoleId, permissionIds:permissionIds},
                    ()=>{
                        //将权限抽屉关闭
                        this.dialog = false;
                    })
            },
            dialogClose(){
                //关闭弹出
                this.dialog = false;
                //清空数据
                this.$refs.jurisdictionData.setCheckedKeys([]);
            },
            doDrawer: function() {
                this.hang = "fa fa-hand-o-left fa-1x";
                this.jurColor = "danger";
                this.loading = false;
                this.dialog = true;
            },
            currentChecked(data, currentChecked) {
                let auth = []
                const {
                    checkedNodes,
                    halfCheckedNodes
                } = currentChecked
                halfCheckedNodes.length && halfCheckedNodes.forEach(({
                                                                         id
                                                                     }) => {
                    auth.push({
                        id,
                        type: 2
                    })
                })
                checkedNodes.length && checkedNodes.forEach(({
                                                                 id
                                                             }) => {
                    auth.push({
                        id,
                        type: 1
                    })
                })
                // api 将auth数据保存至后台
            }
        },
        created() {
            commonUtils.init(this);
            let url =  this.axios.urls.SYSTEM_PERMISSION_GETROLEMODULES;
            //加载菜单(初始化菜单)
            commonUtils.doAjaxGet(url, {}, (data)=>{
                this.jurisdictionData = data;
            })

        }
    }
</script>

<style scoped>

  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }

</style>
