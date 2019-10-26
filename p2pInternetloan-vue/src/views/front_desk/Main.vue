<template>
  <div>
    <!--这是导航体条-->
    <FrontTopNav></FrontTopNav>
    <router-view></router-view>
  </div>
</template>

<script>
    //导入组件
    import FrontTopNav from "../../components/FrontTopNav";

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
        components:{
            FrontTopNav
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
