<template>
  <!--用户提现管理-->
  <el-main style="background-color: #FFFFFF;">
    <el-row>
      <el-col style="font-size: 19px; font-weight: lighter;" :span="8">状态：
        <el-select id="xlk" v-model="queryParams.state" filterable placeholder="请选择">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.title"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-col>
      <el-col style="font-size: 18px; font-weight: 400;" :span="16">申请时间：
        <el-date-picker
          v-model="queryParams.apply_time"
          type="daterange"
          align="right"
          unlink-panels
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
          value-format="yyyy-MM-dd-HH-mm-ss"
          :picker-options="pickerOptions2">
        </el-date-picker>
        <el-button style="left: 0px; right: 0px; margin-left: 15px;" type="success" round icon="el-icon-document-checked" @click="onQuery">查询</el-button>
      </el-col>
    </el-row>
    <!-- 数据表格-->
    <el-table :data="tableList"  height="360" :fit="true" :show-header="true" v-loading="loading" style="margin-top: 25px;">
      <el-table-column  prop="id" label="ID" width="100px">
      </el-table-column>
      <el-table-column value-format="yyyy-MM-dd-HH-mm-ss" prop="apply_time" label="申请时间" width="150px">
      </el-table-column>
      <el-table-column prop="amount" label="提现金额（元）">
      </el-table-column>
      <el-table-column prop="fee" label="手续费金额（元）">
      </el-table-column>
      <el-table-column prop="state_dictText" label="状态">
      </el-table-column>
      <el-table-column label="操作" >
        <template slot-scope="scope"  v-if="scope.row.state == 2">
          <el-button size="small" type="success" round @click="examinationPassed(scope.row)">审核通过</el-button>
          <el-button size="small" type="danger" round @click="auditFailure(scope.row)" >审核不通过</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination style="margin: 15px;" background @size-change="handleSizeChangeDictItem" @current-change="handleCurrentChangeDictItem"
                   :current-page="queryParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="queryParams.rows" layout="total, sizes, prev, pager, next, jumper"
                   :total="queryParams.total">
    </el-pagination>
  </el-main>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";

  export default {
    name: "cashWithdrawalManagement",
    data(){
      return {
        tableList:[],
        //将日期回显到表格上
        pickerOptions2: {
          shortcuts: [{
            text: '上周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '上个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最后3个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value:"",
        //这是下拉框 数据动态绑定
        options: [],
        value:"",
        //这是查询阐述
        queryParams: {
          //定义搜索维度
          id: null,
          apply_time: null,
          state:null,
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
          id:null,
          apply_time:null,
          amount: null,
          fee: null,
          trade_code: null,
          remark: null,
          state:null
        }],

      }
    },
    methods:{
      onQuery(){
        this.queryParams.page = 1;
        this.search();
      },
      //这是审核通过调用的方法
      examinationPassed(row){
          let params = {
              id:row.id,
              state:0,
              amount:row.amount,
              fee:row.fee
          }


          //这是修改状态的请求地址
          let url = this.axios.urls.ASSETS_MONEYWITHDRAW_UPDATESTATE;
          commonUtils.doAjaxPost(url, params, (data)=>{
              this.onQuery();
          })
      },
      //这是审核失败调用的方法
      auditFailure(row){
          let params = {
              id:row.id,
              state:1
          }
          //这是修改状态的请求地址
          let url = this.axios.urls.ASSETS_MONEYWITHDRAW_UPDATESTATE;
          commonUtils.doAjaxPost(url, params, (data)=>{
              this.onQuery();
          })
      },
      //搜索的方法
      search: function() {
        let url = this.axios.urls.ASSETS_MONEYWITHDRAW_QUERYPAGER;
        debugger
        let params = {
          state:this.queryParams.state,
          page:this.queryParams.page,
          rows:this.queryParams.rows,
          total:this.queryParams.total
        }
        //时间区间处理
        if(this.queryParams.apply_time && this.queryParams.apply_time.length > 1){
            params.applyTimeStart = this.queryParams.apply_time[0];
            params.applyTimeEnd = this.queryParams.apply_time[1];
        }
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
        commonUtils.init(this);
        commonUtils.getDictSelect("money_withdraw_state", "options")
        this.onQuery();
    }
  }
</script>

<style scoped>

</style>
