<template>
	<el-menu class="el-menu-demo" mode="horizontal" background-color="#334157" text-color="#fff" active-text-color="#fff">
		<el-button class="buttonimg">
			<img class="showimg" :src="collapsed?imgshow:imgsq" @click="doToggle()">
		</el-button>
		<el-submenu index="2" class="submenu">
			<template slot="title">{{userName}}</template>
			<el-menu-item @click="exit()" index="2-3">退出</el-menu-item>
		</el-submenu>
	</el-menu>
</template>

<script>
	export default {
		name: 'TopNav',
		data: function() {
			return {
				collapsed: false,
				imgshow: require('../assets/img/show.png'),
				imgsq: require('../assets/img/sq.png'),
				userName:null
			};
		},
		methods: {
			doToggle: function() {
				this.collapsed=!this.collapsed;
				this.$emit('topnav-collapsed',this.collapsed);
			},
			exit: function() {
				this.$confirm('确认退出当前系统嘛?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					this.$message({
						type: 'success',
						message: '注销成功!'
					});
				/*	window.clearVuexAlong();*/
					//清空store缓存
//					this.$store.commit('setJwt',{
//						jwt:null
//					});
					this.$router.push({
						path:'/BackstageLogin'
					});
				}).catch(() => {
					this.$message({
						type: 'info',
						message: '已取消删除'
					});
				});
			},
			getUser:function(){
				let user=this.$store.getters.getUser;
				this.userName=user.userRealName;
				console.log("---------------");
			}
		},
		created:function(){
			this.getUser();
		}
	}
</script>

<style scoped>
	.el-menu-vertical-demo:not(.el-menu--collapse) {
		border: none;
	}

	.submenu {
		float: right;
	}

	.buttonimg {
		height: 60px;
		background-color: transparent;
		border: none;
	}

	.showimg {
		width: 26px;
		height: 26px;
		position: absolute;
		top: 17px;
		left: 17px;
	}

	.showimg:active {
		border: none;
	}
</style>
