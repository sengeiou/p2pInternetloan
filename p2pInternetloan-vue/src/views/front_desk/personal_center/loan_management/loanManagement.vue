<template>
  <div>
    <el-card class="box-card"   v-if="LoanRecordList.length > 0" style="margin-bottom: 20px;">
      <div class="clearfix" slot="header">
        <span>正在进行借款项目</span>
        <i style="float: right; padding: 3px 0" type="text">借款标题:{{bidRequest.title}}</i>
      </div>

      <el-row>
        <el-col :span="6">
          <span>借贷额度:</span>
          <div>
            <span style="font-size: 33px;">{{bidRequest.bidRequestAmount}}</span>
            <span>￥</span>
          </div>
        </el-col>
        <el-col :span="6">
          <span>年利率:</span>
          <div>
            <span style="font-size: 33px; color: rgb(255, 87, 34);">{{bidRequest.currentRate_dictText}}</span>
          </div>
        </el-col>
        <el-col :span="6">
          <span>借款期限:</span>
          <div>
            <span style="font-size: 33px;">{{bidRequest.monthesReturn}}</span>
            <span>个月</span>
          </div>
        </el-col>
        <el-col :span="6">
          <span>当前状态:</span>
          <div>
            <span style="font-size: 33px;">{{bidRequest.bidRequestState_dictText}}</span>
          </div>
        </el-col>
        <el-col style="top: 0px; margin-top: 20px;">
          <el-col :span="6">还款方式：{{bidRequest.returnType_dictText}}</el-col>
          <el-col :span="6">贷款类型：{{bidRequest.bidRequestType_dictText}}</el-col>
        </el-col>
        <el-col style="top: 0px; margin-top: 20px;" v-if="bidRequest.bidRequestState == 0 || bidRequest.bidRequestState== 6">
          <el-col :span="6">投标数量：{{bidRequest.returnType_dictText}}</el-col>
          <el-col :span="6">投标总额：{{bidRequest.currentSum}}</el-col>
          <el-col :span="6">还需额度：{{bidRequest.bidRequestAmount - bidRequest.currentSum }}</el-col>
        </el-col>
        <el-col style="top: 0px; margin-top: 15px;">
          <el-col :span="6">申请日期: {{bidRequest.applyTime}}</el-col>
          <el-col :span="12">投标截止日期: {{bidRequest.disableDate}}</el-col>
        </el-col>
        <el-col style="top: 0px; margin-top: 16px;">
          <el-button @click="" type="text" size="small" style="float: right;" @click="toInfo">投标详情</el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-card  style="margin-top: 20px;" class="box-card" v-if="bidRequest.bidRequestState == 4">
      <div class="clearfix" slot="header">
        <span>项目还款计划明显</span>
        <i style="float: right; padding: 3px 0;color: red;" type="text">系统会在到期自动扣除您账户上的余额,请保障每期账户余额足以支付当前期的本息</i>
      </div>
      <el-row>
        <el-table style="margin-top: 10px;" :data="refundDetailList" :fit="true" :show-header="true">
          <el-table-column prop="monthIndex" label="期数">
          </el-table-column>
          <el-table-column prop="principal" label="应还本金">
          </el-table-column>
          <el-table-column prop="interest" label="应还利息">
          </el-table-column>
          <el-table-column prop="deadLine" label="还款截止期限">
          </el-table-column>
          <el-table-column prop="payDate" label="还款时间">
          </el-table-column>
          <el-table-column prop="state" label="状态">
          </el-table-column>
        </el-table>
      </el-row>
    </el-card>


    <el-card header="我的所有借款">
      <el-table style="margin-top: 10px;" :data="LoanRecordList" :fit="true" :show-header="true">
        <el-table-column prop="title" label="标题">
        </el-table-column>
        <el-table-column prop="bidRequestAmount" label="借款金额">
        </el-table-column>
        <el-table-column prop="currentRate_dictText" label="年利率">
        </el-table-column>
        <el-table-column prop="monthesReturn" label="贷款期限/月">
        </el-table-column>
        <el-table-column prop="refundDetailList" label="还款方式">
        </el-table-column>
        <el-table-column prop="bidRequestState_dictText" label="当前状态">
        </el-table-column>
        <el-table-column prop="remark" label="备注">
        </el-table-column>
        <el-table-column prop="name" label="操作">
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                     :total="queryParams.total">
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "loanManagement",
        data:function(){
            return{
                //这是当前借款项目
                bidRequest:{
                    id:null,
                    returnType:null,
                    bidRequestType_dictText:null,
                    bidRequestAmount:null,
                    currentRate_dictText:null,
                    monthesReturn:null,
                    monthesReturn_dictText:null,
                    title:null,
                    description:null,
                    disableDate:null,
                    bidRequestState:null,
                    bidRequestState_dictText:null,
                    membersId:null,
                    applyTime:null,
                    applierName:null,
                    remark:null,
                    publishTime:null,
                    bidCount:null,
                    currentSum:null,
                    returnType_dictText:null,
                    current_sum:null
                },
                LoanRecordList:[],
                queryParams: {
                    // 分页
                    page: 1,
                    rows: 5,
                    total: 0,
                },
                loading:false,
                //这是用户还款计划相关
                refundDetailList:null,
                refundDetailLoading:false,
                queryRefundDetailParams:{
                    bidRequestId:null,
                    page: 1,
                    rows: 100,
                    total: 0,
                }
            }
        },
        methods:{
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.BORROWING_BIDREQUEST_MEMBERS_BID_REQUEST;
                let params = this.queryParams;
                //查询动画
                this.loading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.LoanRecordList = response.data.data
                    this.queryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.loading = false;
                }).catch(function(error) {
                    console.log(error);
                });
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
            //跳入详情页面
            toInfo(){
                //将参数放入到vuex中传到另一个页面
                this.$store.commit('setParamObj',{
                    paramObj: this.bidRequest
                });
                commonUtils.toRouter('/InvestmentDetails');
            },
            searchRefundDetail(){
                let url = this.axios.urls.BORROWING_REFUNDETAIL_QUERY;
                let params = this.queryRefundDetailParams;
                //查询动画
                this.refundDetailLoading = true;
                console.log(params);
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.refundDetailList = response.data.data
                    this.queryRefundDetailParams.total = response.data.total;
                    console.log(this.refundDetailList)
                    //数据查询到了关闭查询动画
                    this.refundDetailLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            }

        },
        created() {
            commonUtils.init(this);//加载用户当前的项目
            //获取当前用户的借口信息
            let url = this.axios.urls.BORROWING_BIDREQUEST_GET_CURRENT_BID_REQUEST;
            commonUtils.doAjaxGet(url, {}, (data)=>{
                //验证是否为空
                if(data.data != null && data.data.length > 0){
                    this.bidRequest = data.data[0];
                    console.log("-----------下面")
                    console.log(this.bidRequest.id);
                    this.queryRefundDetailParams.bidRequestId = this.bidRequest.id;
                    this.search();
                    this.searchRefundDetail();
                }
            })

        }
    }
</script>

<style scoped>

</style>
