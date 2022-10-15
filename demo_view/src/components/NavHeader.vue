<template>
  <div class="wrapRow">
    <el-tabs v-model="currentPage" @tab-click="tabClick">
      <el-tab-pane label="注册" name="register"></el-tab-pane>
      <el-tab-pane label="查询" name="record"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "NavHeader",
  components: {},
  props: {},
  data() {
    return {
      currentPage: "register",
    };
  },
  computed: {},
  watch: {},
  methods: {
    tabClick(tab) {
      this.$router.replace("/" + this.currentPage);
    },
  },
  created() {},
  mounted() {
    this.$router.replace("/" + this.currentPage);
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
<style scoped>

</style>