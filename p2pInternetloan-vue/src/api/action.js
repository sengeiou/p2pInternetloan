/**
 * 对后台请求的地址的封装，URL格式如下：
 * 模块名_实体名_操作
 */
export default {
	'SERVER': 'http://localhost:8080/p2pInternetloan', //服务器
  ///////////// 权限模块请求配置  start /////////////
  'VERIFICATION':'/sys/user/verificationCode',  //获取验证码
	'SYSTEM_USER_DOLOGIN': '/sys/user/login', //登陆
  'SYSTEM_USER_GET': '/sys/user/get',
  'SYSTEM_USER_POST': '/sys/user/post',
  'SYSTEM_USER_PUT': '/sys/user/put',
  'SYSTEM_USER_DELETE': '/sys/user/delete',
  'SYSTEM_TREENODE':'/sys/permission/queryUserMenu',  //初始化动态树
  ///////////// 权限模块请求配置  end /////////////

  ///////////// 设置模块请求配置  start /////////////
  ///////////// 设置模块请求配置  end /////////////

  ///////////// 系统模块请求配置  start /////////////
  //数据字典请求
  "SYSTEM_DICT_QUERYPAGER":'/sys/dict/queryPager',
  "SYSTEM_DICT_ADD":"/sys/dict/add",
  "SYSTEM_DICT_UPDATE":"/sys/dict/update",
  "SYSTEM_DICT_DEl":"/sys/dict/del/",
  //这是字典项的请求
  "SYSTEM_DICT_ITEM_QUERYPAGER":'/sys/dictitem/queryPager',
  "SYSTEM_DICT_ITEM_ADD":"/sys/dictitem/add",
  "SYSTEM_DICT_ITEM_UPDATE":"/sys/dictitem/update",
  "SYSTEM_DICT_ITEM_DEl":"/sys/dictitem/del/",
  ///////////// 系统模块请求配置  end /////////////

  ///////////// 会员模块请求配置  start /////////////
  "MEMBERS_MEN_QUERYPAGER":"/members/mem/queryPager",
  "MEMBERS_MEN_ADD":"/members/mem/add",
  "MEMBERS_MEN_UPDATE":"/members/mem/update",
  "MEMBERS_MEN_DEL":"/members/mem/del",


  //会员账户管理
  // membersAccount/account
  "MEMBERS_ACCOUNT_QUERYPAGER":"/membersAccount/account/queryPager",







  ///////////// 会员模块请求配置  end /////////////

  ///////////// 业务模块请求配置  start /////////////
  ///////////// 业务模块请求配置  end /////////////

  ///////////// 资产模块请求配置  start /////////////
  ///////////// 资产模块请求配置  end /////////////

  'getFullPath': k => { //获得请求的完整地址，用于mockjs测试时使用
		return this.SERVER + this[k];

	}
}
