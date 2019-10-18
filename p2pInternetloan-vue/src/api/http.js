/**
 * vue项目对axios的全局配置
 */
import axios from 'axios'
import qs from 'qs'

//引入action模块，并添加至axios的类属性urls上
import action from '@/api/action'
axios.urls = action

// axios默认配置
axios.defaults.timeout = 10000; // 超时时间
// axios.defaults.baseURL = 'http://localhost:8080/j2ee15'; // 默认地址
axios.defaults.baseURL = action.SERVER;

//axios.defaults.retry = 4;
//
//axios.defaults.retryDelay = 1000;


//整理数据
// 只适用于 POST,PUT,PATCH，transformRequest` 允许在向服务器发送前，修改请求数据
axios.defaults.transformRequest = function(data) {
  data = qs.stringify(data);
  return data;
};


// 请求拦截器
axios.interceptors.request.use(function(config) {
  //设置验证码jwt令牌
  let verificationJwt = window.vm.$store.getters.getVerificationJwt;
  if(verificationJwt){
    config.headers['verificationJwt'] = verificationJwt;
  }

  //这是登录后设置的jwt令牌
  let jwt=window.vm.$store.getters.getJwt;
  if(jwt){
    config.headers['jwt']=jwt;
  }
  return config;
}, function(error) {
  return Promise.reject(error);
});


//axios.interceptors.response.use(undefined, function axiosRetryInterceptor(err) {
//	var config = err.config;
//	//如果配置不存在或未设置重试选项，则返回错误信息
//	if(!config || !config.retry) return Promise.reject(err.response.data);
//	//设置变量即跟踪重试次数
//	config.__retryCount = config.__retryCount || 0;
//	// 检查我们是否已经超过了总重试次数
//	if(config.__retryCount >= config.retry) {
//		// 返回错误信息
//		return Promise.reject(err.response.data);
//	}
//	// 重试次数加1
//	config.__retryCount += 1;
//	// 创建延时器等待发送重试请求
//	var backoff = new Promise(function(resolve) {
//
//		setTimeout(function() {
//			resolve();
//		}, config.retryDelay || 1);
//	});
//	// 返回调用AXIOS来重试请求
//	return backoff.then(function() {
//		return axios(config);
//	});
//});


// 响应拦截器
axios.interceptors.response.use(function(response) {
  //保存验证码jwt令牌
  let verificationjwt=response.headers['verificationjwt'];
  if(verificationjwt){
    window.vm.$store.commit('setVerificationJwt',{
      verificationJwt:verificationjwt
    });
  }
// 保存登录成功的jwt令牌
  let jwt=response.headers['jwt'];
  if(jwt){
    window.vm.$store.commit('setJwt',{
      jwt:jwt
    });
  }
  return response;
}, function(error) {
  return Promise.reject(error);
});

// // 路由请求拦截
// // http request 拦截器
// axios.interceptors.request.use(
// 	config => {
// 		//config.data = JSON.stringify(config.data);
// 		//config.headers['Content-Type'] = 'application/json;charset=UTF-8';
// // 		//config.headers['Token'] = 'abcxyz';
// 		//判断是否存在ticket，如果存在的话，则每个http header都加上ticket
// 		// if (cookie.get("token")) {
// 		// 	//用户每次操作，都将cookie设置成2小时
// 		// 	cookie.set("token", cookie.get("token"), 1 / 12)
// 		// 	cookie.set("name", cookie.get("name"), 1 / 12)
// 		// 	config.headers.token = cookie.get("token");
// 		// 	config.headers.name = cookie.get("name");
// 		// }
// 		return config;
// 	},
// 	error => {
// 		return Promise.reject(error.response);
// 	});

// // 路由响应拦截
// // http response 拦截器
// axios.interceptors.response.use(
// 	response => {
// 		if (response.data.resultCode == "404") {
// 			console.log("response.data.resultCode是404")
// 			// 返回 错误代码-1 清除ticket信息并跳转到登录页面
// 			//      cookie.del("ticket")
// 			//      window.location.href='http://login.com'
// 			return
// 		} else {
// 			return response;
// 		}
// 	},
// 	error => {
// 		return Promise.reject(error.response) // 返回接口返回的错误信息
// 	});



export default axios;
