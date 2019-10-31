<template>
  <div  style="padding: 20px;background-color: #FFFFFF">

    <el-container>
      <el-header style="background-color: rgb(0, 186, 189); min-height: 0px;" height="46px">
        <el-page-header style="text-align: center; font-size: 0px; white-space: pre-wrap; text-decoration: blink; margin-bottom: 0px; padding-bottom: 0px;" content="设置管理">
        </el-page-header>
      </el-header>
      <el-container>
        <el-aside style="background-color: rgb(255, 255, 255);" width="300px">
          <el-menu mode="vertical">
            <el-submenu index="1">
              <span class="magical-drag-tmp-submenu-name" slot="title" >可用额度规则设置</span>
            </el-submenu>
            <el-submenu index="2">
              <!--<span class="magical-drag-tmp-submenu-name" slot="title">费率设置</span>-->

              <router-link class="magical-drag-tmp-submenu-name" slot="title" tag="li" :to="{path:'/retasset'}">费率设置</router-link>

            </el-submenu>
            <el-menu-item index="3">筹款期限设置</el-menu-item>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main style="background-color: rgb(255, 255, 255); min-height: 457px;">
            <el-form :model="dictItemForm" >
            <el-row style="line-height: 0px; min-height: 314px; height: 257px; margin-bottom: -29px; margin-top: 0px;">
              <el-col :span="21">用户初始可用额度规则：
                <el-input id="text1" v-model="dictItemForm.useable_min_limit" v_bind="disable=false" style="width: 40%; left: -29px; margin-left: 29px;" placeholder="请输入内容"></el-input>
                <el-button type="danger" @click="update1">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit1" >确定</el-button>
              </el-col>
              <el-col style="line-height: 77px; padding-bottom: 0px; bottom: 0px; height: 57px;" :span="24">每次还款后的额度加成：
                <el-input  id="text2" v-model="dictItemForm.interest_rate" style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容" ></el-input>
                <el-button type="danger" @click="update1">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit2">确定</el-button>
              </el-col>
              <el-col style="line-height: 100px; padding-bottom: 0px; bottom: 0px; height: 114px; letter-spacing: 1px; text-indent: 4px; min-height: 0px;" :span="24">每次投资的额度加成：
                <el-input  id="text3" v-model="dictItemForm.request_rate" style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容"></el-input>
                <el-button type="danger" @click="update1">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit3">确定</el-button>
              </el-col>
            </el-row>
            </el-form>
          </el-main>
          <el-footer style="background-color: rgb(255, 255, 255);" height="0px">
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>


<script>
    export default {
      name: "setting",
      data() {
          return{
            set:{
              useable_min_limit:null,
              interest_rate:null,
              request_rate:null
            }
          }
      },
      methods: {
        // //点击修改进行禁用输入框
        // update1() {
        //
        // },

        onsubmit() {
          this.search();
        },

        certified: function () {
          this.set.credit;
        },

        search: function () {
          this.$refs['tableForm'].validate((valid) => {
            var url = null;
            url = this.axios.urls.SETTING_SETTING_SETMINLIMIT;
            //发送请求
            this.axios.post(url, {
              id: this.dictItemForm.id,
              state: 0
            }).then(response => {
              //这里是操作成功
              this.dictItemForm();
              this.disable = false;
              //重新查找
              this.search();
              //打印成功信息
              this.$message({
                message: '设置成功',
                type: 'success'
              });
            }).catch(function (error) {
              console.log(error);
            });
          });
        }
      }
    }
</script>

<style scoped>

</style>
