import Mock from 'mockjs' //引入mockjs，npm已安装
import action from '@/api/action' //引入封装的请求地址

//全局设置：设置所有ajax请求的超时时间，模拟网络传输耗时
Mock.setup({
	// timeout: 400  //延时400s请求到数据
	timeout: 200 - 400 //延时200-400s请求到数据
})


import loginInfo from '@/mock/json/login-mock.js'
import menuInfo from '@/mock/json/menu-mock.js'

let url=action.getFullPath('SYSTEM_USER_DOLOGIN');
let menuUrl=action.getFullPath('SYSTEM_TREENODE');

//通过mock.js模拟发送请求
Mock.mock(url, /post|get/i,loginInfo)
Mock.mock(menuUrl, /post|get/i,menuInfo)

//  Mock.mock(url,/post|get/i, (options) =>{
//           // 最佳实践，
//       debugger;
//       return Mock.mock(loginInfo);
//  });

