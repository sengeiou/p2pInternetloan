//这是通用工具类 提供了最最最常用的基础方法 ，帮助你快速简化开发。让你的代码不在有重复 ！！！
import axios from "axios";
import router from "../../config";
// import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");

export default {
  let_this:null,
  init:function(then){
    this.let_this = then;
  },
  export: function uploadImgToBase64 (file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader()
      reader.readAsDataURL(file)
      reader.onload = function () { // 图片转base64完成后返回reader对象
        resolve(reader)
      }
      reader.onerror = reject
    })
  },
  /**
   *修改器前期表单值绑定,和打开弹出
   * @param row 回显数据
   * @param formDataName 表单数据绑定对象名
   * @param showDialogDataName 这是要打开的弹出的展示名
   * @param dialogTitleDataName 这是要改变的标题名
   */
  handleEdit:function(row , formDataName, showDialogDataName, dialogTitleDataName){
    this.echo(row, formDataName);
    //打开弹出
    this.let_this[showDialogDataName] = true;
    if(dialogTitleDataName){
      this.let_this[dialogTitleDataName] = "修改";
    }
  },
  /**
   * 这是数据回显调用的方法
   * @param row 回显数据
   * @param echoDataName 回显目标对象
   */
  echo:function(row , echoDataName){
    var echoData= this.let_this[echoDataName];
    //开启数据回显
    for(var name in echoData){
      echoData[name] = row[name];
    }
  },
  /**
   * 这是关闭弹出并清空表单数据
   * @closeDialogDataName 这是要关闭弹出绑定是否占时数据的名字
   * @param refForm 这是表单的 ref 属性值(表单的ref必须要和双向数据绑定的值一致)
   */
  doCancel: function(closeDialogDataName, refForm){
    //关闭弹出
    this.let_this[closeDialogDataName] = false;
    this.doClearForm(refForm);
  },
  /**
   *
   * 这是清空表单的所有数据和验证
   * @param refForm  这是表单的 ref 属性值
   */
  doClearForm: function(refForm){
    //表单对象
    var formObj = this.let_this.$refs[refForm] ;
    //表单数据对象
    var formData = formObj.model
    //清空数据
    for(var name in formData){
      formData[name] = null;
    }
    //继续清空
    var modelObj = this.let_this[refForm];
    for (var name in modelObj){
      modelObj[name] = null;
    }
    //清空验证
    formObj.resetFields();
  },
  /**
   * 这是发起ajax请求以 get 方式
   * @param url 这是请求的理解
   * @param data 这是请求的数据
   * @param success 请求成功要回调的函数
   */
  doAjaxGet(url, data, success,error){
    this.let_this.axios.get(url, {params:data}).then(response => {
        // 执行成功回调
        success(response.data)
    }).catch(function(error) {
        error(error);
    });
  },
  /**
   * 这是发起ajax请求 以 post 方式
   * @param url 这是请求的理解
   * @param data 这是请求的数据
   * @param success 请求成功要回调的函数
   * @param simple 是否使用简单一点的
   */
  doAjaxPost(url, data, success, simple){
    this.let_this.axios.post(url, data).then(response => {
      if(simple){
        success(response.data)
      }else {
        if (response.data.code == 500) {
          this.let_this.$message({
            message: response.data.msg,
            type: 'warning'
          });
        } else {
          success(response.data)
          this.let_this.$message({
            message: response.data.msg,
            type: 'success'
          });
        }
      }
    }).catch(function(error) {
      console.log(error);
    });
  },
  /**
   * 获取指定数据字典下拉框的数据
   * @param sn 这是数据字典的编号
   * @param dataName 要动态绑定的对象名
   */
  getDictSelect(sn, dataName){
    let url = this.let_this.axios.urls.SYSTEM_DICT_ITEM_GET_SELECT;
    //向后端请求数据
    this.let_this.axios.get(url,{params:{sn:sn}}).then(response => {
        //设置值
        this.setData(dataName, response.data);
    }).catch(error => {
        console.log(error);
    });
  },
  /**
   * 这是当有 xx.aaa 属性要设置的时候 进行处理 最对支持 5层深度(111.222.333.444.555)，不够可以自行添加
   * @param dataName 这是要设置的属性名
   * @param value 这是设置到里面的值
   */
  setData(dataName, value){
    let dataNameArr = dataName.split(".");
    switch (dataNameArr.length) {
      case 1:
        this.let_this[dataNameArr[0]] = value;
      case 2:
        this.let_this[dataNameArr[0]][dataNameArr[1]] = value;
      case 3:
        this.let_this[dataNameArr[0]][dataNameArr[1]][dataNameArr[2]] = value;
      case 4:
        this.let_this[dataNameArr[0]][dataNameArr[1]][dataNameArr[2]][dataNameArr[3]] = value;
      case 5:
        this.let_this[dataNameArr[0]][dataNameArr[1]][dataNameArr[2]][dataNameArr[3]][dataNameArr[4]] = value;
    }
  },

  /**
   * 跳转到指定路由
   * @param routerPath 路由路径
   */
  toRouter(routerPath){
    //跳转路由
    this.let_this.$router.push({
      path:routerPath
    })
  },
  /**
   * 这是将对象中所有属性设置为统一值
   * @param obj 目标对象
   * @param value 这是要设置的值
   */
  setAll(obj, value){
    for(var name in obj){
      obj[name] = value;
    }
  },
  arrToStr(arr){
    var str = "";
    for (let i = 0; i < arr.length; i++) {
      str += "," + arr[i]
    }
    if(str != null && str.length > 0){
      str = str.substr(1);
    }
    return str;

  },

  /**
   * 这是定义一个时间格式转换方法
   * 使用方法 例如：  formatDate(new Date(), "yyyy-MM-dd hh:mm:ss"); 返回的就是 fmt 中指定的格式了
   * 详情看： https://blog.csdn.net/qq_39985511/article/details/80031674
   * @param date 时间对象
   * @param fmt 这是要转换的格式
   */
  formatDate(date, fmt){
    var o = {
      "M+" : date.getMonth()+1,                 //月份
      "d+" : date.getDate(),                    //日
      "h+" : date.getHours(),                    //小时
      "m+" : date.getMinutes(),                 //分
      "s+" : date.getSeconds(),                 //秒
      "q+" : Math.floor((date.getMonth()+3)/3), //季度
      "S"  : date.getMilliseconds()             //毫秒
    };

    if(/(y+)/.test(fmt)) {
      fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
      if(new RegExp("("+ k +")").test(fmt)){
        fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
      }
    }
    return fmt;
  }


}
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
//返回状态判断(添加响应拦截器)
Axios.interceptors.response.use(
  res => {
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

