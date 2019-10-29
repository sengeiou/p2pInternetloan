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
            <el-row style="line-height: 0px; min-height: 314px; height: 257px; margin-bottom: -29px; margin-top: 0px;">
              <el-col :span="21">用户可用额度规则设置：
                <el-input style="width: 40%; left: -29px; margin-left: 29px;" placeholder="请输入内容"></el-input>
                <el-button type="danger">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success">确定</el-button>
              </el-col>
              <el-col style="line-height: 77px; padding-bottom: 0px; bottom: 0px; height: 57px;" :span="24">每次还款后的额度加成：
                <el-input style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容" ></el-input>
                <el-button type="danger">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success">确定</el-button>
              </el-col>
              <el-col style="line-height: 100px; padding-bottom: 0px; bottom: 0px; height: 114px; letter-spacing: 1px; text-indent: 4px; min-height: 0px;" :span="24">每次投资的额度加成：
                <el-input style="width: 35%; left: -29px; margin-left: 29px;" placeholder="请输入内容"></el-input>
                <el-button type="danger">修改</el-button>
                <el-button style="right: 0px; margin-right: 0px; padding-right: 29px; margin-left: 29px;" type="success">确定</el-button>
              </el-col>
            </el-row>
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

      },
      methods: {

      }
    }
</script>

<style scoped>

</style>
