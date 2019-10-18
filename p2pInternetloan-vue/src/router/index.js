import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Main from '@/views/Main'

//首页
import Home from '@/views/Home'

//VUex
import Page1 from '@/components/Page1'
import Page2 from '@/components/Page2'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
     {
      path: '/Main',
      name: 'Main',
      component: Main,
      children:[
   		  {
		      path: '/Home',
		      name: 'Home',
		      component: Home
   		  },
   		  {
		      path: '/Page1',
		      name: 'Page1',
		      component: Page1
   		  },
   		  {
		      path: '/Page2',
		      name: 'Page2',
		      component: Page2
   		  }
      ]
    }
  ]
})
