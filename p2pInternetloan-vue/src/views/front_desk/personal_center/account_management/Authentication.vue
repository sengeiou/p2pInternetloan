<template>
  <div>
<!--    这是认证成功-->
    <el-card class="box-card" style="height: 400px;" v-if="isIdentityAuthentication == 0">
      <div class="clearfix" slot="header">
        <strong style="font-size: 23px;">实名认证</strong>
        <span style="float: right">认证成功</span>
      </div>
      <div style="text-align: center;margin-top: 40px;">
        <i class="el-icon-circle-check" style="font-size: 38px; color: rgb(14, 239, 59);">实名认证成功</i>
        <p style="color: rgb(153, 153, 153);">您已经通过本品台的身份认证，</p>
        <p style="color: rgb(153, 153, 153);">如有疑问 请致电 18874374737,很高效为您服务</p>
      </div>
    </el-card>

    <!--未认证或者认证失败（后台审核失败）-->
    <el-card class="box-card"  v-if="isIdentityAuthentication == 1 || isIdentityAuthentication == 3">
      <div class="clearfix" slot="header">
        <strong style="font-size: 23px;">实名认证</strong>
        <span style="float: right">当前还未通过实名认证</span>
      </div>
      <span style="color: rgb(241, 2, 2);">为了保障您的安全,实名认证成功后不能修改,请认真填写</span>
      <el-form style="margin-top: 22px;" label-width="100px" :model="tableForm" :rules="rules" ref="tableForm" :visible="dialogFormVisible" @close="doCancel">
        <el-row>
          <el-col :span="24">
            <el-form-item label="用户名" prop="realname">
              <el-input placeholder="请输入用户名" v-model="tableForm.realname" autocomplete="off">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
                   <el-form-item label="证件号码" prop="idNumber" :rules="[
                   {required: true, message: '请填写证件号码', trigger: 'blur' },
                 {pattern: /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/, message: '证件号码格式有误！', trigger: 'blur'} ]">
                <el-input v-model="tableForm.idNumber" placeholder="输入证件号码"></el-input>
                 </el-form-item>
          </el-col>
          <el-col :span="12" style="margin-top: 15px;">
            <el-form-item label="身份证正面照片" label-width="140px" prop="image1">
              <el-upload
                action="http://localhost:8080/p2pInternetloan/members/mem/upload"
                list-type="picture-card"
                accept="image/*"
                name="picture"
                limit="1"
                :file-list="productImgs1"
                :multiple="isMultiple1"
                :on-preview="handlePictureCardPreview1"
                :on-remove="handleRemove1"
                :on-success="handleAvatarSuccess1"
                :before-upload="beforeAvatarUpload1"
                :on-exceed="handleExceed1"
                :on-error="imgUploadError1">
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible1">
                <img width="100%" :src="dialogImageUrl1" alt="">
              </el-dialog>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="身份证反面照片" label-width="140px" prop="image2">
              <el-upload
                action="http://localhost:8080/p2pInternetloan/members/mem/upload"
                list-type="picture-card"
                accept="image/*"
                name="picture"
                limit="1"
                :file-list="productImgs2"
                :multiple="isMultiple2"
                :on-preview="handlePictureCardPreview2"
                :on-remove="handleRemove2"
                :on-success="handleAvatarSuccess2"
                :before-upload="beforeAvatarUpload2"
                :on-exceed="handleExceed2"
                :on-error="imgUploadError2">
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible2">
                <img width="100%" :src="dialogImageUrl2" alt="">
              </el-dialog>
            </el-form-item>
          </el-col>

        </el-row>
        <div>
    <!--      <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doCancel">取消</el-button>-->
          <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem" v-if="tableForm">确定</el-button>
        </div>
      </el-form>
    </el-card>

    <!--这是带审核状态-->
    <el-card class="box-card" v-if="isIdentityAuthentication == 2" style="height: 400px;">
      <div class="clearfix" slot="header">
        <strong style="font-size: 23px;">实名认证</strong>
        <span style="float: right">材料已经提交</span>
      </div>
      <div style="text-align: center;margin-top: 55px;">
        <i class="el-icon-warning" style="font-size: 38px; color: rgb(1, 170, 237); margin-top: 30px;">身份认证信息提交完成,请耐心等待后台人员审核</i>
        <p style="color: rgb(153, 153, 153);">您的申请提交成功,申请结果将会在24小时内 短息/平台 同步,请耐心等待</p>
        <p style="color: rgb(153, 153, 153);">如有疑问 请致电 18874374737,很高效为您服务</p>
      </div>
    </el-card>

  </div>

</template>

