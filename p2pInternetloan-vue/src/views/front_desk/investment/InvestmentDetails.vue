<template>
  <el-main>
    <el-card :header="bidRequest.title">
      <el-row>
        <el-col :span="18">
          <el-col>
            <el-col :span="6">
              <span>借款金额:</span>
              <div>
                <span style="font-size: 33px;">{{bidRequest.bidRequestAmount}}</span>
                <span>元</span>
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
          </el-col>

          <el-col style="top: 0px; margin-top: 20px;">
            <el-col :span="6">发标日期：{{bidRequest.publishTime}}</el-col>
            <el-col :span="6">还款方式：{{bidRequest.returnType}}</el-col>
            <el-col :span="6">保障方式：100%本息垫付</el-col>
          </el-col>
          <el-col style="top: 0px; margin-top: 20px;">
            <el-col :span="6">投标截止: {{bidRequest.disableDate}}</el-col>
            <el-col :span="6">借款类型: {{bidRequest.bidRequestType_dictText}}</el-col>
            <el-col :span="6">投资范围 : 50元~ 不限</el-col>
          </el-col>
          <el-col style="top: 0px; margin-top: 20px;">
            <el-col :span="6">借款说明: {{bidRequest.description}}</el-col>

          </el-col>
        </el-col>
        <el-col :span="6">
          <el-col style="color: rgb(153, 153, 153); text-indent: 0px; word-spacing: 68px;" :span="24">投标总数:
            <span style="color: rgb(30, 159, 255);">{{bidRequest.bidCount}}</span>
          </el-col>
          <el-col style="color: rgb(153, 153, 153); word-spacing: 70px; margin-top: 15px;" :span="24">还需金额:
            <span style="color: rgb(30, 159, 255);">{{bidRequest.bidRequestAmount - bidRequest.currentSum}}</span>
          </el-col>
          <el-col style="color: rgb(153, 153, 153); word-spacing: 51px; margin-top: 15px;">投标进度条</el-col>
          <el-col style="color: rgb(153, 153, 153); word-spacing: 51px; margin-top: 15px;">
            <el-progress :color="customColorMethod" :text-inside="true" :stroke-width="20" :percentage="bidRequest.currentSum / bidRequest.bidRequestAmount * 100" status="exception">
            </el-progress>
          </el-col>
          <el-col style="color: rgb(153, 153, 153); word-spacing: 70px; margin-top: 15px;" :span="24">
            <el-input style="width: 65%;" placeholder="投资金额" v-model="tenderParams.availableAmount">
            </el-input>
          </el-col>
          <el-col style="color: rgb(153, 153, 153); word-spacing: 70px; margin-top: 15px;" :span="24">
            <el-button style="width: 100%;" type="danger" @click="tender">马上投资</el-button>
          </el-col>
        </el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top: 25px;">
      <el-tabs value="first">
        <el-tab-pane name="first" label="投资记录">
          <el-table :data="bidList" :fit="true" :show-header="true">
            <el-table-column prop="membersName" label="投标人">
            </el-table-column>
            <el-table-column prop="availableAmount" label="投标金额">
            </el-table-column>
            <el-table-column prop="bidTime" label="投标时间">
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <el-pagination style="margin: 15px;" background @size-change="bidHandleSizeChange" @current-change="bidHandleCurrentChange"
                         :current-page="bidParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="bidParams.rows" layout="total, sizes, prev, pager, next, jumper"
                         :total="bidParams.total">
          </el-pagination>
        </el-tab-pane>
        <el-tab-pane name="second" label="还款列表">
          <el-table :data="tableList" :fit="true" :show-header="true">
            <el-table-column prop="id" label="列1">
            </el-table-column>
            <el-table-column prop="name" label="列2">
            </el-table-column>
          </el-table>
          <!-- 分页 -->
          <el-pagination style="margin: 15px;" background @size-change="refundDetailHandleSizeChange" @current-change="refundDetailHandleCurrentChange"
                         :current-page="refundDetailParams.page" :page-sizes="[5, 10, 15, 20]" :page-size="refundDetailParams.rows" layout="total, sizes, prev, pager, next, jumper"
                         :total="refundDetailParams.total">
          </el-pagination>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    <!--这是投标密码框展示-->

    <!--这是添加和修改弹出-->
    <el-dialog top="25vh" width="45%" title="交易密码" :visible.visible="pwdDialogFormVisible" @close="doCancel()">
      <el-form :model="tenderParams" :rules="tenderRules" ref="tenderForm">
        <el-form-item label="交易密码" prop="pwd" label-width="100">
          <el-input v-model="tenderParams.pwd" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="doCancel()">关闭</el-button>
        <el-button type="primary" @click="tenderSubmit">确 定</el-button>
      </div>
    </el-dialog>


  </el-main>
