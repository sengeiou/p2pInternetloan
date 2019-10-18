export default{
	setResturantNameAsync:function(context,payload){
		//前全是action还是提交的mutation
		//mutations中的两个参数
		//type：事件类型
		//payload:载荷
		setTimeout(function(){
			context.commit('setResturantName',payload);
		},3000);
	},
	setResturantNameAsyncAjax:function(context,payload){
		let _this=payload._this;
		let url=_this.axios.urls.VUEX_DEMO;
		_this.axios.post(url,{
			resturantName:payload.resturantName
		}).then(resp=>{
			payload.resturantName=resp.data.msg;
			//提价mutation
			context.commit('setResturantName',payload);
		}).catch(resp=>{

		});
	},

	//user的set方法
	setUserAsync:function(context,payload){
		context.commit('setUser',payload);
	},
	//
	setSalChanceAsync:function(context,payload){
		context.commit('setSalChance',payload);
	},
	setDictAsync:function(context,payload){
		context.commit('setDict',payload);
	},
	setUserTimeAsync:function(context,payload){
		context.commit('setUserTime',payload);
	}
}
