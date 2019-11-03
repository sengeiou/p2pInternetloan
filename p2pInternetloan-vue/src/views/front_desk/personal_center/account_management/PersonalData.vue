<template>
  <div>
    <el-card class="box-card">
      <div class="clearfix" slot="header">
        <strong style="font-size: 23px;">完善个人资料</strong>
      </div>
        <el-form style="margin-top: 22px;" label-width="100px" :model="tableForm" :rules="rules" ref="tableForm" search>
        <el-row>
<!--          <el-col :span="24">-->
<!--            <el-form-item label="个人头像" :label-width="formLabelWidth">-->
<!--              <div class="demo-image__preview">-->
<!--                <el-image-->
<!--                  style="width: 100px; height: 100px"-->
<!--                  :src="tableForm.headPhoto"-->
<!--                  :preview-src-list="srcList">-->
<!--                </el-image>-->
<!--              </div>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="24">
            <el-form-item label="用户名:" prop="membersId">
<!--              <el-input style="line-height: 0px; width: 30%;" placeholder="请输入用户名">-->
<!--              </el-input>-->
            </el-form-item>
          </el-col>
          <el-col :span="24">
                <el-form-item label="电话号码" prop="phone" :rules="[
                 {required: false, message: '请输入电话号码', trigger: 'change'},
                {validator: checkPhone, trigger: 'change'}]">
                <el-input placeholder="请填写申请人手机号码" v-model="tableForm.phone"></el-input>
               </el-form-item>
            </el-col>
          <el-col :span="24">
            <el-form-item label="个人学历" prop="myselfBackground">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.myselfBackground">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="月收入" prop="monthlyIncome">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.monthlyIncome">
                <el-option v-for="item in a" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="婚姻情况" prop="maritalStatus">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.maritalStatus">
                <el-option v-for="item in b" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="子女情况" prop="children">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.children">
                <el-option v-for="item in c" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="住房条件" prop="house">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.house">
                <el-option v-for="item in d" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <div>
