<template>
  <div>
    <el-card class="box-card">
      <div class="clearfix" slot="header">
        <span>晚上好，tg_gpdt0139 喝一杯下午茶，让心情放松一下~</span>
        <span style="float: right; padding: 3px 0" type="text">上次登录时间： 2015-09-11 14:05:07</span>
      </div>
      <el-row :gutter="25">
        <el-col :span="8">
          <el-card class="box-card" style="background-color: rgb(73, 147, 244);">
            <p>账户总额
              <i class="el-icon-wallet"></i>
            </p>
            <strong>80</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>代收利息
              <i class="el-icon-info"></i>
            </p>
            <strong>80</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>代收本金
              <i class="el-icon-warning"></i>
            </p>
            <strong>80</strong>元</el-card>
        </el-col>
      </el-row>
      <el-row style="margin-top: 17px;" :gutter="25">
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>账户授信额度
              <i class="el-icon-user"></i>
            </p>
            <strong>80</strong>元</el-card>
        </el-col>
        <el-col :span="8">
          <el-card class="box-card" style="background-color:  rgb(73, 147, 244);">
            <p>累计收益
              <i class="el-icon-s-claim"></i>
            </p>
            <strong>80</strong>元</el-card>
        </el-col>
      </el-row>
    </el-card>
    <el-tabs style="padding-top: 0px; margin-top: 25px;" type="border-card">
      <el-tab-pane label="回款计划">回款计划</el-tab-pane>
      <el-tab-pane label="资金记录">资金记录</el-tab-pane>
      <el-tab-pane label="投资记录">投资记录</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
        name: "AccountManagement"
    }
</script>

<style scoped>

</style>

