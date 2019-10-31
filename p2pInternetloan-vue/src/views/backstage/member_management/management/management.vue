<template>
   <el-main  style="background-color: #FFFFFF;" >
<!--    <el-form class="demo-form-inline" style="margin-top: 10px;background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">-->
<!--      <el-form-item label="是否可用">-->
<!--        <el-select v-model="queryParams.membersState" filterable placeholder="请选择">-->
<!--          <el-option-->
<!--            v-for="item in options"-->
<!--            :key="item.value"-->
<!--            :label="item.label"-->
<!--            :value="item.value"-->
<!--             >-->
<!--          </el-option>-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="用户名">-->
<!--        <el-input v-model="queryParams.name" placeholder="请输入用户名">-->
<!--        </el-input>-->
<!--      </el-form-item>-->

<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->

    <!-- 数据表格-->
    <el-table :data="dataList"  height="440" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column  prop="id" label="ID" min-width="1">
      </el-table-column>
      <el-table-column prop="membersId" label="会员id" min-width="3">
      </el-table-column>
      <el-table-column prop="tradePassword" label="交易密码" min-width="3">
      </el-table-column>
      <el-table-column prop="usableAmount" label="账户可用余额" min-width="3">
      </el-table-column>
      <el-table-column prop="unReceiveInterest" label="账户待收利息" min-width="3">
      </el-table-column>
      <el-table-column prop="unReceivePrincipal" label="账户代收本金" min-width="3">
      </el-table-column>
      <el-table-column prop="borrowLimit" label="账户授信额度" min-width="3">
      </el-table-column>
      <el-table-column prop="tradeCode" label="支付宝唯一标识" min-width="3">
      </el-table-column>
      <el-table-column label="操作" min-width="3">
        <template slot-scope="scope">
<!--          <el-button type="text" size="small"  @click="showSysdictItem(scope.$index, scope.row)">详情</el-button>-->
<!--          <el-button @click="" type="text" size="small"  @click="certified(scope.$index, scope.row)">认证</el-button>-->
<!--          <el-button @click="" type="text" size="small"  @click="certified(scope.$index, scope.row)">是否可用</el-button>-->
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                   :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                   :total="queryParams.total">
    </el-pagination>
   </el-main>
</template>

<script>

    export default {
        name: "DataDictionary",
        data:function(){
            return {
                //这是下拉框 数据动态绑定
                options: [{
                    value: '0',
                    label: '可用'
                }, {
                    value: '1',
                    label: '不可用'
                }],
                //这是查询参数
                queryParams: {
                    //定义搜索维度
                    membersState:null,
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
                    realname:null,
                    sex:null,
                    bornDate:null,
                    idNumber:null,
                    address:null,
                    remark:null,
                    state:null,
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
                //这是弹出 框标题
                // dialogTitle:null
            }
        },
        methods:{
            onQuery() {
                this.queryParams.page = 1;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.MEMBERS_ACCOUNT_QUERYPAGER;
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
                this.dialogTitle = "认证";
                this.tableForm.realname = row.realname;
                this.tableForm.sex = row.sex;
                this.tableForm.bornDate = row.bornDate;
                this.tableForm.idNumber = row.idNumber;
                this.tableForm.address = row.address;
                this.tableForm.remark = row.remark;
                this.tableForm.image1 = row.image1;
                this.tableForm.image2 = row.image2;
                this.tableForm.state = row.state;

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
            doSubmit: function() {
                this.$refs['tableForm'].validate((valid) => {
                    if (valid) {
                         var url = null;
                         url = this.axios.urls.SYSTEM_DICT_UPDATE;
                          var obj  = {};
                          obj.state = 1;
                          debugger
                          obj.id = 1;
                          debugger

                        //发送请求
                        this.axios.post(url, obj).then(response => {
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
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            // Submit: function() {
            //     this.$refs['tableForm'].validate((valid) => {
            //         var url = null;
            //         url = this.axios.urls.SYSTEM_DICT_UPDATE;
            //         //发送请求
            //         this.axios.post(url, this.tableForm).then(response => {
            //
            //             this.dialogFormVisible = false;
            //             //重新查找
            //             this.search();
            //             //打印成功信息
            //             this.$message({
            //                 message: '错了哦认证失败',
            //                 type: 'warning'
            //             });
            //         }).catch(function(error) {
            //             console.log(error);
            //         });
            //
            //     });
            // },
            //这是删除的方法
            del:function(id, row){
                this.$confirm('你确定要哦删除吗?', '警告', {
                    //定义两个按钮 确定和取消
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除
                    var url = this.axios.urls.MEMBERS_MEN_DEL + row.id;
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
                this.$refs['tableForm'].resetFields();
                this.tableForm.realname = null;
                this.tableForm.sex = null;
                this.tableForm.bornDate = null;
                this.tableForm.idNumber = null;
                this.tableForm.address = null;
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
