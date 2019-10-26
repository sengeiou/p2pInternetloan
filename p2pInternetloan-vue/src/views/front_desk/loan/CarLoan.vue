<template>
  <el-main>
    <el-card class="box-card">
      <el-steps :active="stepsActive">
        <el-step title="填写申请借款表单" icon="el-icon-edit">
        </el-step>
        <el-step title="填写借贷车辆信息" icon="el-icon-truck">
        </el-step>
        <el-step title="确认贷款信息" icon="el-icon-star-off">
        </el-step>
        <el-step title="完成" icon="el-icon-circle-check">
        </el-step>
      </el-steps>
      <!--这是填写基本信息模块-->
      <el-form v-if="isShowData.basicInformation"  :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-row>
          <el-col :span="24">
            <el-form-item label="借款标题" label-width="100px">
              <el-input v-model="input" placeholder="请输入借款标题">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="借款金额" label-width="100px">
              <el-input v-model="input" placeholder="请输入借款金额">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年利率" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" size="200px">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="还款方式" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" placeholder="sdaf">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="还款期限" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" placeholder="sdaf">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="借款说明" label-width="100px">
              <el-input type="textarea" :rows="4">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-button @click="toVehicleInformation" style="float: right">下一步</el-button>
          </el-col>
        </el-row>
      </el-form>

      <!--这是填写车辆信息-->
      <el-form v-if="isShowData.vehicleInformation" :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="车辆品牌" label-width="100px">
              <el-input v-model="input" placeholder="请输入借款标题">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车牌号" label-width="100px">
              <el-input v-model="input" placeholder="请输入借款金额">
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车系" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" size="200px">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="公里数" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" placeholder="sdaf">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车辆购买价格" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" placeholder="sdaf">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="车辆购买时间" label-width="100px">
              <el-select style="width: 100%;" value="1" @focus="focus" placeholder="sdaf">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="车辆图片" limit="3" label-width="100px">
              <el-upload action="#" list-type="picture-card" :auto-upload="false">
                <i class="el-icon-plus" slot="default">
                </i>
                <div slot-scope="{file}">
                  <img class="el-upload-list__item-thumbnail" :src="file.url" alt />
                </div>
              </el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-button style="float: right" @click="toConfirm">下一步</el-button>
            <el-upload action="https://jsonplaceholder.typicode.com/posts/" :file-list="fileList" :show-file-list="true" :auto-upload="true">
            </el-upload>
          </el-col>
        </el-row>
      </el-form>

      <!--这是确认信息页-->
      <el-row v-if="isShowData.confirm">
        <el-col :span="8">贷款总额:800$</el-col>
        <el-col :span="8">应还本金:600$</el-col>
        <el-col :span="8">总利息:100$</el-col>
        <el-table style="margin-top:30px;" :data="tableList" :fit="true" :show-header="true">
          <el-table-column prop="id" label="还款期">
          </el-table-column>
          <el-table-column prop="name" label="应还本金">
          </el-table-column>
          <el-table-column prop="name" label="应还利息">
          </el-table-column>
          <el-table-column prop="name" label="共计">
          </el-table-column>
        </el-table>
        <div style="float: right; margin: 20px;">
          <el-button @click="toCarryOut" icon="el-icon-success" type="success">申请借贷</el-button>
        </div>
      </el-row>

      <!--这是申请成功页-->
      <div style="text-align: center;margin-top: 40px;" v-if="isShowData.carryOut">
        <i class="el-icon-circle-check" style="font-size: 38px; color: rgb(14, 239, 59);">申请成功</i>
        <p style="color: rgb(153, 153, 153);">您的申请提交成功,申请结果将会在24小时内 短息/平台 同步,请耐心等待</p>
        <p style="color: rgb(153, 153, 153);">如有疑问 请致电 18874374737,很高效为您服务</p>
        <el-button @click="toRouter('/Home')" style="margin-top: 53px;" type="text" >返回首页</el-button>
      </div>
    </el-card>
  </el-main>
</template>

<script>
    import commonUtils from "../../../api/commonUtils";
    export default {
        name: "CreditLoan",
        data:function(){
            return {
                stepsActive:1,
                isShowData:{
                    basicInformation:true,
                    vehicleInformation: false,
                    confirm:false,
                    carryOut:false
                }
            }
        },
        methods:{
            toRouter(routerPath){
                commonUtils.toRouter(routerPath)
            },
            //这是条车辆信息页
            toVehicleInformation(){
                this.isShowData.basicInformation = false;
                this.isShowData.vehicleInformation = true;
                this.stepsActive = 2;
            },
            //条确认借贷信息页
            toConfirm(){
                this.isShowData.vehicleInformation = false;
                this.isShowData.confirm = true;
                this.stepsActive = 3;
            },
            //跳入到完成
            toCarryOut(){
                this.isShowData.confirm = false;
                this.isShowData.carryOut = true;
                this.stepsActive = 4;
            }
        },
        created() {
            commonUtils.init(this);
        }
    }
</script>

<style scoped>

</style>

