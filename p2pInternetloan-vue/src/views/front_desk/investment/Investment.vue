<template>
  <el-main>
    <el-card class="box-card" style="margin-top: 25px;">
      <div class="clearfix" slot="header">
        <span>快速筛选</span>
        <i style="float: right; padding: 3px 0" type="text">精选优质债权，网贷有风险，投资需谨慎</i>
      </div>
      <el-form :model="dictForm" :rules="dictRules" ref="dictForm">
        <el-row>
          <el-col :span="6">
            <el-form-item label="项目类型" label-width="100px">
              <el-select value="1" @focus="focus">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="年利率" label-width="100px">
              <el-select value="1" @focus="focus">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="期限" label-width="100px">
              <el-select value="1" @focus="focus">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="还款方式" label-width="100px">
              <el-select value="1" @focus="focus">
                <el-option label="选择1" value="1">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <el-card class="box-card" style="margin-top: 25px;">
      <div class="clearfix" slot="header">
        <span>卡片名称</span>
      </div>
      <el-table :data="tableList" :fit="true" :show-header="true">
        <el-table-column prop="id" label="列1">
        </el-table-column>
        <el-table-column prop="name" label="列2">
        </el-table-column>
      </el-table>
    </el-card>
  </el-main>
</template>

<script>
    export default {
        name: "Investment"
    }
</script>

<style scoped>

</style>
