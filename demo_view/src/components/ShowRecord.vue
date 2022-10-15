<template>
  <div>
    <el-dialog title="查询结果" :visible="recordVisiable.state" width="100%">
      <el-table :data="recordData" border>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form>
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="姓名">
                    <span>{{ props.row.name }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="体温一">
                    <span>{{ props.row.tiwen }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="体温二">
                    <span>{{ props.row.tiwen2 }}</span>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="体温三">
                    <span>{{ props.row.tiwen3 }}</span>
                  </el-form-item>
                </el-col></el-row
              >
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="打卡地址">
                    <span>{{ props.row.dwaddress }}</span>
                  </el-form-item>
                </el-col></el-row
              >
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="打卡时间">
                    <span>{{ props.row.punchTime }}</span>
                  </el-form-item>
                </el-col></el-row
              >
              <el-row class="recordRow">
                <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
                  <el-form-item label="打卡结果">
                    <span>{{ props.row.state }}</span>
                  </el-form-item>
                </el-col></el-row
              >
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          property="name"
          label="姓名"
          min-width="60"
          max-width="90"
        ></el-table-column>
        <el-table-column
          property="dwaddress"
          label="打卡地址"
          min-width="180"
          max-width="270"
        ></el-table-column>
        <el-table-column
          property="punchTime"
          label="打卡时间"
          min-width="120"
          max-width="210"
        ></el-table-column>
        <el-table-column
          align="center"
          property="state"
          label="结果"
          min-width="60"
          max-width="90"
        ></el-table-column>
      </el-table>
      <el-row class="wrapRow">
        <el-pagination
          background
          layout="prev, pager, next"
          :page-count="pageTotal"
          @current-change="pageChange"
        >
        </el-pagination>
      </el-row>
      <span slot="footer">
        <el-button @click="closeRecord">取 消</el-button>
        <el-button type="primary" @click="closeRecord">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { punchRecord } from "../api/punch";
export default {
  name: "ShowRecord",
  components: {},
  props: {
    recordVisiable: {
      type: Object,
    },
    queryForm: {
      type: Object,
    },
  },
  data() {
    return {
      currentIndex: "",
      pageTotal: null,
      recordData: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    closeRecord() {
      this.recordVisiable.state = false;
    },
    pageChange(value) {
      this.queryForm.currIndex = value;
      console.log(this.queryForm);
      let json = JSON.stringify(this.queryForm);
      punchRecord(json).then((res) => {
        console.log(res.data.data.records)
        this.recordData = res.data.data.records;
        for (let index = 0; index < this.recordData.length; index++) {
          const element = this.recordData[index];
          if (element.state === "成功") {
            continue;
          }
          if (element.state === "1" || element.state === 1) {
            element.state = "成功";
          } else {
            element.state = "失败";
          }
        }
      });
    },
  },
  created() {},
  mounted() {
    this.$bus.$on("recordData", (recordData) => {
      this.pageTotal = recordData.pages;
      console.log(this.pageTotal);
      this.recordData = recordData.records;
      for (let index = 0; index < this.recordData.length; index++) {
        const element = this.recordData[index];
        if (element.state === "成功") {
          continue;
        }
        if (element.state === "1" || element.state === 1) {
          element.state = "成功";
        } else {
          element.state = "失败";
        }
      }
    });
  },
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
};
</script>
<style lang="less" scpoed>
.recordRow {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.recordRow .el-form-item {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.el-input__inner {
  display: flex;
  flex-direction: row;
  flex-shrink: 1;
  justify-content: center;
}
.el-select > .el-input {
  display: flex;
  flex-direction: row;
  flex-shrink: 1;
  justify-content: center;
}
.el-select {
  display: flex;
  flex-direction: row;
  flex-shrink: 1;
  justify-content: center;
  padding: 0;
  width: 5rem;
}
.el-form-item {
  margin-bottom: 10px;
}
</style>