<template>
  <el-main style="background-color: #FFFFFF;">
    <el-page-header @back="goBack" content="信用借贷审核">
    </el-page-header>

    <el-form label-width="100px">
      <el-divider content-position="center">借款信息</el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="借款人">
            <el-input v-model="bidRequest.applierName"  placeholder="请输入内容" :disabled="true">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="借款标题">
            <el-input v-model="bidRequest.title" placeholder="请输入内容" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="年利率">
            <el-input v-model="bidRequest.currentRate_dictText" placeholder="请输入内容" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="还款方式">
            <el-input v-model="bidRequest.returnType" placeholder="请输入内容" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="还款期限">
            <el-input v-model="bidRequest.monthesReturn_dictText" placeholder="请输入内容" disabled>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="截止时间">
            <el-input v-model="bidRequest.disableDate" placeholder="请输入内容" disabled>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="借款说明">
          <el-input v-model="bidRequest.description" placeholder="请输入内容" type="textarea" :rows="4" disabled>
          </el-input>
        </el-form-item>
      </el-row>
    </el-form>
    <el-col :span="24">
      <el-row style="float: right">
        <el-button @click="fail()" type="danger">拒绝放款</el-button>
        <el-button @click="doSubmit(true)" type="success">同意放款</el-button>
      </el-row>
    </el-col>

    <!--修购用户密码-->
    <el-dialog width="50%" title="决绝原因" :visible="failVisible" @close="doCancel('dialogPwdFormVisible', 'pwdForm')">
      <el-form :model="pwdForm" :rules="pwdRules" ref="pwdForm">
        <el-form-item label="拒绝原因" prop="remark" :label-width="100">
          <el-input v-model="bidRequest.remark" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel()">关闭</el-button>
        <el-button type="primary" @click="doSubmit(false)">确 定</el-button>
      </div>
    </el-dialog>


  </el-main>

</template>

<script>
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "creditLoanReview",

        data:function(){
            return {
                //这是决绝弹出 显示控制
                failVisible:false,
                //这是借贷信息对象
                bidRequest:{
                    id:null,
                    returnType:null,
                    bidRequestType_dictText:null,
                    bidRequestType:null,
                    bidRequestAmount:null,
                    currentRate_dictText:null,
                    monthesReturn_dictText:null,
                    title:null,
                    description:null,
                    disableDate:null,
                    membersId:null,
                    applyTime:null,
                    applierName:null,
                    remark:null
                }
            }
        },
        watch: {
            // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
            '$route':'getParams'
        },
        methods:{
            getParams:function(){
                debugger
                alert("请求成功")
                // 取到路由带过来的参数
                var routerParams = this.$route.query
                // 将数据放在当前组件的数据内
                console.log("传来的参数=="+routerParams)
            },
            //这是审核不通过调用的方法
            fail(){
                this.failVisible = true;
            },
            //提交
            doSubmit(isBy){
                let url = this.axios.urls.BORROWING_BIDREQUEST_UPDATE;
                var parpam = {
                    id:this.bidRequest.id
                }
                if(isBy){
                    parpam.bidRequestState = 0;
                }else{
                    parpam.bidRequestState = 1;
                    //这是放入拒绝原因
                    parpam.remark = this.bidRequest.remark;
                }
                commonUtils.doAjaxPost(url, parpam, (data) => {
                    //跳回去
                    commonUtils.toRouter("/creditLoanManagement")
                })
            },
            doCancel(){
                this.failVisible = false;
                this.bidRequest.description = null;
            },
            goBack(){
                commonUtils.toRouter("/creditLoanManagement")
            }
        },
        created() {
            commonUtils.init(this);
            //获取vuex中的参数
            commonUtils.echo(this.$store.getters.getParamObj, "bidRequest");
        }
    }
</script>

<style scoped>

</style>

