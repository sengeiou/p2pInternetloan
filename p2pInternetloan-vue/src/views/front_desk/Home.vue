<template>
    <div @click="doTest">
      测试首页成功
    </div>
</template>

<script>
    import Qs from 'qs'

    export default {
        name: "Home",
        data:{
            treeNodeLst:null
        },
        methods:{
            doTest: function() {
                let url= this.axios.urls.SYSTEM_USER_PUT;
                var readyData=Qs.stringify({
                    name:'zs',
                    fav:1
                });

                this.axios.put(url, {
                    name: 'Henry',
                })
            }
        }

    }
</script>

<style scoped>

</style>
