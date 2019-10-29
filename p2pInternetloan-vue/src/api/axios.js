
/**
 * http配置
 */
// 引入axios以及element ui中的loading和message组件
import axios from "axios";
import qs from "qs";
import { Message } from "element-ui";
import router from "config/index.js";//配置路由的文件

const Axios = axios.create({
  baseURL: "https://api.xxxxxx.com", // 基础URL
  timeout: 10000,
  responseType: "json",
  withCredentials: true, // 是否允许带cookie这些
  headers: {
    //   "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
    "Content-Type": "application/json;charset=utf-8"
  }
});

//POST传参序列化(添加请求拦截器)
Axios.interceptors.request.use(
  config => {
    // 在发送请求之前做某件事
    if (config.method === "post") {
    }

    return config;
  },
  error => {
    // error 的回调信息,看贵公司的定义
    Message({
      showClose: true,
      message: error,
      type: "warning"
    });
    return Promise.reject(error);
  }
);

//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(
  res => {
    //对响应数据做些事
    // if (res.data && !res.data.success) {
    //   return Promise.reject(res.data.error);
    // }

    return res;
  },
  error => {
    // console.log(error);
    if (error.data) {
      switch (error.data.code) {
        case 401:
          // 返回 401 清除token信息并跳转到登录页面
          // store.commit("del_token");
          router.push({
            path: "/login",
            query: {
              redirect: router.currentRoute.fullPath
            }
          });
          break;

      }
    }
    return Promise.reject(error);
  }
);

// 对axios的实例重新封装成一个plugin ,方便 Vue.use(xxxx)
export default {
  install: function(Vue, Option) {
    Object.defineProperty(Vue.prototype, "$http", { value: Axios });
  }
};
