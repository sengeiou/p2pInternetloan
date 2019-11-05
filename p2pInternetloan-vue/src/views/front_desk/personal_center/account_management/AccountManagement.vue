<template>
  <div>
    <el-card class="box-card" >
      <div class="clearfix" slot="header">
        <span>账号信息</span>
      </div>
      <el-row :gutter="25">
        <h1></h1>
        <el-col :span="8">
          <el-card class="box-card" style="background-color: rgb(73, 147, 244);">
            <p>账号余额
              <i class="el-icon-wallet"></i>
            </p>
            <strong>{{statistics.usableAmount}}</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>代收利息
              <i class="el-icon-info"></i>
            </p>
            <strong>{{statistics.unReceiveInterest}}</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>代收本金
              <i class="el-icon-warning"></i>
            </p>
            <strong>{{statistics.unReceivePrincipal}}</strong>元</el-card>
        </el-col>
      </el-row>
      <el-row style="margin-top: 17px;" :gutter="25">
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>账户授信额度
              <i class="el-icon-user"></i>
            </p>
            <strong>{{statistics.borrowLimit}}</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>累计收益
              <i class="el-icon-s-claim"></i>
            </p>
            <strong>{{statistics.membersInterestSum}}</strong>元</el-card>
        </el-col>
      </el-row>
    </el-card>
    <el-tabs style="padding-top: 0px; margin-top: 25px;" type="border-card">
      <el-tab-pane label="回款记录">

        <!-- 数据表格-->
        <el-table :data="returnDetailList"  height="360" :fit="true" :show-header="true" v-loading="loading">
          <el-table-column class="column" prop="payDate" label="回款时间" width="110" align="center">
          </el-table-column>
          <el-table-column class="column" prop="title" label="项目" align="center">
          </el-table-column>
          <el-table-column class="column" prop="returnType_dictText" label="回款方式" align="center">
          </el-table-column>
          <el-table-column class="column" prop="principal" label="回款本金" align="center">
          </el-table-column>
          <el-table-column class="column" prop="interes" label="回款利息" align="center">
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination style="margin: 15px;" background @size-change="handleSizeChangeReturnDetail" @current-change="handleCurrentChangeReturnDetail"
                       :current-page="returnDetailQueryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="returnDetailQueryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                       :total="returnDetailQueryParams.total">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="投资记录">
        <!-- 数据表格-->
        <el-table :data="bidList"  height="360" :fit="true" :show-header="true" v-loading="bidLoading">
          <el-table-column prop="bid_time" label="交易时间">
            <template slot-scope="scope">
              {{new Date(parseInt(scope.row.bid_time)).toLocaleString()}}
            </template>
          </el-table-column>
          <el-table-column prop="title" label="项目">
          </el-table-column>
          <el-table-column label="状态">
            <template slot-scope="scope">
              <span>{{translationBidRequestState(scope.row.bid_request_state)}}</span>
            </template>
          </el-table-column>
          <el-table-column prop="available_amount" label="我的投资">
          </el-table-column>
          <el-table-column prop="name" label="操作">
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination style="margin: 15px;" background @size-change="handleSizeChangeBid" @current-change="handleCurrentChangeBid"
                       :current-page="bidQueryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="bidQueryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                       :total="bidQueryParams.total">
        </el-pagination>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";

    export default {
        name: "AccountManagement",
        data:function () {
            return{
                //这是保存统计数据
                statistics:{
                    //累计收益
                    membersInterestSum:null,
                    //账户余额
                    usableAmount:null,
                    //代收本金
                    unReceivePrincipal:null,
                    //代收利息
                    unReceiveInterest:null,
                    //授信额度
                    borrowLimit:null
                },
                //这是回款记录相关 start
                returnDetailList:[],
                returnDetailLoading:false,
                returnDetailQueryParams:{
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是回款记录相关 end
                //这是投资记录相关 start
                bidList:[],
                bidLoading:false,
                bidQueryParams:{
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                }
                //这是投资记录相关 end
            }
        },
        methods:{
            //根据id查单个所有
            search: function() {

            },
            //.......................这是查询 回款记录相关 start......................//
            searchReturnDetail:function(){
                let url = this.axios.urls.BORROWING_RETURNDETAIL_QUERYMEMBERS;
                let params = this.returnDetailQueryParams;
                //查询动画
                this.returnDetailLoading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.returnDetailList = response.data.data
                    this.returnDetailQueryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.returnDetailLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //一页的数量发送变化的时候调用此方法
            handleSizeChangeReturnDetail: function(rwos) {
                this.returnDetailQueryParams.page = 1;
                this.returnDetailQueryParams.rows = rwos;
                this.searchReturnDetail();
            },
            //当前页面发送变化的时候调用
            handleCurrentChangeReturnDetail: function(page) {
                this.returnDetailQueryParams.page = page;
                this.searchReturnDetail();
            },
            //.......................这是查询 回款记录相关 end......................//
            //.......................这是查询 投资记录相关 start......................//
            searchBid:function(){
                let url = this.axios.urls.BORROWING_BID_INVESTEMT_MEMBERS_BID_QUERY;
                let params = this.bidQueryParams;
                //查询动画
                this.bidLoading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.bidList = response.data.data
                    this.bidQueryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.bidLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });

            },
            //一页的数量发送变化的时候调用此方法
            handleSizeChangeBid: function(rwos) {
                this.bidQueryParams.page = 1;
                this.bidQueryParams.rows = rwos;
                this.searchBid();
            },
            //当前页面发送变化的时候调用
            handleCurrentChangeBid: function(page) {
                this.bidQueryParams.page = page;
                this.searchBid();
            },
            //这是状态翻译
            translationBidRequestState(state){
                if(state == 2){
                    return "投标中";
                }else if(state == 4 || state == 3){
                    return "回款中";
                }else {
                    return "已结束";
                }
            }

            //.......................这是查询 投资记录相关 end......................//

        },
        created() {
            commonUtils.init(this);
            //加载用户统计信息
            let url = this.axios.urls.MEMBERS_ACCOUNT_MY_HOME_STATISTICS;
            commonUtils.doAjaxGet(url, {}, (data)=>{
                this.statistics = data.data;
                console.log(this.statistics)
            })
            //加载汇款记录
            this.searchReturnDetail();
            //加载投资记录
            this.searchBid();
        }

    }
</script>

<style scoped>

</style>

