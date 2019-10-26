<template>
    <el-main>
      <el-row :gutter="9">
        <el-col :span="8">
          <el-card header="信用贷">
            <i style="color: rgb(250, 212, 0);">认证最高额度 5000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">借口流程</span>
              <ul>
                <li>填写基本资料</li>
                <li>身份认证</li>
                <li>材料认证</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card header="车易贷">
            <i style="color: rgb(250, 212, 0);">认证最高额度 10000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">申请条件</span>
              <ul>
                <li>填写基本资料</li>
                <li>身份认证</li>
                <li>材料认证</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card header="房易贷">
            <i style="color: rgb(250, 212, 0);">认证最高额度 10000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">申请条件</span>
              <ul>
                <li>填写基本资料</li>
                <li>身份认证</li>
                <li>材料认证</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-main>
</template>

<script>
    export default {
        name: "Loan"
    }
</script>

<style scoped>

  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
  .el-card__header{

  }
</style>
