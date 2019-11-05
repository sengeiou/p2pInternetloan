<template>
    <el-main>
      <el-row>
        <el-col :span="12">
          <el-input placeholder="请输入充值金额" v-model="formeAmount.amount">
          </el-input>
        </el-col>
        <el-col :span="12">
          <el-button style="margin-left: 20px;" type="success" @click="submit">充值</el-button>
        </el-col>
      </el-row>
    </el-main>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "Recharge",
        data:function(){
            return {
                //这是用户充值
                formeAmount:{
                    amount:null
                }
            }
        },
        methods:{
            submit(){
                //请求充值方法进行充值
                let url = this.axios.urls.ASSETS_MONEYRECHARGE_RECHARGE;
                commonUtils.doAjaxPost(url, this.formeAmount, (data) => {
                  this.formeAmount.amount = null;
                })
            }
        },
        created() {
            commonUtils.init(this);
        }
    }
</script>

<style scoped>

</style>
