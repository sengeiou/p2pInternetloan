<template>
	<el-menu router :default-active="$router.path" :unique-opened="true" class="el-menu-vertical-demo" background-color="#334157"
	 text-color="#fff" active-text-color="#ffd04b" :collapse="showLeftAside">
		<!-- <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b"> -->
		<div class="logobox">
			<img class="logoimg" src="../assets/img/logo.png" alt="">
		</div>
		<el-menu-item index="/DataDictionary">
			<i class="el-icon-s-home"></i>
			<span>首页</span>
		</el-menu-item>
		<el-submenu v-for="n in treeNodeLst" :key="'ley-'+n.id" :index="'index-'+n.id">
			<template slot="title">
				<i :class="n.icon"></i>
				<span>{{n.perName}}</span>
			</template>
			<el-menu-item v-for="n1 in n.childern" :key="'key-'+n1.id" @click="showName(n1.perName)" :index="n1.url">
        <i :class="n1.icon"></i>
        <span>{{n1.perName}}</span>
			</el-menu-item>
		</el-submenu>
	</el-menu>
</template>
<script>
	export default {
		name:"LefterAside",
		props:['leftCollapsed'],
		data:function(){
			return{
				treeNodeLst:[],
        collapsed: false,
          menus: [],
          editableTabsValue: '2',
          tabIndex: 2
			};
		}, methods:{
          showName(name){
              // 把菜单名称放进去，当成tab页的名称
              this.$store.commit('set_showName', name)
              this.$store.commit('set_role', "pass");
          }
      },
		computed:{
			showLeftAside:function(){
				return this.leftCollapsed;
			}
		},
		created:function(){
			let url= this.axios.urls.SYSTEM_TREENODE;
			this.axios.post(url,{}).then(resp=>{
			    console.log(resp.data);
				this.treeNodeLst=resp.data;
			}).catch(resp=>{
			});
			//设置登录最初的页面
			this.$router.push({
				path:'/BackstageHome'
			});
		}
	}
</script>
<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 240px;
    min-height: 400px;
  }

  .el-menu-vertical-demo:not(.el-menu--collapse) {
    border: none;
    text-align: left;
  }

  .el-menu-item-group__title {
    padding: 0px;
  }

  .el-menu-bg {
    background-color: #1f2d3d !important;
  }

  .el-menu {
    border: none;
  }

  .logobox {
    height: 40px;
    line-height: 40px;
    color: #9d9d9d;
    font-size: 20px;
    text-align: center;
    padding: 20px 0px;
  }

  .logoimg {
    height: 40px;
  }
</style>
