<template>
  <div  style="padding: 20px;background-color: #FFFFFF">

  <el-tabs   value="first" editable addable closable type="border-card">
    <el-tab-pane name="first" label="提现" closable lazy>
      <el-card style="font-weight: bold; font-size: 14px; line-height: 73px; max-height: 2000px; margin-top: 0px;" shadow="always">
        <div :date="value" style="margin-top: 0px; margin-bottom: 0px;" font-size="50px">用户提现</div>
        <el-row style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
          <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4" prop="usable_amount">账户可用余额</el-col>
          <el-col style="max-height: 1px;" :span="8">
            <el-input style="max-width: 123%; width: 29%;" disabled="true">
            </el-input>
          </el-col>
        </el-row>

        <el-row :model="txje" style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
          <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4" v-model="amount">提现金额</el-col>
          <el-col style="max-height: 1px;" :span="8">
            <el-input style="padding-bottom: 0px;" placeholder="请输入内容">
            </el-input>
          </el-col>
        </el-row>

        <el-row style="margin-top: 29px;">
          <el-col style="text-align: justify; width:100px;" :span="3" :push="6">
            <el-button type="danger" @click="submit()">提现</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-tab-pane>
  </el-tabs>

  </div>
</template>

<script>
    export default {
        name: "userwithdrawal",
        date:function(){
            return{
                value:{
                  usable_amount:null
                }
            }
        },
      methods:{
        //消息提示
        open11:function() {
          this.$message({
            message: '提现完成，请耐心等待后台管理员审核通过',
            type: 'success'
          });
        },

        submit(){
          let url = this.axios.urls.MEMBERS_ACCOUNT_QUERYBYID;
          let params = {
            amount:this.value.txje,
          }

          //查询动画
          this.loading = true;
          //向后端请求数据
          this.axios.get(url,{params:params}).then(response => {
            this.dictList = response.data.data
            this.queryParams.total = response.data.total;
            //数据查询到了关闭查询动画
            this.loading = false;
          }).catch(function(error) {
            console.log(error);
          });
          this.open11()
        }
      },
      created(){
          this.submit();
      }

    }
</script>

<style scoped>

</style>
