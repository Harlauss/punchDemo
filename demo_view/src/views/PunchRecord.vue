<template>
  <div>
    <div class="wrap">
      <el-form ref="queryForm" :model="queryForm" :rules="queryRule">
        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="学号" prop="id" class="headCol">
              <el-input
                v-model="queryForm.id"
                placeholder="请输入学号"
                class="inputCol"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="时间" prop="time" class="headCol">
              <el-date-picker
                v-model="value"
                type="daterange"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                format="yyyy-MM-dd HH:mm:ss"
                class="timeCol"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div>
      <el-row class="wrapRow">
        <el-col class="rightButton">
          <el-button
            type="primary"
            icon="el-icon-search"
            round
            size="medium"
            @click="userRecord"
            >查询</el-button
          >
        </el-col>
      </el-row>
    </div>

    <show-record :recordVisiable="recordVisiable" :queryForm="queryForm">
    </show-record>
  </div>
</template>
<script>
import DateformatUtil from "dateformat-util";
import { punchRecord, defaultPunchRecord } from "../api/punch";
import ShowRecord from "../components/ShowRecord.vue";

export default {
  name: "PunchRecord",
  components: { ShowRecord },
  props: {},
  data() {
    var StuIdValid = (rule, value, callback) => {
      if (value) {
        if (/[0-9]{10}/.test(value)) {
          callback();
        } else {
          this.$message.error("请输入正确的学号 ");
        }
      } else {
        this.$message.error("请输入学号");
      }
    };
    return {
      queryRule: {
        id: [
          {
            validator: StuIdValid,
            trigger: "blur",
          },
        ],
      },
      queryForm: {
        id: "",
        startTime: "",
        endTime: "",
      },
      value: [],
      recordVisiable: {
        state: false,
      },
    };
  },
  computed: {},
  watch: {},
  methods: {
    userRecord() {
      this.$refs["queryForm"].validate((valid) => {
        if (valid) {
          if (this.value.length <= 0) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            this.queryForm.endTime = DateformatUtil.format(
              end,
              "yyyy-MM-dd hh:mm:ss"
            );
            this.queryForm.startTime = DateformatUtil.format(
              start,
              "yyyy-MM-dd hh:mm:ss"
            );
          } else {
            this.queryForm.startTime = DateformatUtil.format(
              this.value[0],
              "yyyy-MM-dd hh:mm:ss"
            );
            this.queryForm.endTime = DateformatUtil.format(
              this.value[1],
              "yyyy-MM-dd hh:mm:ss"
            );
          }
          let json = JSON.stringify(this.queryForm);
          punchRecord(json).then((res) => {
            this.recordVisiable.state = true;
            this.$bus.$emit("recordData", res.data.data);
          });
        }
      });
    },
  },
  created() {},
  mounted() {},
  beforeCreate() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeDestroy() {},
  destroyed() {},
  activated() {},
};
</script>
<style lang="less" scoped>
.wrapRow .timeCol {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  flex-grow: 1;
  flex-shrink: 1;
}
.wrapRow .rightButton {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
}
.el-date-editor--daterange.el-input,
.el-date-editor--daterange.el-input__inner,
.el-date-editor--timerange.el-input,
.el-date-editor--timerange.el-input__inner {
  width: 100%;
}
</style>