<template>
  <div  style="padding: 20px;background-color: #FFFFFF">
      <div  style="margin-top: 0px; margin-bottom: 0px;" font-size="50px">用户提现</div>
      <el-row style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
        <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4" >账户可用余额</el-col>
        <el-col style="max-height: 1px;" :span="8">
          <el-input v-model="usableAmount" style="max-width: 123%; width: 29%;"  disabled="true">
          </el-input>
        </el-col>
      </el-row>
      <el-row style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
        <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4">提现金额</el-col>
        <el-col style="max-height: 1px;" :span="8">
          <el-input style="padding-bottom: 0px;" placeholder="请输入内容" v-model="formeAmount.amount">
          </el-input>
        </el-col>
      </el-row>
    <el-row style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
      <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4">手续费</el-col>
      <el-col style="max-height: 1px;" :span="8">
        <el-input style="padding-bottom: 0px;" placeholder="0" v-model="free" disabled="true" >
        </el-input>
      </el-col>
    </el-row>

    <el-row style="margin-top: 29px;">
        <el-col style="text-align: justify; width:100px;" :span="3" :push="6">
          <el-button type="danger" @click="submit()">提现</el-button>
        </el-col>
      </el-row>
  </div>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "userwithdrawal",
        data:function(){
            return{
                usableAmount:null,
                formeAmount:{
                    amount:null,
                    fee:null
                }
            }
        },
      methods:{
        submit(){
          let url = this.axios.urls.ASSETS_MONEYWITHDRAW_APPLICATION;
          //请求后台（添加申请信息）
          commonUtils.doAjaxPost(url, this.formeAmount, (data) => {
              this.$message({
                  message: '提现完成，请耐心等待后台管理员审核通过',
                  type: 'success'
              });
              this.formeAmount.amount = null;
          },true);
        }
      },
      computed:{
        //这是判断用户是否登录的计算属性
        free:function(){
            if(this.formeAmount.amount){
                return this.formeAmount.amount * 0.01;
            }
            return 0;
        }
      },
      created(){
        commonUtils.init(this);

        let url =  this.axios.urls.MEMBERS_ACCOUNT_CURRENT_MENBER_ACCOUNT;
        commonUtils.doAjaxGet(url, {},(data) => {
            this.usableAmount= data.usableAmount;
        })
      }
    }
</script>

<style scoped>

</style>
