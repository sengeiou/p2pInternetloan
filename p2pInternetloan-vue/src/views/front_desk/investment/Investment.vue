<template>
  <el-main>
    <el-card class="box-card" style="margin-top: 25px;">
      <div class="clearfix" slot="header">
        <span>快速筛选</span>
        <i style="float: right; padding: 3px 0" type="text">精选优质债权，网贷有风险，投资需谨慎</i>
      </div>
      <el-form :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-row>
          <el-col :span="6">
            <el-form-item label="项目类型" label-width="100px">
              <el-select @change="search" v-model="queryParams.bidRequestType" style="width: 100%;" value="1"  placeholder="请选择项目类型">
                <el-option label="所有"></el-option>
                <el-option
                  v-for="item in options.bidRequestType"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="年利率" label-width="100px">
              <el-select @change="search" v-model="queryParams.currentRate" style="width: 100%;" value="1"  placeholder="请选择项目类型">
                <el-option label="所有"></el-option>
                <el-option
                  v-for="item in options.annualInterestRate"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="期限" label-width="100px">
              <el-select @change="search" v-model="queryParams.monthesReturn" style="width: 100%;" value="1"  placeholder="请选择项目类型">
                <el-option label="所有"></el-option>
                <el-option
                  v-for="item in options.queryRepaymentPeriod"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="还款方式" label-width="100px">
              <el-select @change="search" v-model="queryParams.returnType" style="width: 100%;" value="1"  placeholder="请选择还款方式">
                <el-option label="所有"></el-option>
                <el-option
                  v-for="item in options.repayment"
                  :key="item.value"
                  :label="item.title"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目类型" label-width="100px">
              <el-button-group>
                <el-button type="danger" round @click="searchByStates('0,3')">全部</el-button>
                <el-button type="primary"  @click="searchByStates('0')">招标中</el-button>
                <el-button type="success" round @click="searchByStates('3')">已完成</el-button>
              </el-button-group>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 25px;">
      <div class="clearfix" slot="header">
        <span>项目列表</span>
      </div>
      <!-- 数据表格-->
      <el-table
        :data="tableList"
        style="width: 100%">

        <el-table-column class="column" prop="title" label="标题" width="200" align="center">
        </el-table-column>
        <el-table-column class="column" prop="bidRequestAmount" width="200" label="借款金额" align="center">
        </el-table-column>
        <el-table-column class="column" prop="currentRate_dictText" label="年利率" width="200" align="center">
        </el-table-column>
        <el-table-column class="column" prop="monthesReturn" label="借款期限/月" width="200" align="center">
        </el-table-column>
        <el-table-column class="column" prop="returnType_dictText" width="200" label="还款方式" align="center">
      </el-table-column>
        <el-table-column class="column" label="借款进度" align="center">
          <template slot-scope="scope">
            <el-progress  type="circle" width="100" :color="customColorMethod"  :percentage="scope.row.currentSum / scope.row.bidRequestAmount * 100">
            </el-progress>
          </template>
        </el-table-column>
        <el-table-column class="column" label="操作" align="center">
          <template slot-scope="scope">
            <el-button @click="" type="text" size="small"  @click="toDetails(scope.row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <el-pagination style="margin: 15px;" background @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                     :total="queryParams.total">
      </el-pagination>

    </el-card>
  </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "Investment",
        data:function(){
            return {
                options:{
                    bidRequestType:[],
                    annualInterestRate:[],
                    queryRepaymentPeriod:[],
                    repayment:[],

                },
                queryParams:{
                    bidRequestType:null,
                    currentRate:null,
                    monthesReturn:null,
                    returnType:null,
                    bidRequestStates:"0,3",
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                tableList:[],
                loading:false,
                customColor: '#409eff',
                customColors: [
                    {color: '#f56c6c', percentage: 20},
                    {color: '#e6a23c', percentage: 40},
                    {color: '#5cb87a', percentage: 60},
                    {color: '#1989fa', percentage: 80},
                    {color: '#6f7ad3', percentage: 100}
                ]
            }
        },
        methods:{
            //这是状态搜索
            searchByStates(states){
                this.queryParams.bidRequestStates = states;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.BORROWING_BIDREQUEST_QUERY;
                let params = this.queryParams;
                console.log(params);
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
            //这是跳入到对应的标详情页
            toDetails(row){
                //将参数放入到vuex中传到另一个页面
                this.$store.commit('setParamObj',{
                    paramObj: row
                });
                //跳转页面
                commonUtils.toRouter('/InvestmentDetails');
            },
            //进度条颜色控制
            customColorMethod(percentage){
                if (percentage < 30) {
                    return '#909399';
                } else if (percentage < 70) {
                    return '#e6a23c';
                } else {
                    return '#67c23a';
                }
            }
        },
        created() {
            commonUtils.init(this);
            //加载下拉框
            commonUtils.getDictSelect("bid_request_type", "options.bidRequestType")
            commonUtils.getDictSelect("annual_interest_rate", "options.annualInterestRate")
            commonUtils.getDictSelect("query_repayment_period", "options.queryRepaymentPeriod")
            commonUtils.getDictSelect("repayment", "options.repayment")
            this.search();
        }
    }
</script>

<style scoped>

</style>
