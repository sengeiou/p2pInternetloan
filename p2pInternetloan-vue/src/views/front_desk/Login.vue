<template>

  <el-main>
    <el-card class="box-card box-login">
      <div class="clearfix" slot="header">
        <span>用户登录</span>
        <div style="float: right; padding: 3px 0">还没账号?请
          <el-link type="primary" href="#/Register">注册</el-link>
        </div>
      </div>
      <el-form label-width="100px" style="width:40%;height: 300px;" >
        <el-form-item label="用户名" label-width="60px">
          <el-input placeholder="请输入内容">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="60px">
          <el-input placeholder="请输入内容">
          </el-input>
        </el-form-item>
        <el-form-item label="验证码" label-width="60px">
          <el-input placeholder="请输入内容">
          </el-input>
        </el-form-item>
        <el-form-item label-width="60px">
          <el-button style="width:100%;background: rgb(255, 120, 0);color: rgb(255, 255, 255);">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </el-main>
</template>

<script>
    export default {
        name: "Login"
    }
</script>

<style scoped>

</style>
