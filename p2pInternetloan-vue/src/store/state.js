export default{
	resturantName:'飞歌餐馆',
	jwt:null,//用户登录成功信息会保存在这里
  verificationJwt:null, //这是用来保存用户等登录验证码jwt身份识别的
	user:null,//保存当前用户登录对象（前端或后台 ）
	salChance:null,
  paramObj:null,//页面传参对象
	dict:null,
	userTime:null,
  options: [],
  activeIndex: '',
  showName:'show',
  role:"",
  //这是保持用户权限数组的
  permission:['add']
}