<!--          <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="onQuery">保存数据</el-button>-->
<!--          <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem">提交</el-button>-->
          <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem">确定</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
    import commonUtils from "../../../../api/commonUtils";
    export default {
        name: "PersonalData",
        data: function(){
            return{
                srcList: [
                ],
                                    //这是查询阐述
                // queryParams: {
                //     //定义搜索维度
                //     id: 2,
                //     membersId: null,
                //     phone: null,
                //     myselfBackground: null,
                //     monthlyIncome: null,
                //     maritalStatus: null,
                //     children: null,
                //     house: null,
                // },
                //这是下拉框 数据动态绑定
                options: [{
                    value: '0',
                    label: '小学'
                }, {
                    value: '1',
                    label: '初中'
                }, {
                    value: '2',
                    label: '高中'
                },{
                    value: '3',
                    label: '大学'
                },{
                    value: '4',
                    label: '大学以上 '
                }],
                a: [{
                    value: '0',
                    label: '3000以上'
                }, {
                    value: '1',
                    label: '4000以上'
                }, {
                    value: '2',
                    label: '5000以上'
                },{
                    value: '3',
                    label: '6000以上'
                },{
                    value: '4',
                    label: '10000以上 '
                }],
                b: [{
                    value: '0',
                    label: '小学'
                }, {
                    value: '1',
                    label: '已婚'
                }, {
                    value: '2',
                    label: '未婚'
                }],
                c: [{
                    value: '0',
                    label: '独生子女'
                }, {
                    value: '1',
                    label: '俩女儿'
                }, {
                    value: '2',
                    label: '俩儿子'
                }],
                d: [{
                    value: '0',
                    label: '有房'
                }, {
                    value: '1',
                    label: '无房'
                }],
                tableForm:{
                    id:1,
                    membersId:2,
                    phone:18797736486,
                    myselfBackground:'初中',
                    monthlyIncome:3000,
                    maritalStatus:'未婚',
                    children:'一个女儿',
                    house:'有房',
                    headPhoto:null,
                },
                //这是表格是否占时加载动画
                loading:false,
                //这数据对象（用于表格占时）
                dictList:[],
                //这是新值修改弹出
                // dialogTitle:null,
                //这是新值和修改弹出是否占时
                dialogFormVisible: false,
                //这是文本框中文字说明的长度
                formLabelWidth: "100px",

                rules: {
                    realname: [{
                        required: true,
                        message: '请输入用户名',
                        trigger: 'blur'
                    }],
                    sex: [{
                        required: true,
                        message: '请选择性别',
                        trigger: 'blur'
                    }],
                    address: [{
                        required: true,
                        message: '请输入证件地址',
                        trigger: 'blur'
                    }],
                    remark: [
                        { required: true,
                            message: '请输入活动名称',
                            trigger: 'blur'
                        },
                        {
                            min: 6,
                            max: 50,
                            message: '长度在 6 到 50 个字符',
                            trigger: 'blur'
                        }
                    ],

                },
            }
        },
        methods:{
            checkPhone (rule, value, callback) {
                         if (!(/^1[34578]\d{9}$/.test(value))) {
                              callback(new Error('请输入正确的手机号码!'));
                             }
                   },

            doSubmitDictItem: function() {
                this.$refs['tableForm'].validate((valid) => {
                    if (true) {
                        var url = null;
                        //判断时添加还是修改
                        if (null != this.dictList.id) {
                            url = this.axios.urls.MEMBERS_AAA_UPDATE;
                        } else {
                            url = this.axios.urls.MEMBERS_AAA_ADD;
                        }
                        //发送请求
                        this.axios.get(url, {params: this.tableForm}).then(response => {

                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: '错了哦提交失败',
                                    type: 'warning'
                                });
                            } else {
                                //打印成功信息
                                this.$message({
                                    message: '恭喜提交成功了',
                                    type: 'success'
                                });
                                this.doCancel()
                            }

                        }).catch(function(error) {
                            console.log(error);
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },

            //添加、修改通用
            // doSubmitDictItem: function() {
            //             var url = null;
            //             //判断时添加还是修改
            //             if (null != this.tableForm.id) {
            //                 url = this.axios.urls.MEMBERS_AAA_UPDATE;
            //
            //             } else {
            //                 // url = this.axios.urls.MEMBERS_AAA_UPDATE;
            //             }
            //             //发送请求
            //             this.axios.post(url, {params: this.tableForm}).then(response => {
            //
            //                 //如果是操作失败
            //                 if (response.data.code == 500) {
            //                     this.$message({
            //                         message: '错了哦提交失败',
            //                         type: 'warning'
            //                     });
            //                 } else {
            //                     //打印成功信息
            //                     this.$message({
            //                         message: '恭喜提交成功了',
            //                         type: 'success'
            //                     });
            //                     this.doCancel()
            //                 }
            //
            //             }).catch(function(error) {
            //                 console.log(error);
            //             });
            //
            // },

                // debugger
                // this.$refs['tableForm'].validate((valid) => {
                //     alert("是否成功");
                //     debugger
                //     // if (true) {
                //     //     var url = null;
                //     //     //判断时添加还是修改
                //     //     if (null != this.dictList.id) {
                //     //         url = this.axios.urls.MEMBERS_AAA_UPDATE;
                //     //
                //     //     } else {
                //     //         url = this.axios.urls.MEMBERS_AAA_UPDATE;
                //     //     }
                //     //     //发送请求
                //     //     this.axios.post(url, {params: this.tableForm}).then(response => {
                //     //
                //     //         //如果是操作失败
                //     //         if (response.data.code == 500) {
                //     //             this.$message({
                //     //                 message: '错了哦提交失败',
                //     //                 type: 'warning'
                //     //             });
                //     //         } else {
                //     //             //打印成功信息
                //     //             this.$message({
                //     //                 message: '恭喜提交成功了',
                //     //                 type: 'success'
                //     //             });
                //     //             this.doCancel()
                //     //         }
                //     //
                //     //     }).catch(function(error) {
                //     //         console.log(error);
                //     //     });
                //     // } else {
                //     //     console.log('error submit!!');
                //     //     return false;
                //     // }
                // });

            // onQuery() {
            //     this.search();
            // },
            // // 根据id查单个所有
            // search: function() {
            //     let url = this.axios.urls.MEMBERS_AAA_QUERYBYID;
            //     // this.certified();
            //     let params = this.queryParams;
            //     //查询动画
            //     this.loading = true;
            //     //向后端请求数据
            //     this.axios.get(url,{params:{id:1}}).then(response => {
            //         this.dataList = response.data.data;
            //         this.queryParams.total = response.data.total;
            //         //数据查询到了关闭查询动画
            //         this.loading = false;
            //
            //     }).catch(function(error) {
            //         console.log(error);
            //     });
            // },

            //表单回响
            // certified: function(id, row) {
            //     this.tableForm.id = row.id;
            //     this.tableForm.membersId = row.membersId;
            //     this.tableForm.phone = row.phone;
            //     this.tableForm.myselfBackground = row.myselfBackground;
            //     this.tableForm.monthlyIncome = row.monthlyIncome;
            //     this.tableForm.children = row.children;
            //     this.tableForm.house = row.house;
            // },

        }

    }
</script>

<style scoped>

</style>
