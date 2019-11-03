<template>
  <div  style="padding: 20px;background-color: #FFFFFF">

    <el-container>
      <el-header style=" min-height: 0px;" height="46px">
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
              <!--<span class="magical-drag-tmp-submenu-name" slot="title" @click="onfeilvset()">费率设置</span>-->
              <router-link class="magical-drag-tmp-submenu-name" slot="title" tag="li" :to="{path:'/ratesset'}">费率设置</router-link>

            </el-submenu>
            <el-menu-item index="3">筹款期限设置</el-menu-item>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main style="background-color: rgb(255, 255, 255); min-height: 457px;">
            <el-form :date="value" >
            <el-row style="line-height: 0px; min-height: 314px; height: 257px; margin-bottom: -29px; margin-top: 0px;">
              <el-col :span="21">
                <label>用户初始可用额度规则：</label>
                <el-input id="text1" prop="useable_min_limit"  style=" width: 40%; left: -29px; margin-left: 29px;" placeholder="请输入内容" disabled="true"></el-input>
                <el-button type="danger" @click="update1">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit1()" v-bind="disabled"  >确定</el-button>
              </el-col>
              <el-col style="line-height: 77px; padding-bottom: 0px; bottom: 0px;    height: 57px;" :span="24">
                <label>每次还款后的额度加成：</label>
                <el-input  id="text2" prop="Credit_line_plus"  style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容" disabled="true"></el-input>
                <el-button type="danger" @click="update2">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit22()">确定</el-button>
              </el-col>
              <el-col style="line-height: 100px; padding-bottom: 0px; bottom: 0px; height: 114px; letter-spacing: 1px; text-indent: 4px; min-height: 0px;" :span="24" >
                <label>每次投资的额度加成：</label>
                <el-input  id="text3"  prop = "Investment_amount_plus"  style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容" disabled="true"></el-input>
                <el-button type="danger" @click="update3">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success" @click="onsubmit33()">确定</el-button>
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
              value:[{
                useable_min_limit: null,
                Credit_line_plus:null,
                Investment_amount_plus:null
              }],

          };
      },
      methods: {
        //消息提示
        open11:function() {
          this.$message({
            message: '更改成功，输入框已禁用，如需更改，请点击修改',
            type: 'success'
          });
        },

        update1(){
          let wechatId = document.getElementById('text1');
          wechatId.disabled = false;
        },
        onsubmit1(){
          let wechatId = document.getElementById('text1');
          wechatId.disabled = true;
          this.open11()
          },
        update2(){
          let wechatId = document.getElementById('text2');
          wechatId.disabled = false;
        },
        onsubmit22(){
          let wechatId = document.getElementById('text2');
          wechatId.disabled = true;
          this.open11()
        },
        update3(){
          let wechatId = document.getElementById('text3');
          wechatId.disabled = false;
        },
        onsubmit33(){
          let wechatId = document.getElementById('text3');
          wechatId.disabled = true;
          // this.uodaetesetting()
          this.open11()
        },


        //这是搜索设置的方法
        search: function() {
          let url = this.axios.urls.SETTING_SETTING_QUERY;
          let params = {
            useable_min_limit:this.tableForm.useable_min_limit,
            interest_rate:this.tableForm.interest_rate,
            request_rate:this.tableForm.request_rate,
            useable_max_limit:this.tableForm.useable_max_limit,
            min_limit:this.tableForm.min_limit,
            interest_rate:this.tableForm.interest_rate,
            request_rate:this.tableForm.request_rate,
            request_precedure_limit:this.tableForm.request_precedure_limit,
            withdraw_precedure_limit:this.tableForm.withdraw_precedure_limit,
            ramaining_limit:this.tableForm.ramaining_limit
          }

          //查询动画
          // this.loading = true;
          //向后端请求数据
          this.axios.get(url,{params:params}).then(response => {
            this.tableForm = response.data.data;
            // this.tableForm.total = response.data.total;
            //数据查询到了关闭查询动画
            this.loading = false;
          }).catch(function(error) {
            console.log(error);
          });
        },

        //更改设置
        uodaetesetting: function() {
          let url = this.axios.urls.SETTING_SETTING_SETMINLIMIT;
          let params = {
            useable_min_limit:this.tableForm.useable_min_limit,
            interest_rate:this.tableForm.interest_rate,
            request_rate:this.tableForm.request_rate
          }

          //查询动画
          // this.loading = true;
          //向后端请求数据
          this.axios.get(url,{params:params}).then(response => {
            this.tableForm = response.data.data;
            // this.tableForm.total = response.data.total;
            //数据查询到了关闭查询动画
            this.loading = false;
          }).catch(function(error) {
            console.log(error);
          });
        },
      },
      created(){
        this.search();

      }
    }
</script>

<style scoped>

</style>
