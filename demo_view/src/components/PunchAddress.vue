<template>
  <div>
    <el-cascader
      placeholder="试试搜索：九江"
      v-model="selectOption"
      :options="addressList"
      :props="addressProp"
      @change="clickNode"
      filterable
      clearable
    ></el-cascader>
  </div>
</template>

<script>
import { queryAddress } from "../api/punch";

export default {
  name: "AddressCascader",
  components: {},
  props: {},
  data() {
    return {
      selectOption: [],
      addressProp: {
        value: "name",
        label: "name",
        children: "children",
      },
      addressList: [],
    };
  },
  computed: {},
  watch: {},
  methods: {
    init() {
      queryAddress().then((res) => {
        this.addressList = res.data.data;
      });
    },
    clickNode(value) {
      this.selectOption = value;
      this.$bus.$emit("sendAddress", this.selectOption);
    },
  },
  created() {
    this.init();
  },
  mounted() {
    this.$bus.$on("restAddress", (data) => {
      this.selectOption = data
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
<style scoped>
</style>