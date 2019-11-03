<template>
  <el-main style="background-color: #FFFFFF;">
    <el-page-header @back="goBack" content="项目满标审核">
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
        <el-button @click="fail()" type="danger">拒绝通过</el-button>
        <el-button @click="doSubmit(true)" type="success">审核通过</el-button>
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
        name: "fullStandardReview",
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
                    currentRate:null,
                    monthesReturn_dictText:null,
                    monthesReturn:null,
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
            //这是审核不通过调用的方法
            fail(){
                this.failVisible = true;
                this.bidRequest.remark = null;
            },
            //这是盛和提交（true代表通过 false 代表不通过）
            doSubmit(isBy){
                let url = this.axios.urls.BORROWING_BIDREQUEST_UPDATE;
                var parpam = {
                    id:this.bidRequest.id
                }
                if(isBy){
                    parpam.bidRequestState  = 4;
                    // parpam.bidRequestType = this.bidRequest.bidRequestType;
                    parpam.bidRequestType = 1;
                    parpam.id = this.bidRequest.id;
                    parpam.monthesReturn = this.bidRequest.monthesReturn;
                    parpam.returnType = this.bidRequest.returnType;
                    parpam.bidRequestAmount = this.bidRequest.bidRequestAmount;
                    parpam.currentRate = this.bidRequest.currentRate;
                    parpam.monthesReturn = this.bidRequest.monthesReturn;
                    console.log(parpam);
                }else{
                    parpam.bidRequestState = 8;
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




