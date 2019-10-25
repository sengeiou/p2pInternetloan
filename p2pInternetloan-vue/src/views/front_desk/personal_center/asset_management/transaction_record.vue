<template>
  <el-tabs value="first" editable addable closable type="border-card">
    <el-tab-pane name="first" label="充值记录">
      <el-row>
        <el-col style="font-weight: bold;" :span="12">充值交易ID：
          <el-input style="height: 0px; width: 58%;" placeholder="请输入内容" size="medium" clearable show-word-limit type="text"></el-input>
          <el-button style="right: 275px; margin-right: -50px;" :autofocus="true" plain type="success" :native-type="submit">查询</el-button>
        </el-col>
        <el-col style="font-weight: bold;" :span="12">充值交易时间：
          <el-input style="height: 6px; width: 58%;" placeholder="请输入内容" size="medium" clearable show-word-limit type="text"></el-input>
          <el-button style="right: 275px; margin-right: -50px;" :autofocus="true" plain type="success" :native-type="submit">查询</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
    <el-tab-pane name="second" label="提现记录">
      <el-row>
        <el-col style="font-weight: bold;" :span="12">提现交易ID：
          <el-input style="height: 0px; width: 58%;" placeholder="请输入内容" size="medium" clearable show-word-limit></el-input>
          <el-button style="right: 275px; margin-right: -50px;" :autofocus="true" plain type="success" :native-type="submit">查询</el-button>
        </el-col>
        <el-col style="font-weight: bold;" :span="12">提现交易时间：
          <el-input style="height: 6px; width: 58%;" placeholder="请输入内容" size="medium" clearable show-word-limit></el-input>
          <el-button style="right: 275px; margin-right: -50px;" :autofocus="true" plain type="success" :native-type="submit">查询</el-button>
        </el-col>
      </el-row>
    </el-tab-pane>
  </el-tabs>
  <hr>
  </hr>
  <!-- 分页 -->
  <el-table style="margin-bottom: 43px; padding-top: 0px; margin-left: 0px;" :data="dictList" height="440" :fit="true" :show-header="true" v-loading="loading" show-header="true" highlight-current-row>
    <el-table-column prop="id" label="ID" min-width="1.5">
    </el-table-column>
    <el-table-column prop="title" label="支付宝唯一标识（账号）" min-width="4">
    </el-table-column>
    <el-table-column prop="sn" label="交易时间" min-width="4">
    </el-table-column>
    <el-table-column prop="description" label="交易金额" min-width="4">
    </el-table-column>
    <el-table-column label="操作" min-width="4">
      <template slot-scope="scope">
      </template>
    </el-table-column>
  </el-table>
  <el-pagination layout="prev,pager,next" :total="paginationTotal" :page-size="paginationSize" :pager-count="paginationCount" background :hide-on-single-page="true">
  </el-pagination>
</template>

<script>
  import Qs from 'qs'

    export default {
        name: "transaction_record",
        data:{

        },
      methods:{
          
      }
    }
</script>

<style scoped>

</style>
