<template>
  <!--用户提现-->
    <div>
      <el-card style="font-size: 26px; font-weight: bold; max-height: 314px; min-height: 429px;" header="用户提现管理">
        <el-row>
          <el-col style="font-size: 19px; font-weight: lighter;" :span="8">状态：
            <el-select style="width: 38%; margin-right: 0px; margin-left: 16px;" value="1" @focus="focus">
              <el-option label="待审核" value="1"></el-option>
              <el-option label="审核通过" value="1">
              </el-option>
              <el-option label="审核不通过" value="1">
              </el-option>
            </el-select>
            <el-button style="margin-left: 13px;" type="success" icon="el-icon-document-checked" round>查询</el-button>
          </el-col>
          <el-col style="font-size: 18px; font-weight: 400;" :span="16">申请时间：
            <el-date-picker :editable="true" :clearable="true"></el-date-picker>到
            <el-date-picker :editable="true" :clearable="true"></el-date-picker>
            <el-button style="left: 0px; right: 0px; margin-left: 15px;" type="success" round icon="el-icon-document-checked">查询</el-button>
          </el-col>
        </el-row>
        <el-table :data="tableList" :fit="true" :show-header="true">
          <el-table-column prop="id" label="ID" width="140px">
          </el-table-column>
          <el-table-column prop="name" label="申请时间">
          </el-table-column>
          <el-table-column prop="id" label="提现金额">
          </el-table-column>
          <el-table-column prop="name" label="状态">
          </el-table-column>
          <el-table-column prop="id" label="审核人" width="300px">
          </el-table-column>
        </el-table>
        <el-pagination layout="prev,pager,next" :total="paginationTotal" :page-size="paginationSize" :pager-count="paginationCount" background :hide-on-single-page="true">
        </el-pagination>
      </el-card>
    </div>
</template>

<script>
    export default {
        name: "money_withdraw",
        data(){
              return {
                  tableList:[]
              }
        },
        methods:{

        }
    }
</script>

<style scoped>

</style>
