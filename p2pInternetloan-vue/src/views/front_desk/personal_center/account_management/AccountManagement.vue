<el-container>
  <el-main style="left: 0px; position: relative; top: 32px;">
    <el-menu style="width: 100%;" mode="horizontal">
      <el-menu-item style="height: 50px;" index>晚上好，tg_gpdt0139 喝一杯下午茶，让心情放松一下~</el-menu-item>
      <el-menu-item style="width: 40%; height: 50px;" index>
      </el-menu-item>
      <el-menu-item style="height: 50px;" index>上次登录时间： 2015-09-11 14:05:07</el-menu-item>
    </el-menu>
    <el-row>
      <el-col :span="8">
        <el-card header="可用余额">
          <strong>{{msg}}</strong>
          <strong>元</strong>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card header="代收利息">
          <strong>00</strong>
          <strong>元</strong>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card header="代收本金">
          <strong>00</strong>
          <strong>元</strong>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card header="账户授信额度">
          <strong>00</strong>
          <strong>元</strong>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card header="累计收益">
          <!--<strong style="color: rgb(204, 0, 0);">00</strong>-->
          <!--<strong style="color: rgb(204, 0, 0);">元</strong>-->
          <strong>00</strong>
          <strong>元</strong>
        </el-card>
      </el-col>
    </el-row>
    <el-tabs style="position: relative; left: 13px; color: rgb(57, 61, 73);" value="first">
      <el-tab-pane name="1" style="left: 20px;" label="回款计划">
      </el-tab-pane>
      <el-tab-pane name="2" label="资金记录">
      </el-tab-pane>
      <el-tab-pane name="3" label="投资记录">
      </el-tab-pane>
    </el-tabs>
  </el-main>
  <el-footer>
  </el-footer>
</el-container>

<script>
    // new Vue({
    //     el: "#app",
    //     data() {
    //         return {
    //             msg: 'hello vue',
    //
    //         };
    //     }
    // });

    export default {
        el: "#app",
        data: function () {
            return {
                msg: "qqqqqqqqqq"
            }
        }
    }
</script>
