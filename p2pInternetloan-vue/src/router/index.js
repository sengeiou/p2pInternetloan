import Vue from 'vue'
import Router from 'vue-router'




//VUex

////////////       这是前台的页面导入 （开始） /////////////////////////////////////////
import Main from "../views/front_desk/Main";
import Home from "../views/front_desk/Home";
//我要借款
import Loan from "../views/front_desk/loan/Loan";
//信用贷
import CreditLoan from "../views/front_desk/loan/CreditLoan";
//车易贷
import CarLoan from "../views/front_desk/loan/CarLoan";
//房易贷
import HousingLoan from "../views/front_desk/loan/HousingLoan";

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

//会员账户管理
// import AccountManngement from "../views/front_desk/AccountManngement"

import AccountManngement from "../views/front_desk/personal_center/account_management/AccountManagement";


////////////       这是前台的页面导入 （结束） /////////////////////////////////////////

////////////       这是个人中心的页面导入 (开始） /////////////////////////////////////////
import test from "../views/front_desk/personal_center/account_management/test";
////////////       这是个人中心的页面导入 （结束） /////////////////////////////////////////

////////////       这是后台的页面导入 （开始） /////////////////////////////////////////
import BackstageLogin from '@/views/backstage/BackstageLogin'
import BackstageHome from "@/views/backstage/BackstageHome";
import BackstageMain from "@/views/backstage/BackstageMain";
//系统管理页面
import UserManagement from "@/views/backstage/system_management/UserManagement";
import RoleManagement from "@/views/backstage/system_management/RoleManagement";
import MenuManagement from "@/views/backstage/system_management/MenuManagement";
//数据字段
import DataDictionary from "../views/backstage/system_management/DataDictionary";

//实名认证
import identification from "../views/backstage/member_management/identity_review/identification";

//管理认证
import management from "../views/backstage/member_management/management/management";

////////////       这是后台的页面导入 （结束） /////////////////////////////////////////



Vue.use(Router)

export default new Router({
  routes: [
    /////////////////////// 这是前台路由挂载 （开始） ///////////////////////
    {
      path: '/',
      name: 'Main',
      component: Main,
      children:[
        {
          path: '/Home',
          name: 'Home',
          component: Home
        //用户注册
        }, {
          path: '/Register',
          name: 'Register',
          component: Register
        },
        //用户登录
        {
          path: '/Login',
          name: 'Login',
          component: Login

        },{
          path: '/Loan',
          name: 'Loan',
          component: Loan
        },
        //信用贷
        {
          path: '/CreditLoan',
          name: 'CreditLoan',
          component: CreditLoan
        },
        //车易贷
        {
          path: '/CarLoan',
          name: 'CarLoan',
          component: CarLoan
        },
        //房易贷
        {
          path: '/HousingLoan',
          name: 'HousingLoan',
          component: HousingLoan
        },
        {
          path: '/Investment',
          name: 'Investment',
          component: Investment
        },{
          path: '/PersonalCenterMain',
          name: 'PersonalCenterMain',
          component: PersonalCenterMain,
          //这是个人中心子页面挂载
          children:[
            {
              path: '/test',
              name: 'test',
              component: test
            }
          ]
        },{
          path: '/AboutUs',
          name: 'AboutUs',
          component: AboutUs
        },{
          path: '/AccountManngement',
          name: 'AccountManngement',
          component: AccountManngement
        }
      ]
    },
    /////////////////////// 这是前台路由挂载 （结束） ///////////////////////
    /////////////////////// 这是后台路由挂载 （开始） ///////////////////////
     {
      path: '/ds',
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
        }, {
          path: '/DataDictionary',
          name: 'DataDictionary',
          component: DataDictionary
        }, {
          path: '/identification',
          name: 'identification',
          component: identification
        }, {
          path: '/management',
          name: 'management',
          component: management
        }
      ]
    }
    /////////////////////// 这是后台路由挂载 （结束） ///////////////////////
  ]
})
