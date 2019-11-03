
<template>
  <el-main  style="background-color: #FFFFFF;" >
    <el-form class="demo-form-inline" style="background:#FFFFFF;padding-top: 10px; height: 50px;padding-left: 10px;" :inline="true">
      <el-form-item label="借款标题">
        <el-input v-model="queryParams.title" placeholder="请输入借款标题">
        </el-input>
      </el-form-item>
      <el-form-item label="借款人">
        <el-input v-model="queryParams.applierName" placeholder="请输入借款人姓名">
        </el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select  v-model="queryParams.bidRequestState" style="width: 100%;" value="1" size="200px"  placeholder="更具状态查询">
          <el-option label="所有"></el-option>
          <el-option
            v-for="item in options.bidRequestState"
            :key="item.value"
            :label="item.title"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格-->
    <el-table :data="tableList"  height="360" :fit="true" :show-header="true" v-loading="loading">
      <el-table-column class="column" type="index" :index="indexMethod" label="#" width="20" align="center">
      </el-table-column>
      <el-table-column class="column" prop="title" label="标题" width="200" align="center">
      </el-table-column>
      <el-table-column class="column" prop="applierName" label="借款人" width="80" align="center">
      </el-table-column>
      <el-table-column class="column" prop="bidRequestAmount" label="贷款总额" align="center">
      </el-table-column>
      <el-table-column class="column" prop="currentRate_dictText" label="年化率" width="80" align="center">
      </el-table-column>
      <el-table-column class="column" prop="returnType_dictText" label="还款方式" align="center">
      </el-table-column>
      <el-table-column class="column" prop="applyTime" label="申请时间" align="center" width="200">
      </el-table-column>
      <el-table-column class="column" prop="bidRequestState_dictText" label="状态" align="center" width="100">
      </el-table-column>
      <el-table-column class="column" label="操作" align="center">
        <template slot-scope="scope">
          <el-button v-if="scope.row.bidRequestState == 2" @click="" type="text" size="small"  @click="review(scope.row)">审核借款人材料</el-button>
          <el-button v-if="scope.row.bidRequestState == 3" @click="" type="text" size="small"  @click="fullStandardReview(scope.row)">满标审核</el-button>
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
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "userManagement",
        data:function(){

            return {
                options:{
                    bidRequestState: []
                },
                //这是查询参数
                queryParams: {
                    //定义搜索维度
                    title: null,
                    applierName: null,
                    bidRequestState:null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                //这是表格是否占时加载动画
                loading:false,
                //表格数据对象
                tableList:[],
            }
        },
        methods:{
            onQuery() {
                this.queryParams.page = 1;
                this.search();
            },
            //这是搜索的方法
            search: function() {
                let url = this.axios.urls.BORROWING_BIDREQUEST_QUERY;
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
            review(row){
                //将参数放入到vuex中传到另一个页面
                this.$store.commit('setParamObj',{
                    paramObj: row
                });
                //跳转页面
                commonUtils.toRouter("/creditLoanReview")
            },
            //这是满标审核的时候调用
            fullStandardReview(row){
                //将参数放入到vuex中传到另一个页面
                this.$store.commit('setParamObj',{
                    paramObj: row
                });
                //跳转页面
                commonUtils.toRouter("/fullStandardReview")
            }
        },
        created() {
            commonUtils.init(this);
            commonUtils.getDictSelect("bid_request_state", "options.bidRequestState")
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
