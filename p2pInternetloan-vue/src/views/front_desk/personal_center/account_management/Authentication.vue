<template>
  <div>
<el-card class="box-card">
  <div class="clearfix" slot="header">
    <strong style="font-size: 23px;">实名认证</strong>
  </div>
  <span style="color: rgb(241, 2, 2);">为了保障您的安全,实名认证成功后不能修改,请认真填写</span>
<!--  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">-->
  <el-form style="margin-top: 22px;" label-width="100px" :model="tableForm" :rules="rules" ref="tableForm">
    <el-row>
      <el-col :span="12">
        <el-form-item label="用户名" prop="realname">
          <el-input placeholder="请输入用户名" v-model="tableForm.realname" autocomplete="off">
          </el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="出生日期" prop="bornDateForm">
          <div class="block">
            <el-date-picker placeholder="选择日期" v-model="tableForm.bornDateForm" autocomplete="off">
            </el-date-picker>
          </div>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="tableForm.sex" autocomplete="off">
            <el-radio label="1">男</el-radio>
            <el-radio label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="证件地址" prop="address">
          <el-input placeholder="请输入证件地址" v-model="tableForm.address" autocomplete="off">
          </el-input>
        </el-form-item>
      </el-col>
      <el-col :span="12">
               <el-form-item label="证件号码" prop="idNumber" :rules="[
               {required: true, message: '请填写证件号码', trigger: 'blur' },
             {pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/, message: '证件号码格式有误！', trigger: 'blur'} ]">
            <el-input v-model="tableForm.idNumber" placeholder="输入证件号码"></el-input>
             </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" :rows="4" v-model="tableForm.remark">
          </el-input>
        </el-form-item>
      </el-col>
      <el-col :span="7">
        <el-form-item label="身份证正面照片" label-width="112px" prop="image1">
<!--          <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" v-model="tableForm.image1" >-->
<!--            <i class="el-icon-plus">-->
<!--            </i>-->
<!--          </el-upload>-->
          <el-upload v-model="tableForm.image1" action="#" list-type="picture-card" :auto-upload="false">
            <i slot="default" class="el-icon-plus"></i>
            <div slot="file" slot-scope="{file}">
              <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
              <span class="el-upload-list__item-actions">
        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
          <i class="el-icon-zoom-in"></i>
        </span>
        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
          <i class="el-icon-download"></i>
        </span>
        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
          <i class="el-icon-delete"></i>
        </span>
      </span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
      </el-col>



      <el-col :span="11">
        <el-form-item label="身份证反面照片" label-width="112px" prop="image2">
<!--          <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" v-model="tableForm.image2" >-->
<!--            <i class="el-icon-plus">-->
<!--            </i>-->
<!--          </el-upload>-->
          <el-upload v-model="tableForm.image2" action="#" list-type="picture-card" :auto-upload="false">
            <i slot="default" class="el-icon-plus"></i>
            <div slot="file" slot-scope="{file}">
              <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
              <span class="el-upload-list__item-actions">
        <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
          <i class="el-icon-zoom-in"></i>
        </span>
        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
          <i class="el-icon-download"></i>
        </span>
        <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
          <i class="el-icon-delete"></i>
        </span>
      </span>
            </div>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
      </el-col>
    </el-row>
    <div>
      <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doCancel">取消</el-button>
      <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem">确定</el-button>
    </div>
  </el-form>
</el-card>
  </div>
</template>

<script>
    //导入工具包
    import commonUtils from "../../../../api/commonUtils";

    // import axios from "../../../../api/axios";

    export default {
        // install: function(Vue, Option) {
        //     Object.defineProperty(Vue.prototype, "$http", { value: Axios });
        // },

        name: "Authentication",
        data:function() {

            return {
                // fileList: [
                //     {name: 'food.jpg', url: ''}, {name: 'food2.jpg', url: ''}
                //     ],
                // dialogImageUrl: '',
                // dialogVisible: false,
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false,


                tableForm:{
                    id:null,
                    realname:null,
                    sex:null,
                    bornDateForm:null,
                    idNumber:null,
                    address:null,
                    remark:null,
                    state:null,
                    image1:null,
                    image2:null,
                },
                //这是表格是否占时加载动画
                loading:false,
                //这书数据对象（用于表格占时）
                dictList:[],
                //这是新值修改弹出
                dictDialogTitle:null,
                //这是新值和修改弹出是否占时
                dictDialogFormVisible: false,
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
                value1: '',
                value2: '',
            };
        },
        methods:{
            handleRemove(file) {
                console.log(file);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            handleDownload(file) {
                console.log(file);
            },
            // handleRemove(file, fileList) {
            //     console.log(file, fileList);
            // },
            // handlePictureCardPreview(file) {
            //     this.dialogImageUrl = file.url;
            //     this.dialogVisible = true;
            // },
            //这是提交就调用的方法【添加、修改通用】  --------------------
            doSubmitDictItem: function() {
                this.$refs['tableForm'].validate((valid) => {
                    if (true) {
                        var url = null;
                        //判断时添加还是修改
                        if (null != this.dictList.id) {
                            url = this.axios.urls.MEMBERS_MEN_UPDATE;
                        } else {
                            url = this.axios.urls.MEMBERS_MEN_ADD;
                        }
                        //时间的特殊处理
                        this.tableForm.bornDateForm = commonUtils.formatDate(this.tableForm.bornDateForm, "yyyy-MM-dd");
                        // alert(this.tableForm.image1)


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
            // beforeUpload(file) {
            //     var code = store.state.shop_code;
            //     let fd = new FormData();//通过form数据格式来传
            //     fd.append("picFile", file); //传文件
            //     fd.append("shop_code", code); //传其他参数
            //     var url = api.uploadotherpic;
            //     this.$http
            //         .post(url, fd)
            //         .then(res => {
            //             console.log(res.data);
            //             let data = res.data;
            //             if (data.code == 200) {
            //                 this.$message({
            //                     message: "上传成功",
            //                     type: "success"
            //                 });
            //
            //                 //再次请求数据，实现本地与服务器内容一致，解决图片删除失败的bug
            //                 var code = store.state.shop_code;
            //                 this.getImgs(code);//封装好的请求后台图片的axios请求
            //             } else {
            //                 this.$message.error(data.msg);
            //             }
            //         })
            //         var url = "/getgoodlist";
            //         this.$http
            //             .get(url, { params: { shop_code: 126 } })
            //             .then(res => {
            //                 console.log(res.data);
            //             })
            //             .catch(error => {
            //                 console.log(error);
            //             }).catch(error => {
            //         });
            // },
            doCancel() {
                this.tableForm.realname = '';
                this.tableForm.bornDateForm = '';
                this.tableForm.sex = '';
                this.tableForm.address = '';
                this.tableForm.idNumber = '';
                this.tableForm.image1 = '';
                this.tableForm.image2 = '';
                this.tableForm.remark = '';
                // this.tableForm.bornDateForm = '';
                this.tableForm.image1 = '';
                this.tableForm.image2 = '';
            },
            pickerOptions: {
                disabledDate(time) {
                    return time.getTime() > Date.now();
                },
                shortcuts: [{
                    text: '今天',
                    onClick(picker) {
                        picker.$emit('pick', new Date());
                    }
                }, {
                    text: '昨天',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24);
                        picker.$emit('pick', date);
                    }
                }, {
                    text: '一周前',
                    onClick(picker) {
                        const date = new Date();
                        date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                        picker.$emit('pick', date);
                    }
                }]
            },
        }
    };
</script>
<style scoped>
  @import url("//unpkg.com/element-ui@2.12.0/lib/theme-chalk/index.css");
</style>
