//const loginInfo={
//	'success':true,
//	'msg':'用户登录成功!'
//}

const menuInfo={
  "success": true,
  "rows": [
    {
      "id": 10,
      "pid": -1,
      "text": "营销管理",
      "icon": "el-icon-s-order",
      "url": null,
      "sort": 1,
      "childern": []
    },
    {
      "id": 20,
      "pid": -1,
      "text": "客户管理",
      "icon": "el-icon-s-order",
      "url": null,
      "sort": 2,
      "childern": []
    },
    {
      "id": 30,
      "pid": -1,
      "text": "服务管理",
      "icon": "el-icon-s-order",
      "url": null,
      "sort": 3,
      "childern": []
    },
    {
      "id": 40,
      "pid": -1,
      "text": "统计报表",
      "icon": "el-icon-s-order",
      "url": null,
      "sort": 4,
      "childern": []
    },
    {
      "id": 50,
      "pid": -1,
      "text": "基础数据",
      "icon": "el-icon-s-order",
      "url": null,
      "sort": 5,
      "childern": [
        {
          "id":5004,
          "pid":50,
          "sort":0,
          "text":"权限管理",
          "url":"/base_data/authority_management/myRoleManage"
        },{
          "id":5001,
          "pid":50,
          "sort":0,
          "text":"数字字典管理",
          "url":"/base_data/dictList/dictList"
        }
      ]
    }
  ]
}

export default menuInfo
