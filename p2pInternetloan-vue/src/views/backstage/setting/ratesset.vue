<template>
  <el-container class="main-container">
    <el-aside :class="showLeftAside" >
      <LeftAside :left-collapsed="collapsed"></LeftAside>
    </el-aside>
    <el-container>
      <el-header class="main-header">
        <TopNav @topnav-collapsed="openCollapsed"></TopNav>
      </el-header>
      <div class="template-tabs">
        <el-tabs v-model="activeIndex" type="border-card"  closable  @tab-click="tabClick" @tab-remove="tabRemove">
          <el-tab-pane label="首页" name="/BackstageHome" closable="false"></el-tab-pane>
          <el-tab-pane :key="item.name" v-for="(item, index) in options" :label="item.name" :name="item.route">
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-main class="main-center">
        <router-view>34567</router-view>
      </el-main>
    </el-container>
  </el-container>
</template>



<script>
    export default {
        name: "ratesset",
        data(){

        }
    }
</script>

<style scoped>

</style>
