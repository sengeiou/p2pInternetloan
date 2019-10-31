/**
 * vue项目对axios的全局配置
 */
import axios from 'axios'
import qs from 'qs'

//引入action模块，并添加至axios的类属性urls上
import action from '@/api/action'
import de from "element-ui/src/locale/lang/de";
axios.urls = action

// axios默认配置
axios.defaults.timeout = 10000; // 超时时间
axios.defaults.baseURL = action.SERVER;




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



// 响应拦截器
axios.interceptors.response.use(function(response) {
  //保存验证码jwt令牌
  let verificationjwt=response.headers['verificationjwt'];
  console.log(verificationjwt)
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


export default axios;
