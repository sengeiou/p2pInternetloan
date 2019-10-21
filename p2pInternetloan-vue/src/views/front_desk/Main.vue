<template>
  <div>
    <div class="template-tabs">
      <el-tabs  v-model="activeIndex" @tab-click="tabClick" style="line-height: 68px;background-color: rgb(248, 248, 248)">
        <el-tab-pane label="" name=""></el-tab-pane>
        <el-tab-pane label="首页" name="/Home"></el-tab-pane>
        <el-tab-pane label="我要投资" name="/Investment"></el-tab-pane>
        <el-tab-pane label="我要借贷" name="/Loan"></el-tab-pane>
        <el-tab-pane label="个人中心" name="/PersonalCenterMain"></el-tab-pane>
        <el-tab-pane label="关于我们" name="/AboutUs"></el-tab-pane>
      </el-tabs>
    </div>
    <el-main class="main-center">
      <router-view></router-view>
    </el-main>
  </div>
</template>

<script>
    export default {
        name: "Main",
        data:function(){
          return {
              activeIndex:null
          }
        },
        methods:{
            tabClick (tab){
                let path = this.activeIndex;
                this.$router.push({ path: path });
            }
        },
        created() {
            this.$router.push({ path: "/Home" });
        }
    }
</script>

<style scoped>
  .template-tabs{
    width:100%;
  }

  .main-container {
    height: 100%;
    width: 100%;
    box-sizing: border-box;
  }

  .main-aside-collapsed {
    /* 在CSS中，通过对某一样式声明! important ，可以更改默认的CSS样式优先级规则，使该条样式属性声明具有最高优先级 */
    width: 64px !important;
    height: 100%;
    background-color: #334157;
    margin: 0px;
  }

  .main-aside {
    width: 200px !important;
    height: 100%;
    background-color: #334157;
    margin: 0px;
  }

  .main-header,
  .main-center {
    padding: 0px;
    border-left: 2px solid #333;
  }

  .el-icon-close:before{
    display: none;
  }

</style>
