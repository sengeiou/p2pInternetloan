<template>
    <el-main>
      <el-row :gutter="9">
        <el-col :span="8">
          <el-card header="信用贷" style="height: 306px;">
            <i style="color: rgb(250, 212, 0);">认证最高额度 5000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">申请流程</span>
              <ul>
                <li>申请前已经完成 身份及材料 认证</li>
                <li>填写申请借款信息</li>
                <li>等待审核....</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button @click="toLoanApplication('/CreditLoan')" style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card header="车易贷">
            <i style="color: rgb(250, 212, 0);">认证最高额度 10000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">申请条件</span>
              <ul>
                <li>申请前已经完成 身份及材料 认证</li>
                <li>填写申请借款信息</li>
                <li>填写抵押车辆信息</li>
                <li>等待审核....</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button @click="toLoanApplication('/CarLoan')" style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card header="房易贷">
            <i style="color: rgb(250, 212, 0);">认证最高额度 10000 ￥</i>
            <div style="margin-top: 9px;">
              <span style="color: rgb(0, 206, 209); ">申请条件</span>
              <ul>
                <li>申请前已经完成 身份及材料 认证</li>
                <li>填写申请借款信息</li>
                <li>填写抵押房产信息</li>
                <li>等待审核....</li>
              </ul>
            </div>
            <div style="text-align: center">
              <el-button @click="toLoanApplication('/HousingLoan')" style="color: rgb(255, 255, 255); background-color: rgb(1, 170, 237);" icon="el-icon-thumb">立即借贷</el-button>
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

</style>
