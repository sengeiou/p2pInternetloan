import Vue from 'vue'
import Router from 'vue-router'



//VUex

////////////       这是前台的页面导入 （开始） /////////////////////////////////////////
import Main from "../views/front_desk/Main";
import Home from "../views/front_desk/Home";
//我要借款
import Loan from "../views/front_desk/loan/Loan";
//我要投资
import Investment from "../views/front_desk/investment/Investment";
//个人中心
import PersonalCenterMain from "../views/front_desk/personal_center/PersonalCenterMain";
//关于我们
import AboutUs from "../views/front_desk/AboutUs/AboutUs";


//注册
import Register from "../views/front_desk/Register";
//登录
import Login from "../views/front_desk/Login";

////////////       这是前台的页面导入 （结束） /////////////////////////////////////////



////////////       这是后台的页面导入 （开始） /////////////////////////////////////////
import BackstageLogin from '@/views/backstage/BackstageLogin'
import BackstageHome from "@/views/backstage/BackstageHome";
import BackstageMain from "@/views/backstage/BackstageMain";
//系统管理页面
import UserManagement from "@/views/backstage/system_management/UserManagement";
import RoleManagement from "@/views/backstage/system_management/RoleManagement";
import MenuManagement from "@/views/backstage/system_management/MenuManagement";

////////////       这是后台的页面导入 （结束） /////////////////////////////////////////



Vue.use(Router)

export default new Router({
  routes: [
    /////////////////////// 这是前台路由挂载 （开始） ///////////////////////
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/Login',
      name: 'Login',
      component: Login
    },
    {
      path: '/',
      name: 'Main',
      component: Main,
      children:[
        {
          path: '/Home',
          name: 'Home',
          component: Home
        },       {
          path: '/Loan',
          name: 'Loan',
          component: Loan
        },{
          path: '/Investment',
          name: 'Investment',
          component: Investment
        },{
          path: '/PersonalCenterMain',
          name: 'PersonalCenterMain',
          component: PersonalCenterMain
        },{
          path: '/AboutUs',
          name: 'AboutUs',
          component: AboutUs
        }
      ]
    },
    /////////////////////// 这是前台路由挂载 （结束） ///////////////////////
    /////////////////////// 这是后台路由挂载 （开始） ///////////////////////
     {
      path: '/BackstageMain',
      name: 'BackstageMain',
      component: BackstageMain,
      children:[
   		  {
		      path: '/BackstageHome',
		      name: 'BackstageHome',
		      component: BackstageHome
   		  }
   		  //// 系统页面挂载
        ,{
          path: '/UserManagement',
          name: 'UserManagement',
          component: UserManagement
        },{
          path: '/RoleManagement',
          name: 'RoleManagement',
          component: RoleManagement
        }, {
          path: '/MenuManagement',
          name: 'MenuManagement',
          component: MenuManagement
        }
      ]
    }
    /////////////////////// 这是后台路由挂载 （结束） ///////////////////////
  ]
})
