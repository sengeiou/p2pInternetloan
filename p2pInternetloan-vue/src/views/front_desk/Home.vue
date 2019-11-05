<template>
  <el-main>
    <form>
      <el-form-item label="图片">
        <el-upload
          ref="upload"
          action="/upload.do"
          name="picture"
          list-type="picture-card"
          :limit="1"
          :file-list="fileList"
          :on-exceed="onExceed"
          :before-upload="beforeUpload"
          :on-preview="handlePreview"
          :on-success="handleSuccess"
          :on-remove="handleRemove">
          <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </el-form-item>
    </form>
  </el-main>
</template>

<script>
    export default {
        name: "Bidding",
        data:function(){
            return{
                //文件上传的参数
                dialogImageUrl: '',
                dialogVisible: false,
                //图片列表（用于在上传组件中回显图片）
                fileList: [{name: '', url: ''}],
            }
        },
        methods:{
            //文件上传成功的钩子函数
            handleSuccess(res, file) {
                this.$message({
                    type: 'info',
                    message: '图片上传成功',
                    duration: 6000
                });
                if (file.response.success) {
                    this.editor.picture = file.response.message; //将返回的文件储存路径赋值picture字段
                }
            },
            //删除文件之前的钩子函数
            handleRemove(file, fileList) {
                this.$message({
                    type: 'info',
                    message: '已删除原有图片',
                    duration: 6000
                });
            },
            //点击列表中已上传的文件事的钩子函数
            handlePreview(file) {
            },
            //上传的文件个数超出设定时触发的函数
            onExceed(files, fileList) {
                this.$message({
                    type: 'info',
                    message: '最多只能上传一个图片',
                    duration: 6000
                });
            },
            //文件上传前的前的钩子函数
            //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
            beforeUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isGIF = file.type === 'image/gif';
                const isPNG = file.type === 'image/png';
                const isBMP = file.type === 'image/bmp';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG && !isGIF && !isPNG && !isBMP) {
                    this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
            },
        },
        created() {
        }
    }
</script>

<style scoped>

</style>
