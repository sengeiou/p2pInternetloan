import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

//导入相关你模块
import state from '@/store/state'
import actions from '@/store/actions'
import mutations from '@/store/mutations'
import getters from '@/store/getters'

import createVuexAlong from 'vuex-along'

//创建store实例,分别注册以上模块
const store = new Vuex.Store({
	state,
	getters,
	actions,
	mutations,
	plugins: [createVuexAlong()]
})




export default store