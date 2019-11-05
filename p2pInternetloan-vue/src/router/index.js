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
//投资详情页
import InvestmentDetails from "../views/front_desk/investment/InvestmentDetails";

//个人中心
import PersonalCenterMain from "../views/front_desk/personal_center/PersonalCenterMain";
import AccountManagement from "../views/front_desk/personal_center/account_management/AccountManagement";
//个人资料
import PersonalData from "../views/front_desk/personal_center/account_management/PersonalData";
//实名认证
import Authentication from "../views/front_desk/personal_center/account_management/Authentication";
//关于我们
import AboutUs from "../views/front_desk/AboutUs/AboutUs";


//注册
import Register from "../views/front_desk/Register";
//登录
import Login from "../views/front_desk/Login";



////////////       这是前台的页面导入 （结束） /////////////////////////////////////////

////////////       这是个人中心的页面导入 (开始） /////////////////////////////////////////
import test from "../views/front_desk/personal_center/account_management/test";
////////////        用户提现充值记录   ///////////////////////////
import transaction_record from "../views/front_desk/personal_center/asset_management/transaction_record";
// ////////////          用户提现      ///////////////////////////
import money_withdraw from "../views/front_desk/personal_center/asset_management/money_withdraw";
//借款管理
import loanManagement from "../views/front_desk/personal_center/loan_management/loanManagement";

//投资管理
import lnvestmentManagement   from "../views/front_desk/personal_center/lnvestment_management/lnvestmentManagement.vue";
//这是用户体现
import userwithdrawal from "../views/front_desk/personal_center/asset_management/userwithdrawal";
//这是用户充值
import Recharge from "../views/front_desk/personal_center/asset_management/Recharge";

//这是用户提现记录
import withdrawalsRecord from "../views/front_desk/personal_center/asset_management/withdrawalsRecord";
////////////       这是个人中心的页面导入 （结束） /////////////////////////////////////////

////////////       这是后台的页面导入 （开始） /////////////////////////////////////////
import BackstageLogin from '@/views/backstage/BackstageLogin'
import BackstageHome from "@/views/backstage/BackstageHome";
import BackstageMain from "@/views/backstage/BackstageMain";
//系统管理页面
import UserManagement from "@/views/backstage/system_management/UserManagement";
import RoleManagement from "@/views/backstage/system_management/RoleManagement";
import MenuManagement from "@/views/backstage/system_management/MenuManagement";
//设置管理
import setting  from  "@/views/backstage/setting/setting";
import ratesset from "../views/backstage/setting/ratesset"
//用户提现管理
import cashWithdrawalManagement from "../views/backstage/financial_management/cash_withdrawal_management/cashWithdrawalManagement";
//数据字段
import DataDictionary from "../views/backstage/system_management/DataDictionary";
//实名认证
import identification from "../views/backstage/member_management/identity_review/identification";
//管理认证
import management from "../views/backstage/member_management/management/management";
//材料认证
import Materialcertification from "../views/backstage/member_management/material_review/Materialcertification";

////////////////////// 这是业务模块 start ///////////////////////////////////////////
//这是信用贷
import creditLoanManagement from "../views/backstage/business_management/credit_loan_management/creditLoanManagement";
//这是申请审核
import creditLoanReview from "../views/backstage/business_management/credit_loan_management/creditLoanReview";
//这是满标审核
import fullStandardReview from "../views/backstage/business_management/credit_loan_management/fullStandardReview";
//这是车贷
import carLoanManagement from "../views/backstage/business_management/car_loan_management/carLoanManagement";

////////////////////// 这是业务模块 end ///////////////////////////////////////////


