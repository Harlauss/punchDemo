<template>
  <div class="warp">
    <div class="container">
      <nav-header></nav-header>
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import NavHeader from "./components/NavHeader.vue";
export default {
  components: { NavHeader },
  props: {},
  data() {
    return {
      width: "",
    };
  },
  computed: {},
  watch: {},
  methods: {},
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
<style lang="less">
@media only screen and (max-width: 767px) {
  .el-col {
    width: 90%;
  }
  .el-picker-panel__body {
    min-width: 0 !important;
  }
  .el-date-range-picker {
    width: 400px !important;
    left: auto !important;
    margin-left: auto;
    margin-right: auto;
  }
  .el-date-range-picker__content .el-date-range-picker__header div {
    margin-left: 0;
    margin-right: 0;
  }
  .el-date-range-picker__content {
    padding: 0;
  }
}
@media only screen and (min-width: 768px) and (max-width: 1023px,) {
  .el-col {
    width: 70%;
  }
}
@media only screen and (min-width: 1024px) and (max-width: 1339px) {
  .el-col {
    width: 45%;
  }
}
@media only screen and (min-width: 1440px) and (max-width: 2559px) {
  .el-col {
    width: 35%;
  }
}
.wrapRow .el-form-item__label {
  flex-shrink: 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.wrapRow .el-form-item__content {
  display: flex;
  flex-shrink: 1;
  flex-wrap: nowrap;
  width: 100% !important;
  margin-left: 0rem !important;
}
.el-form-item {
  display: flex;
  flex-grow: 100%;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.wrapRow {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.wrapRow .leftPosition {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-content: flex-start;
}
.wrapRow .rightPosition {
  display: flex;
  flex-direction: row;
  justify-content: space-around !important;
  align-content: flex-start;
}
.wrapRow .headCol {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
}
.wrapRow .headCol .temperatureCol {
  display: flex;
  flex-shrink: 1;
  margin-right: 1rem;
}
.wrapRow .inputCol {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
</style>
