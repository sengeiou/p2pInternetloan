<template>
    <div>
      <!--这是轮播图 start-->
      <el-carousel :autoplay="  true" :loop="true">
        <el-carousel-item >
          <img src="../../images/test.jpg">
        </el-carousel-item>
        <el-carousel-item>
          <img src="../../images/test.jpg">
        </el-carousel-item>
        <el-carousel-item>
          <img src="../../images/test.jpg">
        </el-carousel-item>
      </el-carousel>
      <!--这是轮播图 end-->
      <el-col :span="24">
        <el-row style="margin-top: 18px;">
          <el-col style="text-indent: 48px; font-size: 36px;" :span="8">
            <i class="el-icon-s-flag" style="text-indent: 9px;">投资理财</i>
          </el-col>
          <el-col style="text-indent: 48px; font-size: 36px;" :span="8">
            <i class="el-icon-s-marketing" style="text-indent: 13px;">高收益</i>
          </el-col>
          <el-col style="text-indent: 48px; font-size: 36px;" :span="8">
            <i class="el-icon-s-finance">随时随地</i>
          </el-col>
        </el-row>
      </el-col>
      <el-card style="margin-top: 120px;">
        <el-col style="font-size: 28px; color: rgb(245, 201, 88);" :span="8">
          <i class="el-icon-user">信贷项目推荐</i>
        </el-col>
        <el-table :data="tableList" :fit="true" :show-header="true">
          <el-table-column prop="id" label="列1">
          </el-table-column>
          <el-table-column prop="name" label="列2">
          </el-table-column>
        </el-table>
      </el-card>
      <el-card style="margin-top: 20px;">
        <el-col style="font-size: 28px; color: rgb(245, 201, 88);" :span="8">
          <i class="el-icon-truck">车待项目推荐</i>
        </el-col>
        <el-table :data="tableList" :fit="true" :show-header="true">
          <el-table-column prop="id" label="列1">
          </el-table-column>
          <el-table-column prop="name" label="列2">
          </el-table-column>
        </el-table>
      </el-card>
      <el-card style="margin-top: 20px;">
        <el-col style="font-size: 28px; color: rgb(245, 201, 88);" :span="8">
          <i class="el-icon-school">房待项目推荐</i>
        </el-col>
        <el-table :data="tableList" :fit="true" :show-header="true">
          <el-table-column prop="id" label="列1">
          </el-table-column>
          <el-table-column prop="name" label="列2">
          </el-table-column>
        </el-table>
      </el-card>
    </div>
</template>

<script>
    import Qs from 'qs'


    export default {
        name: "Home",
        data:{
            treeNodeLst:null,
            tableList:null
        },
        methods:{

        }

    }
</script>

<style scoped>

</style>
