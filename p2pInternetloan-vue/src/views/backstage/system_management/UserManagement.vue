<template>
  <el-main  style="background-color: #FFFFFF;" >
    <el-form class="demo-form-inline" style="background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
      <el-form-item label="用户名">
        <el-input v-model="queryParams.userName" placeholder="请输入用户名称">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
        <el-button type="success" icon="el-icon-plus" @click="handleAdd">添加</el-button>
      </el-form-item>
    </el-form>

    <!-- 数据表格-->
    <el-table :data="tableList"  height="360" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column class="column" type="index" :index="indexMethod" min-width="50" label="#" width="50" align="center">
      </el-table-column>
      <el-table-column class="column" prop="userName" label="用户名称" width="110" align="center">
      </el-table-column>
      <el-table-column class="column" prop="roleNames" label="角色" align="center">
      </el-table-column>
      <el-table-column class="column" prop="createdate" label="创建时间" align="center">
      </el-table-column>
      <el-table-column class="column" label="操作" align="center">
        <template slot-scope="scope">
          <el-button @click="" type="text" size="small"  @click="handleEdit(scope.row, 'auForm', 'dialogFormVisible', 'dialogTitle')">编辑</el-button>
          <el-button @click="" type="text" size="small"  @click="handleEditPwd(scope.row, 'pwdForm', 'dialogPwdFormVisible')">密码</el-button>
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
<!--    10vh-->
     <el-dialog :top="dialogTop" width="50%" :title="dialogTitle" :visible="dialogFormVisible" @close="doCancel('dialogFormVisible', 'auForm')">
      <el-form :model="auForm" :rules="rules" ref="auForm">
        <el-form-item label="用户名" prop="userName" :label-width="formLabelWidth">
          <el-input v-model="auForm.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item v-if="auForm.userId == null" label="密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="auForm.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item v-if="auForm.userId == null" label="确认密码" prop="passwordOk" :label-width="formLabelWidth">
          <el-input v-model="auForm.passwordOk" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="用户角色" prop="roleIds" :label-width="formLabelWidth">
          <el-select v-model="auForm.roleIds" multiple placeholder="请选择">
            <el-option
              v-for="item in optionsRoles"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否可用" prop="roleDesc" :label-width="formLabelWidth">
          <el-switch
            v-model="auForm.userFlagIs"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel('dialogFormVisible', 'auForm')">关闭</el-button>
        <el-button type="primary" @click="doSubmit">确 定</el-button>
      </div>
    </el-dialog>

    <!--修购用户密码-->
    <el-dialog width="50%" title="重新设定密码" :visible="dialogPwdFormVisible" @close="doCancel('dialogPwdFormVisible', 'pwdForm')">
      <el-form :model="pwdForm" :rules="pwdRules" ref="pwdForm">
        <el-form-item label="用户名" prop="userName"   :label-width="formLabelWidth">
          <el-input v-model="pwdForm.userName" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="登录密码" prop="password" :label-width="formLabelWidth">
          <el-input v-model="pwdForm.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="passwordOk" :label-width="formLabelWidth">
          <el-input v-model="pwdForm.passwordOk" autocomplete="off" show-password></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel('dialogPwdFormVisible', 'pwdForm')">关闭</el-button>
        <el-button type="primary" @click="doSubmitPwd">确 定</el-button>
      </div>
    </el-dialog>
  </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "userManagement",
        data:function(){

            var validatePassOk = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.auForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };


            var validatePwdOk = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.pwdForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };


            return {
                //这是查询参数
                queryParams: {
                    //定义搜索维度
                    userName: null,
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
                    userId:null,
                    userName:null,
                    password:null,
                    passwordOk:null,
                    userFlagIs:true,
                    userFlag:null,
                    //用户所绑定的角色
                    roleIds:null
                },
                //表单验证
                rules:{
                    userName:[{
                        required: true,
                        message: '请输用户名',
                        trigger: 'blur'
                    }],
                    password:[{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    },{
                        min: 3,
                        max: 10,
                        message: '密码长度在3到10个字符之间',
                        trigger: 'blur'
                    }],
                    passwordOk:[{
                        required: true,
                        message: '请再次输出密码',
                        trigger: 'blur'
                    },{
                        validator: validatePassOk,
                        trigger: 'blur'
                    }],
                    roleIds:[{
                        required: true,
                        message: '请选择角色',
                        trigger: 'blur'
                    }]
                },
                //这是弹出框距离顶部距离
                dialogTop:'10vh',
                //这是角色数据保存
                optionsRoles:[],
                //修改密码相关
                pwdForm:{
                    userId:null,
                    userName:null,
                    password:null,
                    passwordOk:null
                },
                dialogPwdFormVisible:false,
                pwdRules:{
                    password:[{
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur'
                    },{
                        min: 3,
                        max: 10,
                        message: '密码长度在3到10个字符之间',
                        trigger: 'blur'
                    }],
                    passwordOk:[{
                        required: true,
                        message: '请再次输出密码',
                        trigger: 'blur'
                    },{
                        validator: validatePwdOk,
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
                let url = this.axios.urls.SYSTEM_USER_QUERY;
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
                this.dialogTop = '10vh';
                this.dialogFormVisible = true;
            },
            //这是修改前期调用
            handleEdit: function(row, formDataName, showDialogDataName, dialogTitleDataName) {
                this.dialogTop = '20vh';
                commonUtils.handleEdit(row , formDataName, showDialogDataName, dialogTitleDataName)
                //状态特殊处理
                this.auForm.userFlagIs = this.auForm.userFlag == 1? true: false;
            },
            //修改用户密码前期
            handleEditPwd:function(row, formDataName, showDialogDataName){
                commonUtils.handleEdit(row , formDataName, showDialogDataName);
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
                        if (null != this.auForm.userId) {
                            url = this.axios.urls.SYSTEM_USER_EDIT;
                        } else {
                            url = this.axios.urls.SYSTEM_USER_ADD;
                        }
                        //是否可用处理
                        this.auForm.userFlag = this.auForm.userFlagIs? 1:0;
                        //角色字符串处理
                        this.auForm.roleIds = commonUtils.arrToStr(this.auForm.roleIds);
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
            //修改密码提交
            doSubmitPwd:function(){
                //valid为true即为通过
                this.$refs['pwdForm'].validate((valid) => {
                    if (valid) {
                        let url = this.axios.urls.SYSTEM_USER_EDIT_PWD;
                        console.log(this.pwdForm);
                        //发送请求
                        this.axios.post(url, this.pwdForm).then(response => {
                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                });
                            } else {
                                //这里是操作成功
                                this.doCancel('dialogPwdFormVisible', 'pwdForm')
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
                    var url = this.axios.urls.SYSTEM_USER_DEL + row.userId;
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
                this.auForm.userFlagIs = true;
                //清空表单验证
                formObj.resetFields();
            },
            //和授权操作想干
            handleClose: function() {
                this.hang = "fa fa-hand-o-right fa-1x";
                this.jurColor = "success";
                this.dialog = false;
            },indexMethod: function(index) {
                return index + 1;
            }
        },
        created() {
            commonUtils.init(this);
            let url =  this.axios.urls.SYSTEM_ROLE_GETSELECT;
            //加载角色数据
            commonUtils.doAjaxGet(url, {}, (data)=>{
                this.optionsRoles = data;
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

