<template>
  <div  style="padding: 20px;background-color: #FFFFFF">

              <el-row>
                <el-col style="font-weight: bold;" :span="12">充值交易ID：
                  <el-input    v-model="queryParams.titleid" style="height: 0px; width: 58%;" placeholder="请输入内容" size="medium" clearable show-word-limit type="text"></el-input>
                </el-col>
                <el-col style="font-weight: bold;" :span="12" >充值交易时间：
                  <el-date-picker
                    v-model="queryParams.tradeTime"
                    type="daterange"
                    align="right"
                    unlink-panels
                    range-separator="To"
                    start-placeholder="Start date"
                    end-placeholder="End date"
                    :picker-options="pickerOptions2">
                  </el-date-picker>
                  <el-button type="primary" icon="el-icon-search" @click="onQuery">查询</el-button>
            </el-col>

          </el-row>

          <el-table :data="dictList"  height="520" :fit="true" :show-header="true" v-loading="loading">
            <el-table-column prop="id" label="ID" min-width="2">
            </el-table-column>

            <el-table-column prop="tradeCode" label="支付宝账号" min-width="4">
            </el-table-column>
            <el-table-column value-format="yyyy-MM-dd-HH-mm-ss" prop="tradeTime" label="交易时间" min-width="4">
            </el-table-column>1
            <el-table-column prop="amount" label="交易金额" min-width="4">
            </el-table-column>

          </el-table>
          <el-pagination style="margin: 15px;" background @size-change="handleSizeChangeDictItem" @current-change="handleCurrentChangeDictItem"
                         :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                         :total="queryParams.total">
          </el-pagination>
  </div>

</template>

<script>
  // import commonUtils from "../../../api/commonUtils";
  // import commonUtils

    import commonUtils from "../../../../api/commonUtils";

  export default {
        name: "transaction_record",
        data:function(){
              return {
                //将日期回显到表格上
                pickerOptions2: {
                  shortcuts: [{
                    text: 'Last week',
                    onClick(picker) {
                      const end = new Date();
                      const start = new Date();
                      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                      picker.$emit('pick', [start, end]);
                    }
                  }, {
                    text: 'Last month',
                    onClick(picker) {
                      const end = new Date();
                      const start = new Date();
                      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                      picker.$emit('pick', [start, end]);
                    }
                  }, {
                    text: 'Last 3 months',
                    onClick(picker) {
                      const end = new Date();
                      const start = new Date();
                      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                      picker.$emit('pick', [start, end]);
                    }
                  }]
                },
                value:"",
                //这是查询参数
                queryParams: {
                  //定义搜索维度
                  titleid: null,
                  tradeTime: null,
                  // 分页
                  page: 1,
                  rows: 10,
                  total: 0,

                },
                //这是表格是否占时加载动画
                loading: false,
                //这书数据对象（用于表格占时）
                dictList: [],
                //这是新值修改弹出
                dictDialogTitle: null,
                //这是新值和修改弹出是否占时
                dictDialogFormVisible: false,
                //这是文本框中文字说明的长度
                formLabelWidth: "100px",
                //绑定数据
                recordList: [{
                  titleid:null,
                  tradeTime:null,
                  name:null,
                  membersId: null,
                  tradeCode: null,
                  tradeTime: null,
                  amount: null
                }],
              }
        },
      methods:{

        onQuery() {
          this.queryParams.page = 1;
          this.search();

        },
        //这是搜索加展示数据的方法
        search: function() {
          let url = this.axios.urls.MEMBERS_MONEYRECHARGE_QUERYPAGER;
          let params = {
            id:this.queryParams.titleid,
            tradeTime:this.queryParams.tradeTime,
            page:this.queryParams.page,
            rows:this.queryParams.rows,
            total:this.queryParams.total
          }
          //这是处理开始时间和结束时间
          if(this.queryParams.tradeTime && this.queryParams.tradeTime.length > 1){
              params.tradeTimeStart = commonUtils.formatDate(this.queryParams.tradeTime[0], 'yyyy/MM/dd')
              params.tradeTimeEnd = commonUtils.formatDate(this.queryParams.tradeTime[1], 'yyyy/MM/dd')
          }
         if(params.titleid != 0) {
           //查询动画
           this.loading = true;
           //向后端请求数据
           this.axios.get(url, {params: params}).then(response => {
             this.dictList = response.data.data
             this.queryParams.total = response.data.total;
             //数据查询到了关闭查询动画
             this.loading = false;
           }).catch(function (error) {
             console.log(error);
           });
         }
      },
        //一页的数量发送变化的时候调用此方法
        handleSizeChangeDictItem: function(rwos) {
          this.queryParams.page = 1;
          this.queryParams.rows = rwos;
          this.search();
        },
        //当前页面发送变化的时候调用
        handleCurrentChangeDictItem: function(page) {
          this.queryParams.page = page;
          this.search();
        }
      },
      created() {
        this.onQuery();
      }
    }
</script>

<style scoped>

</style>
