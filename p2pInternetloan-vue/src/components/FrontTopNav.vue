<template>
  <div>
    <el-row style="margin-top: 18px;height:30px">
      <el-col style="font-size: 12px;" :span="12">咨询热线：400-668-6698服务时间：9:00 - 18:00</el-col>
      <el-col style="font-size: 20px;text-align: right;" :span="12">
        <el-link style="bottom: 0px; font-weight: bold; margin-bottom: 0px; margin-left: 0px; margin-right: 20px;">免费注册</el-link>
        <el-link style="bottom: 0px; font-weight: bold; margin-left: 29px; margin-right: 10px;">登录</el-link>
      </el-col>
    </el-row>
    <el-form style="background-color: rgb(255, 255, 255);" label-width="100px">
      <el-row>
        <el-menu style="text-align: center;" mode="horizontal" background-color="#4993f4" router="true">
          <el-menu-item route="/Home" style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center; margin-left: 662px; margin-top: 0px;" index="3">首页</el-menu-item>
          <el-menu-item route="/Home"  style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="3~~e5921075-b63b-4d41-9479-f8041ec759bd">我要投资</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="3~~bd7a8c47-6197-4e03-9c5e-0d3214e9088c">我要借款</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="3~~ca12431e-acaf-41c5-8303-f8bd144cb5d8">个人中心</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="3~~12f633f8-b88b-4ee4-9220-854aa452ff97">安全保障</el-menu-item>
          <el-menu-item style="height: 86px; color: rgb(244, 237, 237); background-color: rgb(62, 143, 248); font-size: 18px; text-align: center;" index="3~~55103665-dd33-44f1-af87-f5cd6c3d87ee">关于我们</el-menu-item>
        </el-menu>
      </el-row>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "FrontTopNav",
        methods:{
            itemClick (tab){
                let path = this.activeIndex;
                this.$router.push({ path: path });
            }
        }
    }
</script>

<style scoped>

</style>
