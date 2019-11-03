<template>
  <div>
<el-card class="box-card">
  <div class="clearfix" slot="header">
    <strong style="font-size: 23px;">实名认证</strong>
  </div>
  <span style="color: rgb(241, 2, 2);">为了保障您的安全,实名认证成功后不能修改,请认真填写</span>
  <el-form style="margin-top: 22px;" label-width="100px" :model="tableForm" :rules="rules" ref="tableForm" :visible="dialogFormVisible" @close="doCancel" v-if="isShow">
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
            <el-date-picker placeholder="选择日期" v-model="bornDateForm" autocomplete="off">
            </el-date-picker>
          </div>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="性别" prop="sex">
<!--          <el-radio-group v-model="tableForm.sex" autocomplete="off">-->
<!--            <el-radio label="1">男</el-radio>-->
<!--            <el-radio label="2">女</el-radio>-->
<!--          </el-radio-group>-->
          <template>
            <el-radio v-model="tableForm.sex" label="男">男</el-radio>
            <el-radio v-model="tableForm.sex" label="女">女</el-radio>
          </template>
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

          <el-upload :on-change="handleRemove" v-model="tableForm.image1" action="#" list-type="picture-card" :auto-upload="false">
          <i slot="default" class="el-icon-plus" ></i>
          <div slot="file" slot-scope="{file}" >
            <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
            <span class="el-upload-list__item-actions" >
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
          <el-upload  :on-change="handleRemove" v-model="tableForm.image2" action="#" list-type="picture-card" :auto-upload="false">
            <i slot="default" class="el-icon-plus" ></i>
            <div slot="file" slot-scope="{file}" >
              <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
              <span class="el-upload-list__item-actions" >
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
<!--      <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doCancel">取消</el-button>-->
      <el-button style="float: right;" icon="el-icon-circle-check" round type="success" @click="doSubmitDictItem" v-if="tableForm">确定</el-button>
    </div>
  </el-form>
</el-card>


    <el-dialog top="20vh" width="50%" :title="dialogTitle" :visible="dialogFormVisible" v-if="aaa">
      <el-form :model="tableForm" :rules="rules" ref="tableForm">
        <span style="color: rgb(241, 2, 2);">待审核</span>
      </el-form>
    </el-dialog>
  </div>

</template>

<script>
    //导入工具包
    import commonUtils from "../../../../api/commonUtils";


    export default {
        name: "Authentication",
        data:function() {

            return {
                isShow:true,
                dialogImageUrl: '',
                dialogVisible: false,
                disabled: false,
                // radio: '1',
                bornDateForm:null,
                tableForm:{
                    id:null,
                    realname:null,
                    sex:null,
                    bornDate:null,
                    idNumber:null,
                    address:null,
                    remark:null,
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
                    image1: [{
                        required: true,
                        message: '只能传一张照片',
                        trigger: 'blur'
                    }],
                    image2: [{
                        required: true,
                        message: '只能传一张照片',
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

            };
        },
        methods:{
            handleRemove(file) {
                console.log('11111111111111111');
                console.log(this.tableForm.image2);
                console.log(this.tableForm.image1);

                this.tableForm.image2=file.url;
                this.tableForm.image1 = file.url;
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
                this.tableForm.image2=file.url;
                this.tableForm.image1 = file.url;
            },
            handleDownload(file) {
                this.tableForm.image2=file.url;
                this.tableForm.image1 = file.url;
            },

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
                        this.tableForm.bornDate = commonUtils.formatDate(this.bornDateForm, "yyyy/MM/dd");
                        //发送请求  、、
                        this.image2="test";
                        this.image1="test";
                        console.log('------test');
                        console.log(this.tableForm);

                        this.axios.post(url, this.tableForm).then(response => {

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

                                this.doCancel();
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
        }
    };
</script>
<style scoped>
</style>
