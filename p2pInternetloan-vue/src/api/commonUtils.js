//这是通用工具类 提供了最最最常用的基础方法 ，帮助你快速简化开发。让你的代码不在有重复 ！！！

export default {
  let_this:null,
  init:function(then){
    this.let_this = then;
  },
  /**
   *修改器前期表单值绑定,和打开弹出
   * @param row 回显数据
   * @param formDataName 表单数据绑定对象名
   * @param showDialogDataName 这是要打开的弹出的展示名
   * @param dialogTitleDataName 这是要改变的标题名
   */
  handleEdit:function(row , formDataName, showDialogDataName, dialogTitleDataName){
    var formData  = this.let_this[formDataName];
    //开启数据回显
    for(var name in formData){
      formData[name] = row[name];
    }
    //打开弹出
    this.let_this[showDialogDataName] = true;
    if(dialogTitleDataName){
      this.let_this[dialogTitleDataName] = "修改";
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
   */
  doAjaxPost(url, data, success){
    this.let_this.axios.post(url, data).then(response => {
      if (response.data.code == 500) {
        this.let_this.$message({
          message: response.data.msg,
          type: 'warning'
        });
      } else {
        success()
        this.let_this.$message({
          message: response.data.msg,
          type: 'success'
        });
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
  }
}

