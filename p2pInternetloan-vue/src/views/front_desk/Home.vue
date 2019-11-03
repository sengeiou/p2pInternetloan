<template>
  <el-main>
    <el-card class="box-card" style="border-left-width: 66px;border-right-width: 66px;border-top-width: 36px;">
      <div class="clearfix" slot="header">
        <p style="color: green;"><strong> 投 资 理 财 </strong>&nbsp;&nbsp;&nbsp;<i class="el-icon-s-promotion"></i></p>
        <!--  步骤条  -->
        <el-steps :active="stepsActive" align-center finish-status="success">
          <el-step title="投 资 详 情" icon="el-icon-reading" description="请知悉您要投资的信息，确认无误后请点击下一步！"></el-step>
          <el-step title="确 认 投 资" icon="el-icon-d-arrow-right" description="已确认无误，请点击投资即可！"></el-step>
          <el-step title="投 资 成 功" icon="el-icon-finished" description="2019-10-17"></el-step>
        </el-steps>
      </div>
      <!--  1、步骤一  这是投标信息展示模块  -->
      <el-form v-if="isShowData.basicInformation" :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-row>
          <el-card class="box-card" style="background-color: whitesmoke;">
            <!--  头 部  -->
            <div slot="header" class="clearfix">
              &nbsp;<strong>投资总额：<input style="width: 60px;" v-model="bidMoney" /> </strong> ￥
              <p align="right">—— 借款人：XX</p>
            </div>
            <!--  主 体  -->
            <div class="text item">
              <span style="color:green;"><strong>安全分数：78</strong></span><br/><br/>
              <span><strong>利息率：2.27%</strong></span><br/><br/>
              <p align="right" style="color: red;"><strong>总利息：210 ￥</strong></p>
            </div>
          </el-card>
          <!--  下一步   按钮  -->
          <el-col :span="24">
            <p align="left"><el-button @click="cancle" type="default">取消投标</el-button></p>
            <p style="color: green;" align="right">我已熟知投标信息&nbsp;&nbsp;<i class="el-icon-bottom-right"></i>&nbsp;&nbsp;&nbsp;&nbsp;
              <el-button type="success" round @click="toConfirm" style="float: right">下一步</el-button>
            </p>
          </el-col>
        </el-row>
      </el-form><br/>

      <!--  2、步骤二   这是确认信息模块   -->
      <el-row v-if="isShowData.confirm">
        <el-card class="box-card" style="background-color: whitesmoke;">
          <!--  头 部  -->
          <div slot="header" class="clearfix">
            <p>确 认 投 标 信 息 ：</p>
          </div><br/><br/><br/>
          <!--  主 体  -->
          <div class="text item">
            <el-col :span="3">&nbsp;</el-col>
            <el-col :span="8">投资总额 : 800￥</el-col>
            <el-col :span="6">利息率 : 2.27%</el-col>
            <el-col :span="6">总利息 : 100￥</el-col>
          </div><br/><br/><br/><br/><br/>
        </el-card>
        <p align="left"><el-button @click="cancle" type="default">取消投标</el-button></p>
        <div style="float: right; margin: 20px;">
          <el-button @click="back" type="default">修改投标金额</el-button>
          <el-button @click="next" icon="el-icon-check" type="success">申请投标</el-button>
        </div>
      </el-row>

      <!--  3、步骤三  这是申请成功页  -->
      <div style="text-align: center;margin-top: 40px;" v-if="isShowData.carryOut">
        <i class="el-icon-circle-check" style="font-size: 38px; color: green;">申请成功</i><br/><br/>
        <p>您的投标已提交成功，资金将会在XXX年XXX月XXX日返回到您的账户！</p><br/>
        <el-button @click="toRouter" style="margin-top: 53px;" type="text" >返回首页</el-button>
      </div>
    </el-card>
  </el-main>
</template>

<script>
    export default {
        name: "Bidding",
        data:function(){
            return {
                //步骤条 默认为第一步
                stepsActive:1,
                isShowData:{//步骤条的变动
                    basicInformation:true,
                    confirm:false,
                    carryOut:false
                }
            }
        },
        methods:{
            //返回首页
            toRouter(){
                // 跳转路由 返回首页
                this.$router.push({
                    path: '/Reception/ReceptionHome'
                });
            },
            //取消投标  返回上一界面
            cancle(){
                this.$router.go(-1);//返回上一层
            },
            //确认投标
            toConfirm(){
                this.isShowData.basicInformation = false;
                this.isShowData.confirm = true;
                this.stepsActive = 2;
            },
            //步骤条 返回修改投标金额 上一步
            back(){
                this.isShowData.basicInformation = true;
                this.isShowData.confirm = false;
                this.stepsActive = 1;
            },
            //步骤条  下一步
            next(){
                this.isShowData.confirm = false;
                this.isShowData.carryOut = true;
                this.stepsActive = 3;
            }
        },
        created() {
            commonUtils.init(this);
        }
    }
</script>

<style scoped>

</style>