</template>

<script>
  import commonUtils from "../../../api/commonUtils";
    export default {
        name: "InvestmentDetails",
        data:function(){
            return {
                //这是借贷对象
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
                //这是投资记录想干属性 start
                bidParams:{
                    bidRequestId:null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                bidLoading:false,
                bidList:[],
                //这是投资记录想干属性 end
                //这是还款相关记录 start
                refundDetailParams:{
                    bidRequestId:null,
                    // 分页
                    page: 1,
                    rows: 10,
                    total: 0,
                },
                refundDetailLoading:false,
                //还款列表
                refundDetailList:[],
                //这是还款相关记录 end
                //用户投标 start
                tenderParams:{
                    pwd:null,
                    availableAmount:null,
                    bidRequestId:null
                },
                pwdDialogFormVisible: false,
                tenderRules:[
                ],
                //用户投标 end
                //进度条花里胡哨
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
            //进度条颜色控制
            customColorMethod(percentage){
                if (percentage < 30) {
                    return '#909399';
                } else if (percentage < 70) {
                    return '#e6a23c';
                } else {
                    return '#67c23a';
                }
            },
            //查投资记录
            searchBid(){
                let url = this.axios.urls.BORROWING_BID_QUERY;
                let params = this.bidParams;
                //查询动画
                this.bidLoading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.bidList = response.data.data
                    this.bidParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.bidLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            searchRefundDetail(){
                let url = this.axios.urls.BORROWING_REFUNDETAIL_QUERY;
                let params = this.refundDetailParams;
                //查询动画
                this.refundDetailLoading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.refundDetailList = response.data.data
                    this.refundDetailParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.refundDetailLoading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //////////////////////////////////////// 这是分页相关  ///////////////////////////////////////////////////////
            //一页的数量发送变化的时候调用此方法
            bidHandleSizeChange: function(rwos) {
                this.bidParams.page = 1;
                this.bidParams.rows = rwos;
                this.searchBid();
            },
            //当前页面发送变化的时候调用
            bidHandleCurrentChange: function(page) {
                this.bidParams.page = page;
                this.searchBid();
            },
            //一页的数量发送变化的时候调用此方法
            refundDetailHandleSizeChange: function(rwos) {
                this.refundDetailParams.page = 1;
                this.refundDetailParams.rows = rwos;
                this.searchRefundDetail();
            },
            //当前页面发送变化的时候调用
            refundDetailHandleCurrentChange: function(page) {
                this.refundDetailParams.page = page;
                this.searchRefundDetail();
            },
            //这是用户投标
            tender(){
                //弹出密码框
                this.pwdDialogFormVisible = true;
            },
            //刷新借贷数据
            refreshBidRequest(){
                let url = this.axios.urls.BORROWING_BIDREQUEST_QUERY_BY_ID;
                commonUtils.doAjaxGet(url, {id:this.bidRequest.id}, (data) => {
                    this.bidRequest.bidCount = data.bidCount;
                    this.bidRequest.currentSum = data.currentSum;
                })
            },
            //这是提交密码
            tenderSubmit(){
                this.$refs['tenderForm'].validate((valid) => {
                    if(valid){
                        let url = this.axios.urls.BORROWING_BID_RENDER;
                        //提交
                        commonUtils.doAjaxPost(url, this.tenderParams, (data) =>{
                            // console.log(data);
                            console.log(data);
                            //判断状态
                            if(data.code == 0){
                                this.$message({
                                    message: "投标成功",
                                    type: 'warning'
                                });
                                //刷新投标数据
                                this.searchBid();
                                //刷新数据
                                this.refreshBidRequest();
                                //关闭弹出
                                this.doCancel();
                                //清空金额框输入
                                this.tenderParams.availableAmount = null;
                            }

                        }, true)
                    }
                });
            },
            doCancel(){
                this.tenderParams.pwd = null;
                this.pwdDialogFormVisible = false;
                //这是清空表单验证
                this.$refs['tenderForm'].resetFields();
            }
        },
        created() {
            //将页面插入的值放入到 bidRequest 中
            commonUtils.init(this);
            //获取vuex中的参数
            commonUtils.echo(this.$store.getters.getParamObj, "bidRequest");
            //借贷id加载
            this.bidParams.bidRequestId = this.bidRequest.id;
            this.refundDetailParams.bidRequestId = this.bidRequest.id;
            //投标对象id
            this.tenderParams.bidRequestId = this.bidRequest.id;
            //初始化查询
            this.searchBid();
            // this.searchRefundDetail();
        }
    }
</script>

<style scoped>

</style>
