export default{
	//type：事件类型,可以理解为set方法用于赋值或者改变state中的信息;
	//payload:载荷。就是一个保存要传参数的容器
	setResturantName:(state,payload)=>{
		state.resturantName=payload.resturantName;
	},
	setJwt:(state,payload)=>{
		state.jwt=payload.jwt;
	},
	//用户的set方法
	setUser:(state,payload)=>{
		state.user=payload.user;
	},
	//
	setSalChance:(state,payload)=>{
		state.salChance=payload.salChance;
	},
	setDict:(state,payload)=>{
		state.dict=payload.dict;
	},
	setUserTime:(state,payload)=>{
		state.userTime=payload.userTime;
	},
  setVerificationJwt:(state,payload)=>{
    state.verificationJwt = payload.verificationJwt;
  },
  // 添加tabs
  add_tabs(state, data) {
    this.state.options.push(data);
  },
  // 删除tabs
  delete_tabs(state, route) {
    let index = 0;
    for (let option of state.options) {
      if (option.route === route) {
        break;
      }
      index++;
    }
    this.state.options.splice(index, 1);//删除options里面下标为Index的一个数
  },
  // 设置当前激活的tab
  set_active_index(state, index) {
    this.state.activeIndex = index;
  },
  //设置tab页显示标题
  set_showName(state,name){
    this.state.showName=name;
  },
  set_role(state,role){
    this.state.role=role;
  }
}
