<template>
  <el-main>
    <el-card class="box-card">
      <div class="clearfix" slot="header">
        <el-steps :active="showIndex">
          <el-step title="信用带说明" icon="el-icon-edit">
          </el-step>
          <el-step title="填写申请借款表单" icon="el-icon-edit">
          </el-step>
          <el-step title="确认贷款信息" icon="el-icon-star-off">
          </el-step>
          <el-step title="完成" icon="el-icon-circle-check">
          </el-step>
        </el-steps>
      </div>

      <!--  1、步骤一  这是 信用贷款信息  展示 模块  -->
      <el-form v-if="showIndex == 1">
        <el-row><br/>
          <!-- 1.1 贷款条件 -->
          <el-col :span="7">
            <el-card class="box-card" style="background-color: lightyellow;">
              <!--  头 部  -->
              <div slot="header" class="clearfix" style="color: orangered"><strong>贷 款 条 件：</strong></div><br/><br/><br/>
              <!--  主 体  -->
              <div class="text item">
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基本资料填写&nbsp;&nbsp;&nbsp;&nbsp;<el-button type="danger" size="mini" icon="el-icon-check" circle></el-button></span><br/><br/><br/>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;身份认证通过&nbsp;&nbsp;&nbsp;&nbsp;<el-button type="danger" size="mini" icon="el-icon-check" circle></el-button></span><br/><br/><br/>
                <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;资料认证完善&nbsp;&nbsp;&nbsp;&nbsp;<el-button type="danger" size="mini" icon="el-icon-check" circle></el-button></span><br/><br/><br/>
              </div><br/>
            </el-card>
          </el-col>
          <el-col :span="1">&nbsp;</el-col><!--  间隙  -->
          <!-- 1.2 贷款须知 -->
          <el-col :span="7">
            <el-card class="box-card" style="background-color: lightyellow;">
              <!--  头 部  -->
              <div slot="header" class="clearfix" style="color: orangered"><strong>贷 款 须 知：</strong></div><br/>
              <!--  主 体  -->
              <div class="text item">
                <p>1. 必须满足贷款的条件方可进行贷款</p>
                <p>2. 贷款申请需申请人认真并酌情填写</p>
                <p>3. 贷款申请内容须遵守一定的规范</p>
                <p>4. 贷款申请人最高可贷 10000 元</p>
                <p>5. 申请有流程，请耐心等待，♪(^∇^*)</p>
                <p>6. 关于贷款，我方具有绝对的解释权</p>
              </div><br/>
            </el-card>
          </el-col>
          <el-col :span="1">&nbsp;</el-col><!--  间隙  -->
          <!-- 1.3 贷款流程 -->
          <el-col :span="8">
            <el-card class="box-card" style="background-color: lightyellow;">
              <!--  头 部  -->
              <div slot="header" class="clearfix" style="color: orangered"><strong>贷 款 流 程：</strong></div><br/>
              <!--  主 体  -->
              <div class="text item">
                <p align="left">1、贷款申请填写、提交</p>
                <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style="color: orangered;font-size: 34px" class="el-icon-bottom"></i></p>
                <p align="left">2、工作人员进行审核</p>
                <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i style="color: orangered;font-size: 34px" class="el-icon-bottom"></i></p>
                <p align="left">3、审核通过，资金到账</p>
                <p align="right" style="color: palevioletred;"><i class="el-icon-warning-outline"></i> 贷款申请不合格，不予通过！</p>
              </div>
            </el-card>
          </el-col>
        </el-row><br/><br/>
        <el-row>
            <p style="color: green;" align="right">我已熟知贷款信息&nbsp;&nbsp;<i style="font-size: 18px" class="el-icon-bottom-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;
              <el-button type="success" round @click="showIndex ++" style="float: right">我要贷款</el-button>
            </p>
        </el-row>
      </el-form>


      <!--这是填写基本信息模块-->
      <el-form v-if="showIndex == 2"  :model="loanForm" :rules="rules" ref="loanForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="借款标题" prop="title"  label-width="100px">
              <el-input  v-model="loanForm.title" placeholder="请输入借款标题">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="借款金额" prop="bidRequestAmount" label-width="100px">
              <el-input v-model="loanForm.bidRequestAmount" placeholder="请输入借款金额">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年利率" prop="currentRate" label-width="100px">
              <el-select  v-model="loanForm.currentRate" style="width: 100%;" value="1" size="200px"  placeholder="请选择年利率">
                <el-option
                  v-for="item in options.annualInterestRate"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="还款方式" prop="returnType" label-width="100px">
              <el-select v-model="loanForm.returnType" style="width: 100%;" value="1"  placeholder="请选择还款方式">
                <el-option
                  v-for="item in options.repayment"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="还款期限" prop="monthesReturn" label-width="100px">
              <el-select v-model="loanForm.monthesReturn" style="width: 100%;" value="1"  placeholder="请选择还款期限">
                <el-option
                  v-for="item in options.creditRepaymentPeriod"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="截止时间" prop="disableDateForm" label-width="100px">
              <el-date-picker
                v-model="disableDateForm"
                align="right"
                type="date"
                placeholder="请选投标截止时间"  style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="借款说明" label-width="100px" prop="bidRequestAmount" >
              <el-input v-model="loanForm.description" type="textarea" :rows="4" placeholder="......">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-row style="float: right">
              <el-button @click="toRouter('/Home')"  >取消借款</el-button>
              <el-button @click="toConfirm" >下一步</el-button>
            </el-row>
          </el-col>
        </el-row>
      </el-form>

      <!--这是确认信息页-->
      <el-row v-if="showIndex == 3">
        <el-table style="margin-top:30px;" :data="repaymentInfo.refundDetails" show-summary :fit="true" :show-header="true">
          <el-table-column prop="monthIndex" label="还款期">
          </el-table-column>
          <el-table-column prop="principal" label="应还本金">
          </el-table-column>
          <el-table-column prop="interest" label="应还利息">
          </el-table-column>
          <el-table-column prop="totalAmount" label="共计">
          </el-table-column>
        </el-table>
        <el-col :span="24" style="margin-top: 20px;">
          <el-row style="float: right">
            <el-button @click="showIndex --" >上一步</el-button>
            <el-button @click="toCarryOut" icon="el-icon-success" type="success" >申请借贷</el-button>
          </el-row>
        </el-col>
      </el-row>

      <!--这是申请成功页-->
      <div style="text-align: center;margin-top: 40px;" v-if="showIndex == 4">
        <i class="el-icon-circle-check" style="font-size: 38px; color: rgb(14, 239, 59);">申请成功</i>
        <p style="color: rgb(153, 153, 153);">您的申请提交成功,申请结果将会在24小时内 短息/平台 同步,请耐心等待</p>
        <p style="color: rgb(153, 153, 153);">如有疑问 请致电 18874374737,很高效为您服务</p>
        <el-button @click="toRouter('/Home')" style="margin-top: 53px;" type="text" >返回首页</el-button>
      </div>

    </el-card>
  </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "CreditLoan",
        data:function(){
            //自定义验证
            var validateAmount = (rule, value, callback) => {

                if (isNaN(Number(value))) {
                    callback(new Error('请输入数字值'));
                }else{
                    if(value < 300){
                        callback(new Error('信用贷最低贷款额度为 300'));
                    }else if(value > 5000){
                        callback(new Error('信用贷最高贷款额度为 5000'));
                    } else{
                        callback();
                    }
                }
            }


            return {
                showIndex:1,
                //当前页面的几个下拉框
                options:{
                    //年利率
                    annualInterestRate:[],
                    creditRepaymentPeriod:[],
                    repayment:[]
                },
                disableDateForm:null,
                //这是要提交的form
                loanForm:{
                    title:null,
                    bidRequestAmount:null,
                    currentRate:null,
                    returnType:null,
                    monthesReturn:null,
                    disableDate:null,
                    description:null

                },
                //表单验证
                rules:{
                    title:[{
                        required: true,
                        message: '请输入借款标题',
                        trigger: 'blur'
                    }],
                    bidRequestAmount:[{
                        required: true,
                        message: '请输入借款金额',
                        trigger: 'blur'
                    }, {
                        validator: validateAmount,
                        trigger: 'blur'
                    }],
                    currentRate:[{
                        required: true,
                        message: '请选择年利率',
                        trigger: 'blur'
                    }],
                    returnType:[{
                        required: true,
                        message: '请选择还款方式',
                        trigger: 'blur'
                    }],
                    monthesReturn:[{
                        required: true,
                        message: '请选择还款期限',
                        trigger: 'blur'
                    }],
                    description:[{
                        required: true,
                        message: '请输入借款说明',
                        trigger: 'blur'
                    }]
                },
                //这是还款计划展示用到的
                repaymentInfo:{
                    //表格占时数据
                    refundDetails:[],
                }
            }
        },
        methods:{
            toRouter(routerPath){
                commonUtils.toRouter(routerPath)
            },
            //条确认借贷信息页
            toConfirm(){
                this.$refs['loanForm'].validate((valid) => {
                    if(valid){
                        //时间格式处理
                        this.loanForm.disableDate = commonUtils.formatDate(this.disableDateForm, "yyyy/MM/dd");
                        //计算还款计划信息
                        let url = this.axios.urls.BORROWING_REFUNDETAIL_CALCULATIONREFUNDDETAIL;
                        commonUtils.doAjaxGet(url, this.loanForm, (data)=>{
                            console.log(data);
                            this.repaymentInfo.refundDetails = data.refundDetails;
                            this.showIndex ++;
                        })

                    }
                });
            },
            //这是申请借贷
            toCarryOut(){
                //将借贷信息提交到后台
                let url =  this.axios.urls.BORROWING_BIDREQUEST_APPLICATION_CREDIT_LOAN;
                commonUtils.doAjaxPost(url, this.loanForm, (data)=>{
                    //这是贷款完成
                    this.showIndex ++;
                })
            }
        },
        created() {
            commonUtils.init(this);
            //加载下拉框
            commonUtils.getDictSelect("annual_interest_rate", "options.annualInterestRate")
            commonUtils.getDictSelect("repayment", "options.repayment")
            commonUtils.getDictSelect("credit_repayment_period", "options.creditRepaymentPeriod")
        }
    }
</script>

<style scoped>
  * {

  }
</style>