//用户登录
// import BackstageLogin from "../views/backstage/BackstageLogin";
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
          component: Home,
          meta: { requiresAuth: true } // 添加表示需要验证
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

        },{ //这是借贷页面
          path: '/Loan',
          name: 'Loan',
          component: Loan
        },
        //信用贷
        {
          path: '/CreditLoan',
          name: 'CreditLoan',
          component: CreditLoan,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        //车易贷
        {
          path: '/CarLoan',
          name: 'CarLoan',
          component: CarLoan,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        //房易贷
        {
          path: '/HousingLoan',
          name: 'HousingLoan',
          component: HousingLoan,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        {
          path: '/Investment',
          name: 'Investment',
          component: Investment
        }, {
          path: '/InvestmentDetails',
          name: 'InvestmentDetails',
          component: InvestmentDetails,
          meta: { requiresAuth: true } // 添加表示需要验证
        },{
          path: '/PersonalCenterMain',
          name: 'PersonalCenterMain',
          component: PersonalCenterMain,
          meta: { requiresAuth: true }, // 添加表示需要验证
          //这是个人中心子页面挂载
          children:[
            {
              path: '/AccountManagement',
              name: 'AccountManagement',
              component: AccountManagement,
              meta: { requiresAuth: true } // 添加表示需要验证
            }, {
              path: '/PersonalData',
              name: 'PersonalData',
              component: PersonalData,
              meta: { requiresAuth: true } // 添加表示需要验证
            },{
              path: '/Authentication',
              name: 'Authentication',
              component: Authentication,
              meta: { requiresAuth: true } // 添加表示需要验证
            },
            //借款管理
            {
              path: '/loanManagement',
              name: 'loanManagement',
              component: loanManagement,
              meta: { requiresAuth: true } // 添加表示需要验证
            },
            //投资管理
            {
              path: '/lnvestmentManagement',
              name: 'lnvestmentManagement',
              component: lnvestmentManagement,
              meta: { requiresAuth: true } // 添加表示需要验证
            },{
              //用户提现充值记录
              path: '/transaction_record',
              name: 'transaction_record',
              component: transaction_record,
              meta: { requiresAuth: true } // 添加表示需要验证
            },
            {
              path: '/userwithdrawal',
              name: 'userwithdrawal',
              component: userwithdrawal,
              meta: { requiresAuth: true } // 添加表示需要验证
            },
            //这是客户充值
            {
              path: '/Recharge',
              name: 'Recharge',
              component: Recharge,
              meta: { requiresAuth: true } // 添加表示需要验证
            },
            {
              path: '/withdrawalsRecord',
              name: 'withdrawalsRecord',
              component: withdrawalsRecord,
              meta: { requiresAuth: true } // 添加表示需要验证
            }
          ]
        },{
          path: '/AboutUs',
          name: 'AboutUs',
          component: AboutUs
        },{
          path: '/money_withdraw',
          name: 'money_withdraw',
          component: money_withdraw
        }
      ]
    },
    /////////////////////// 这是前台路由挂载 （结束） ///////////////////////
    /////////////////////// 这是后台路由挂载 （开始） ///////////////////////
    {
      path: '/BackstageLogin',
      name: 'BackstageLogin',
      component: BackstageLogin
    },
    {
      path: '/BackstageMain',
      name: 'BackstageMain',
      component: BackstageMain,
      meta: { requiresAuth: true }, // 添加表示需要验证
      children:[
   		  {
		      path: '/BackstageHome',
		      name: 'BackstageHome',
		      component: BackstageHome,
          meta: { requiresAuth: true } // 添加表示需要验证
   		  }
   		  //// 系统页面挂载
        ,{
          path: '/UserManagement',
          name: 'UserManagement',
          component: UserManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        },{
          path: '/RoleManagement',
          name: 'RoleManagement',
          component: RoleManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        }, {
          path: '/MenuManagement',
          name: 'MenuManagement',
          component: MenuManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        }, {
          path: '/DataDictionary',
          name: 'DataDictionary',
          component: DataDictionary,
          meta: { requiresAuth: true } // 添加表示需要验证
        }, {
          path: '/identification',
          name: 'identification',
          component: identification,
          meta: { requiresAuth: true } // 添加表示需要验证
        }, {
          path: '/management',
          name: 'management',
          component: management,
          meta: { requiresAuth: true } // 添加表示需要验证
        },{
          path: '/setting',
          name: 'setting',
          component: setting,
          meta: { requiresAuth: true }, // 添加表示需要验证
          children:[{
              path: '/ratesset',
              name: 'ratesset',
              component: ratesset,
            meta: { requiresAuth: true } // 添加表示需要验证
          }]
        },{
          path: '/cashWithdrawalManagement',
          name: 'cashWithdrawalManagement',
          component: cashWithdrawalManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        }, {
          path: '/Materialcertification',
          name: 'Materialcertification',
          component: Materialcertification,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        {
          path: '/creditLoanManagement',
          name: 'creditLoanManagement',
          component: creditLoanManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        {
          path: '/creditLoanReview',
          name: 'creditLoanReview',
          component: creditLoanReview,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        {
          path: '/fullStandardReview',
          name: 'fullStandardReview',
          component: fullStandardReview,
          meta: { requiresAuth: true } // 添加表示需要验证
        },
        {
          path: '/carLoanManagement',
          name: 'carLoanManagement',
          component: carLoanManagement,
          meta: { requiresAuth: true } // 添加表示需要验证
        }
      ]
    }
    /////////////////////// 这是后台路由挂载 （结束） ///////////////////////
  ]
})
