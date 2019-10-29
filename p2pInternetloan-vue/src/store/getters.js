export default{
	getResturantName:(state)=>{
		 return state.resturantName;
	},
	getJwt:(state)=>{
		return state.jwt;
	},
	getTreeNodeLst:(state)=>{
		return state.treeNodeLst;
	},
	getUser:(state)=>{
		return state.user;
	},
	getSalChance:(state)=>{
		return state.salChance;
	},
	getDict:(state)=>{
		return state.dict;
	},
  getPermission:(state)=>{
    return state.permission;
  },
	getUserTime:(state)=>{
		return state.userTime;
	},
  getVerificationJwt:(state)=>{
    return state.verificationJwt;
  },
  getShowName:(state) => {
    return state.showName;
  },
  getOptions:(state) => {
    return state.options;
  },
  getRole:(state) =>{
    return state.role;
  }
}
