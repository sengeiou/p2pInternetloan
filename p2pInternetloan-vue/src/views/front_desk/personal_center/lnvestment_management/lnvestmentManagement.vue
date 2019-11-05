<template>
  <div>
    <el-card header="我的投资记录">
      <el-row :gutter="25">
        <el-col :span="12">
          <el-card class="box-card" style="background-color: rgb(0, 186, 189);">
            <p>累计投资
              <i class="el-icon-wallet"></i>
            </p>
            <strong style="color: rgb(254, 38, 14);">{{statistics.membersAvailableAmountSum}}</strong>元</el-card>
        </el-col>
        <el-col :span="12">
          <el-card class="box-card" style="background-color: rgb(255, 215, 0);">
            <p>累计收益
              <i class="el-icon-info"></i>
            </p>
            <strong style="color: rgb(1, 170, 237);">{{statistics.membersInterestSum}}</strong>元</el-card>
        </el-col>
      </el-row>
      <el-row style="margin-top: 10px;">
        <el-col :span="12">
        </el-col>
        <el-col :span="12">
        </el-col>
      </el-row>
      <el-table style="margin-top: 10px;" :data="tableList" :fit="true" :show-header="true" v-loading="loading">
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
        name: "lnvestmentManagement",
        data:function(){
            return {
                //这是统计信息数据
                statistics:{
                    membersAvailableAmountSum:null,
                    membersInterestSum:null
                },
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
                    membersId:null,
                    applyTime:null,
                    applierName:null,
                    remark:null,
                    publishTime:null,
                    bidCount:null,
                    currentSum:null
                },
                //这是查询参数
                queryParams: {
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是表格是否占时加载动画
                loading:false,
                tableList:[]
            }
        },
        methods:{
            search(){
                let url = this.axios.urls.BORROWING_BID_INVESTEMT_MEMBERS_BID_QUERY;
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

        },
        created() {
            commonUtils.init(this);//加载用户当前的项目
            //获取当前用户的借口信息
            // let url = this.axios.urls.BORROWING_BIDREQUEST_GET_CURRENT_BID_REQUEST;
            // commonUtils.doAjaxGet(url, {}, (data)=>{
            //     bidRequest = data.data;
            // })
          //加载统计信息
            let url = this.axios.urls.BORROWING_BID_INVESTEMT_CALCULATION;
            commonUtils.doAjaxGet(url, {}, (data)=>{
                this.statistics  = data.data;
            })
            //初始化投资项目
            this.search();

        }
    }
</script>

<style scoped>

</style>
