<template>
  <div>
    <el-row style="height:30px">
      <el-col style="font-size: 12px;" :span="12">咨询热线：400-668-6698服务时间：9:00 - 18:00</el-col>
      <el-col style="font-size: 20px;text-align: right;" :span="12">
        <el-link href="#/Register" style="bottom: 0px; font-weight: bold; margin-bottom: 0px; margin-left: 0px; margin-right: 20px;">免费注册</el-link>
        <el-link href="#/Login" style="bottom: 0px; font-weight: bold; margin-left: 29px; margin-right: 10px;">登录</el-link>
      </el-col>
    </el-row>
    <el-form style="background-color: rgb(255, 255, 255);" label-width="100px">
      <el-row>
        <el-menu style="text-align: center;" mode="horizontal" background-color="#4993f4" router="true">
          <el-menu-item route="/Home" style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center; margin-left: 662px; margin-top: 0px;" index="3">首页</el-menu-item>
          <el-menu-item route="/Investment"  style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="4">我要投资</el-menu-item>
          <el-menu-item route="/Loan" style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="5">我要借款</el-menu-item>
          <el-menu-item route="/PersonalCenterMain" style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="6">个人中心</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="7">安全保障</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="8">关于我们</el-menu-item>
        </el-menu>
      </el-row>
    </el-form>
  </div>
</template>

<script>
    import commonUtils from "../api/commonUtils";
    export default {
        name: "FrontTopNav",
        methods:{
            itemClick (tab){
                let path = this.activeIndex;
                this.$router.push({ path: path });
            }
        },
    }
</script>

<style scoped>

</style>
