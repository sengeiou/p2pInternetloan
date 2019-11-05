// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import axios from '@/api/http' //vue项目对axios的全局配置
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)

import ElementUI from 'element-ui' //新添加1
import 'element-ui/lib/theme-chalk/index.css' //新添加2，避免后期打包样式不同，要放在import App from './App';之前

//引用font-awesome
import 'font-awesome/css/font-awesome.css'
//引用echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts;
// import packedAxios from "./axios";
// Vue.use(packedAxios); //使用this.$http代替封装好的axios

//开发环境下才会引入mockjs
// process.env.MOCK && require('@/mock')

//导入store实例
import store from './store'

import App from './App'
import router from './router'
//这是加载 element组件
Vue.use(ElementUI)
Vue.config.productionTip = false



//在main.js设置全局的请求次数，请求的间隙



/* eslint-disable no-new */
window.vm = new Vue({
	el: '#app',
	router,
	store,
	components: {
		App
	},
	template: '<App/>'
})

//这是登陆控制
router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    //这里判断用户是否登录，验证本地存储是否有token
    debugger
    console.log(window.vm.$store.getters.getJwt)
    if (!window.vm.$store.getters.getJwt) { // 判断当前的token是否存在
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      })
    } else {
      next()
    }
  } else {
    next() // 确保一定要调用 next()
  }
})

