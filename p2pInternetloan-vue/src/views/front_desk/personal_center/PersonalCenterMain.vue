<template>
    <el-main> 
      <el-row>
        <el-col :span="5">
          <el-menu class="el-menu-vertical-demo"  router="true">
            <el-menu-item  route="/test" index="/test">账户总览</el-menu-item>
            <el-menu-item index="3">投标项目</el-menu-item>
            <el-menu-item index="3">借贷项目</el-menu-item>
            <el-menu-item index="3">借款项目</el-menu-item>
            <el-menu-item index="3">资产管理</el-menu-item>
            <el-menu-item index="3">消息通知</el-menu-item>
          </el-menu>
        </el-col>
        <el-col :span="12">
          <router-view></router-view>
        </el-col>
      </el-row>
    </el-main>
</template>

<script>
    export default {
        name: "PersonalCenterMain"
    }
</script>

<style scoped>

</style>
