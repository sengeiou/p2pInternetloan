/**
 * 对后台请求的地址的封装，URL格式如下：
 * 模块名_实体名_操作
 */
export default {
	'SERVER': 'http://localhost:8080/p2pInternetloan', //服务器
  'VERIFICATION':'/sys/user/verificationCode',  //获取验证码
	'SYSTEM_USER_DOLOGIN': '/sys/user/login', //登陆
  'SYSTEM_USER_GET': '/sys/user/get',
  'SYSTEM_USER_POST': '/sys/user/post',
  'SYSTEM_USER_PUT': '/sys/user/put',
  'SYSTEM_USER_DELETE': '/sys/user/delete',
  'SYSTEM_TREENODE':'/sys/permission/queryUserMenu',  //初始化动态树
  'getFullPath': k => { //获得请求的完整地址，用于mockjs测试时使用
		return this.SERVER + this[k];

	}
}