<script>
    //导入工具包
    import commonUtils from "../../../../api/commonUtils";


    export default {
        name: "Authentication",
        data:function() {
            return {
                isIdentityAuthentication:1,//这是当前用户的认证状态
                disabled: false,
                // radio: '1',
                bornDateForm:null,
                tableForm:{
                    realname:null,
                    idNumber:null,
                    image1:null, //这是身份证正面照片
                    image2:null,//这是身份证反面照片
                },
                //这是表格是否占时加载动画
                loading:false,
                //这书数据对象（用于表格占时）
                dictList:[],
                //这是新值修改弹出
                dictDialogTitle:null,
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
                    image1: [{
                        required: true,
                        message: '只能传一张照片',
                        trigger: 'blur'
                    }],
                    image2: [{
                        required: true,
                        message: '只能传一张照片',
                        trigger: 'blur'
                    }]
                },
                //这是图片上传相关代码
                dialogImageUrl1: '',
                productImgs1: [],//这个好像是放入身份证照片地址栏的
                isMultiple1: true,
                imgLimit1: 6,
                dialogVisible1: false,
                dialogImageUrl2: '',
                productImgs2: [],//这个好像是放入身份证照片地址栏的
                isMultiple2: true,
                imgLimit2: 6,
                dialogVisible2: false,
            };
        },
        methods:{
            //这是提交就调用的方法【添加、修改通用】  --------------------
            doSubmitDictItem: function() {
                this.$refs['tableForm'].validate((valid) => {
                    if (valid) {
                        //添加省份证申请信息
                        var url =  this.axios.urls.MEMBERS_MEN_ADD;
                        this.axios.post(url, this.tableForm).then(response => {

                            //如果是操作失败
                            if (response.data.code == 500) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                });
                            } else {
                                //打印成功信息
                                this.$message({
                                    message: '材料已提交,请耐心等待审核',
                                    type: 'success'
                                });
                                this.isIdentityAuthentication = 2;
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
            doCancel() {
                this.tableForm.realname = '';
                this.bornDateForm = '';
                this.tableForm.sex = '';
                this.tableForm.address = '';
                this.tableForm.idNumber = '';
                this.tableForm.image1 = '';
                this.tableForm.image2 = '';
                this.tableForm.remark = '';
                this.bornDateForm = '';
                this.dialogFormVisible = false;
            },
            ///////////////////////////////////// 这是身份证图片上传想干代码 /////////////////////////////////////////
            handleRemove1(file, fileList) {//移除图片
                console.log(file, fileList);
            },
            handlePictureCardPreview1(file) {//预览图片时调用
                console.log(file);
                this.dialogImageUrl1 = file.url;
                this.dialogVisible1 = true;
            },

            beforeAvatarUpload1(file) {//文件上传之前调用做一些拦截限制
                console.log(file);
                const isJPG = true;
                // const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                // if (!isJPG) {
                //   this.$message.error('上传头像图片只能是 JPG 格式!');
                // }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            handleAvatarSuccess1(res, file) {//图片上传成功
                this.tableForm.image1 = res.path;
            },
            handleExceed1(files, fileList) {//图片上传超过数量限制
                this.$message.error('上传图片不能超过6张!');
                console.log(file, fileList);
            },
            imgUploadError1(err, file, fileList){//图片上传失败调用
                console.log(err)
                this.$message.error('上传图片失败!');
            },
            /////////////////////////////////上传图片///////////////////////////////////////////////////////
            handleRemove2(file, fileList) {//移除图片
                console.log(file, fileList);
            },
            handlePictureCardPreview2(file) {//预览图片时调用
                this.dialogImageUrl2 = file.url;
                this.dialogVisible2 = true;
            },

            beforeAvatarUpload2(file) {//文件上传之前调用做一些拦截限制
                console.log(file);
                const isJPG = true;
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            handleAvatarSuccess2(res, file) {//图片上传成功
               this.tableForm.image2 = res.path;
            },
            handleExceed2(files, fileList) {//图片上传超过数量限制
                this.$message.error('上传图片不能超过6张!');
                console.log(file, fileList);
            },
            imgUploadError2(err, file, fileList){//图片上传失败调用
                console.log(err)
                this.$message.error('上传图片失败!');
            }
        },
        created(){
            commonUtils.init(this);
            //这是请求到当前用户的信息，判断认证状态
            let url = this.axios.urls.MEMBERS_MEN_CURRENT_MEMBERS;
            commonUtils.doAjaxGet(url, {}, (data)=>{
                this.isIdentityAuthentication = data.isIdentityAuthentication;
            })
        }
    };
</script>
<style scoped>
</style>
