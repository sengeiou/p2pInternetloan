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
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	//实现折叠效果
	//点击TopNav折叠LeftAside

	//注
	//1.父组件Main.vue
	//2.子组件TopNav.vue和LeftAside.vue

	//思路
	//1.点击TopNav  ->Main ->LeftAside
	//2.TopNav  ->Main:子组件到父组件，使用事件this.$emit()
	//3.Main->lLeftAside:父组件到子组件，使用prop


	// 导入组件
	import TopNav from '@/components/TopNav.vue'
	import LeftAside from '@/components/LeftAside.vue'

	// 导出模块
	export default {
		name:'BackstageMain',
		data:function(){
			return {
				collapsed:false,
          asideClass: 'main-aside',
          editableTabsValue: '',
          editableTabs: []
			};
		},
		methods:{
			openCollapsed:function(collapsed){
				this.collapsed=collapsed;
				console.log(this.collapsed);
			},
        // tab切换时，动态的切换路由
        tabClick(tab) {
            let path = this.activeIndex;
            this.$router.push({ path: path });
            this.$store.commit('set_role',"nopass");
        },
        tabRemove(targetName) {
			      //判断是否是首页
            if(targetName == '/BackstageHome'){
                this.$message({
                    showClose: true,
                    message: '首页不能关闭哦',
                    type: 'error'
                });
                return;
            }
            this.$store.commit('set_role',"nopass");
            // let tabs = this.editableTabs;
            this.$store.commit('delete_tabs', targetName);
            // 如果激活的关闭的tab就是激活的tab
            if (this.activeIndex === targetName) {
                // 设置当前激活的路由
                if (this.options && this.options.length >= 1) {
                    console.log(this.options[this.options.length - 1].route);
                    this.$store.commit('set_active_index', this.options[this.options.length - 1].route);
                    this.$router.push({ path: this.activeIndex });
                }
                else {
                    this.$router.push({ path: '/BackstageHome' });
                    this.activeIndex = '/BackstageHome';
                }
            }
        },
			//加载字典表
			initDict:function(){
				let dict_url=this.axios.urls.DICT_QUERYDICTNOTPAGE;
				this.axios.post(dict_url,{}).then(resp=>{
					this.$store.dispatch('setDictAsync',{
			  			dict:resp.data.rows
			  		});
				}).catch(resp=>{
					console.log(resp);
				})
			},
			setUserTime:function(){
				console.log("进入");
				let userTime=this.$store.getters.getUserTime;
				let len=userTime.length;
				let userId=[];
				for (let i=0;i<len;i++) {
					if(this.timeOut(userTime[i].orderItemDate)){
						userId.push(userTime[i].cid);
					}
				}
				console.log("最终结果")
				console.log(userId);
				//如歌存在流失客户将Id转为String
				if(userId.length>=1){
					let userIdString="";
					for (let i=0;i<userId.length;i++) {
						userIdString+=userId[i]+"-";
					}
					console.log(userIdString);
				}
			},
			timeOut:function(orderItemDate){
				//获取当前系统时间
				let date=new Date().getTime();
				orderItemDate+=1000;   //1000*60*60*24*60(60天)
				if(date>orderItemDate){
					//客户流失
					console.log("已流失");
					return true;
				}else{
					console.log("未流失");
					return false;
				}
			},
			initOrderItem:function(){
				let url=this.axios.urls.ORDERITEM_QUERYORDERITEMTIME;
				this.axios.post(url,{}).then(resp=>{
					console.log(resp.data.rows);
					this.$store.dispatch('setUserTimeAsync',{
			  			userTime:resp.data.rows
			  		});
			  		//设置24小时查询一次客户流失
//			  		window.setInterval(() => {
//					  setTimeout(this.setUserTime(), 0);
//					}, 1000*60*60*24);//1000*60*60*24
				}).catch(resp=>{

				})
			}
		},
		components:{
			TopNav,
			LeftAside
		},
		created:function(){
			//加载字典表
			this.initDict();
			//加载订单项查询
			this.initOrderItem();
			//查找流失用户
			//this.setUserTime();
		},
		computed:{
			showLeftAside:function(){
				return !this.collapsed?'main-aside':'main-aside-collapsed'
			},
        options() {
            return this.$store.state.options;
        },
        //动态设置及获取当前激活的tab页
        activeIndex: {
            get() {
                return this.$store.state.activeIndex;
            },
            set(val) {
                this.$store.commit('set_active_index', val);
            }
        }
		},
      watch:{
          '$route'(to) {
              let role=this.$store.state.role;
              let showName=this.$store.getters.getShowName
              let flag = false;//判断是否页面中是否已经存在该路由下的tab页
              //options记录当前页面中已存在的tab页
              for (let option of this.options) {
                  //用名称匹配，如果存在即将对应的tab页设置为active显示桌面前端
                  if (option.name === showName) {
                      flag = true;
                      this.$store.commit('set_active_index',  to.path);
                      break;
                  }
              }
              //如果不存在，则新增tab页，再将新增的tab页设置为active显示在桌面前端
              // if(role!='nopass'){}
              if(role=='pass'){
                  if (!flag) {
                      this.$store.commit('add_tabs', { route: to.path, name: showName});
                      this.$store.commit('set_active_index',  to.path);
                  }
              }
          }
      }
	};
</script>
<style type="text/css">
  .el-tabs--border-card>.el-tabs__content {
    padding: 0px;
  }
</style>

<style scoped>
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
