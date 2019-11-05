<template>
    <el-main>
      <el-row>
        <el-col :span="5" style="margin-right: 15px;">
          <el-menu class="el-menu-vertical-demo"  router="true">
            <el-menu-item  route="/AccountManagement" index="/AccountManagement">账户总览</el-menu-item>
            <el-menu-item route="/Authentication" index="Authentication">实名认证</el-menu-item>
            <el-menu-item route="/PersonalData" index="PersonalData">个人资料</el-menu-item>
            <el-menu-item index="/loanManagement">借款项目</el-menu-item>
            <el-menu-item index="/lnvestmentManagement">投资管理</el-menu-item>
            <el-submenu index="2">
              <span class="magical-drag-tmp-submenu-name" slot="title">资产管理</span>
              <el-menu-item index="/Recharge">用户充值</el-menu-item>
              <el-menu-item index="/userwithdrawal">用户提现</el-menu-item>
              <el-menu-item index="/transaction_record">充值记录</el-menu-item>
              <el-menu-item index="/withdrawalsRecord">提现记录</el-menu-item>
            </el-submenu>
          </el-menu>
        </el-col>

        <el-col :span="18">
          <router-view></router-view>
        </el-col>
      </el-row>
    </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "PersonalCenterMain",
        created() {
            commonUtils.init(this);
            //初始化跳转到 用户总览这个页
            commonUtils.toRouter("/AccountManagement");
        }
    }
</script>

<style scoped>

</style>
