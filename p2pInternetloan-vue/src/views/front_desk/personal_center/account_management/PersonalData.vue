<template>
  <div>
    <el-card class="box-card">
      <div class="clearfix" slot="header">
        <strong style="font-size: 23px;">个人资料</strong>
      </div>
<!--      <el-form style="margin-top: 22px;" label-width="100px">-->
        <el-form style="margin-top: 22px;" label-width="100px" :model="tableForm" :rules="rules" ref="tableForm" search>
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户名:" prop="membersId">
              <el-input style="line-height: 0px; width: 30%;" placeholder="请输入用户名">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
                <el-form-item label="电话号码" prop="phone" :rules="[
                 {required: true, message: '请输入电话号码', trigger: 'change'},
                {validator: checkPhone, trigger: 'change'}]">
                <el-input v-model="tableForm.phone" placeholder="请填写申请人手机号码"></el-input>
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
            <el-form-item label="子女情况" prop="myselfBackground">
              <el-select  @focus="focus"filterable placeholder="请选择^@^" v-model="tableForm.myselfBackground">
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
          <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem">保存数据</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
    export default {
        name: "PersonalData",
        data: function(){
            return{
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
                    id:null,
                    membersId:null,
                    phone:null,
                    myselfBackground:null,
                    monthlyIncome:null,
                    maritalStatus:null,
                    children:null,
                    house:null,
                },
                //这是表格是否占时加载动画
                loading:false,
                //这数据对象（用于表格占时）
                dataList:[],
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
                      else {
                         this.$api.bsp.member.checkPhoneNo(this.tableForm.phone, this.tableForm.id).then(ret => {
                        if (ret) {
                          callback(new Error('该手机号码已存在，请修改手机号码。'));
                        } else {
                            callback();
                         }
                         });
                    }
                   },
            //添加、修改通用
            doSubmitDictItem: function() {
                this.$refs['tableForm'].validate((valid) => {
                    if (true) {
                        var url = null;
                        //判断时添加还是修改
                        if (null != this.dictList.id) {
                            url = this.axios.urls.MEMBERS_DETA_UPDATE;
                        } else {
                            url = this.axios.urls.MEMBERS_DETA_ADD;
                        }
                        //发送请求
                        this.axios.get(url, {params: this.tableForm}).then(response => {

                            debugger
                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: '错了哦保存失败',
                                    type: 'warning'
                                });
                            } else {
                                //重新搜索
                                this.search();
                                //打印成功信息
                                this.$message({
                                    message: '恭喜保存成功了',
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
            //根据id查单个所有
            search: function() {
                let url = this.axios.urls.MEMBERS_DETA_QUERYBYID;
                this.certified();
                // let params = this.queryParams;
                //查询动画
                this.loading = true;
                //向后端请求数据
                this.axios.get(url,{params:params}).then(response => {
                    this.dataList = response.data.data
                    this.queryParams.total = response.data.total;
                    //数据查询到了关闭查询动画
                    this.loading = false;
                }).catch(function(error) {
                    console.log(error);
                });
            },
            //表单回响
            certified: function(id, row) {
                // this.dialogTitle = "认证";
                this.tableForm.id = row.id;
                this.tableForm.membersId = row.membersId;
                // this.tableForm.sex = row.sex;
                // this.tableForm.bornDate = row.bornDate;
                // this.tableForm.idNumber = row.idNumber;
                // this.tableForm.address = row.address;
                // this.tableForm.remark = row.remark;
                // this.tableForm.image1 = row.image1;
                // this.tableForm.image2 = row.image2;
                // this.tableForm.state = row.state;

                this.dialogFormVisible = true;
            },
            // doCancel() {
            //     this.tableForm.realname = '';
            //     this.tableForm.bornDateForm = '';
            //     this.tableForm.sex = '';
            //     this.tableForm.address = '';
            //     this.tableForm.idNumber = '';
            //     this.tableForm.image1 = '';
            //     this.tableForm.image2 = '';
            //     this.tableForm.remark = '';
            //     // this.tableForm.bornDateForm = '';
            //     this.tableForm.image1 = '';
            //     this.tableForm.image2 = '';
            // },
        }

    }
</script>

<style scoped>

</style>
