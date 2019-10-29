<template>
  <el-tabs value="first" editable addable closable type="border-card">
    <el-tab-pane name="first" label="提现" closable lazy>
      <el-card style="font-weight: bold; font-size: 14px; line-height: 73px; max-height: 2000px; margin-top: 0px;" shadow="always">
        <div style="margin-top: 0px; margin-bottom: 0px;" font-size="30px">填写提现金额</div>
        <el-row>
          <el-col style="text-align: center; letter-spacing: 2px; line-height: 33px; text-indent: 0px; float: left; right: 143px; left: -200px; word-spacing: 0px;" :span="4">账户可用余额</el-col>
          <el-col style="max-height: 1px;" :span="8">
					<span>
					</span>
          </el-col>
        </el-row>
        <el-row style="line-height: 26px; margin-top: 39px; padding-top: 0px;">
          <el-col style="text-align: center; letter-spacing: 12px; line-height: 33px; word-spacing: 0px;" :span="4">提现金额</el-col>
          <el-col style="max-height: 1px;" :span="8">
            <el-input style="padding-bottom: 0px;" placeholder="请输入内容">
            </el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top: 43px;">
          <el-col style="line-height: 30px; text-align: center; letter-spacing: 6px;" :span="4">提现手续费</el-col>
          <el-col style="line-height: 0px; height: 44px;" :span="16">
            <el-input style="line-height: 0px; min-height: 57px; max-width: 50%; border-top-style: outset; height: 0px;" placeholder="请输入内容" size="medium">
            </el-input>
          </el-col>
        </el-row>
        <el-row style="margin-top: 29px;">
          <el-col style="text-align: justify;" :span="3" :push="6">
            <el-button type="danger">提现</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
    export default {
        name: "Withdraw_deposit"
    }
</script>

<style scoped>

</style>
