<template>
  <div>
    跳转成功
  </div>
</template>

<script>
  import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "review",
        data:{

        },
        watch: {
            // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
            '$route': 'getParams'
        },
        methods:{
            getParams:function(){
                // 取到路由带过来的参数
                var routerParams = this.$route.query
                // 将数据放在当前组件的数据内
                console.log(routerParams)
                console.log("传来的参数=="+routerParams)
            },
        }
    }
</script>

<style scoped>

</style>
