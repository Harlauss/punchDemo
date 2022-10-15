<template>
  <div>
    <div class="wrap">
      <el-form ref="userForm" :model="userForm" :rules="userRule">
        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="易班账号" prop="phone" class="headCol">
              <el-input
                v-model="userForm.phone"
                placeholder="输入易班账号"
                class="inputCol"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="易班密码" prop="password" class="headCol">
              <el-input
                v-model="userForm.password"
                placeholder="输入易班密码"
                class="inputCol"
                show-password
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="三次体温" prop="" class="headCol">
              <el-form-item label="" prop="tiwen">
                <el-input
                  v-model="userForm.tiwen"
                  placeholder="30-49.9"
                  class="temperatureCol"
                ></el-input>
              </el-form-item>

              <el-form-item label="" prop="tiwen2">
                <el-input
                  v-model="userForm.tiwen2"
                  placeholder="30-49.9"
                  class="temperatureCol"
                ></el-input>
              </el-form-item>

              <el-form-item label="" prop="tiwen3">
                <el-input
                  v-model="userForm.tiwen3"
                  placeholder="30-49.9"
                  class="temperatureCol"
                ></el-input>
              </el-form-item>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row class="wrapRow">
          <el-col :xl="8" :lg="8" :md="10" :sm="10" :xs="15">
            <el-form-item label="打卡地址" prop="city" class="headCol">
              <el-form-item label="" prop="prefixAddress">
                <address-cascader> </address-cascader>
              </el-form-item>
              <el-form-item label="" prop="suffixAddress">
                <el-input
                  v-model="userForm.suffixAddress"
                  placeholder="填写详细地址"
                ></el-input>
              </el-form-item>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>

    <div>
      <el-row class="wrapRow">
        <el-col class="leftPosition">
          <el-button
            type="info"
            icon="el-icon-refresh"
            round
            size="medium"
            @click="restForm"
            class="leftButton"
            >重置</el-button
          >
        </el-col>
        <el-col class="rightPosition">
          <el-button
            type="primary"
            icon="el-icon-position"
            round
            size="medium"
            id="submit"
            class="rightButton"
            >提交</el-button
          >
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import AddressCascader from "../components/PunchAddress.vue";
import { submitUserForm } from "../api/punch";

export default {
  name: "PunchRegister",
  components: { AddressCascader },
  props: {},
  data() {
    var phoneValid = (rule, value, callback) => {
      if (value) {
        if (/^1[3456789]\d{9}$/.test(value)) {
          callback();
        } else {
          this.$message.error("请输入正确的账号");
        }
      } else {
        this.$message.error("请输入账号");
      }
    };
    var passwordValid = (rule, value, callback) => {
      if (value) {
        callback();
      } else {
        this.$message.error("请输入密码");
      }
    };
    var tiwenValid = (rule, value, callback) => {
      if (value) {
        if (/[3-4][0-9](\.[0-9])?/.test(value)) {
          callback();
        } else {
          this.$message.warning("请输入30-49.9之间的体温");
        }
      } else {
        this.$message.error("请输入体温");
      }
    };
    var prefixAddressValid = (rule, value, callback) => {
      if (value) {
        callback();
      } else {
        this.$message.error("请选择城市");
      }
    };
    var suffixAddressVaild = (rule, value, callback) => {
      if (value) {
        callback();
      } else {
        this.$message.error("请输入详细地址");
      }
    };
    return {
      userForm: {
        phone: "",
        password: "",
        tiwen: "",
        tiwen2: "",
        tiwen3: "",
        prefixAddress: "",
        suffixAddress: "",
        dwaddress: "",
        wxprovince: "",
        wxcity: "",
      },
      userRule: {
        phone: [
          {
            validator: phoneValid,
            trigger: "blur",
          },
        ],
        password: [
          {
            validator: passwordValid,
            trigger: "blur",
          },
        ],
        tiwen: [
          {
            validator: tiwenValid,
            trigger: "blur",
          },
        ],
        tiwen2: [
          {
            validator: tiwenValid,
            trigger: "blur",
          },
        ],
        tiwen3: [
          {
            validator: tiwenValid,
            trigger: "blur",
          },
        ],
        prefixAddress: [
          {
            validator: prefixAddressValid,
            trigger: "blur",
          },
        ],
        suffixAddress: [
          {
            validator: suffixAddressVaild,
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {},
  watch: {},
  methods: {
    restForm() {
      this.$bus.$emit("restAddress", "");
      this.userForm = {};
      this.submitData = {};
    },
    userRegister() {
      this.$refs.userForm.validate((valid) => {
        this.userForm.dwaddress =
          this.userForm.prefixAddress + this.userForm.suffixAddress;
        let address = this.userForm.prefixAddress.split("/");
        this.userForm.wxprovince = address[0];
        this.userForm.wxcity = address[1];
        this.userForm.dwaddress = this.userForm.dwaddress.replaceAll("/", "");
        let userList = [];
        userList.push(this.userForm);
        let json = JSON.stringify(userList);
        submitUserForm(json).then((res) => {
          if (res.data.code !== 200) {
            this.$message.error(res.data.message);
          } else {
            this.restForm();
            this.$message.success(res.data.message);
          }
        });
      });
    },
  },
  created() {},
  mounted() {
    document
      .getElementById("submit")
      .addEventListener("click", this.userRegister);
    this.$bus.$on("sendAddress", (data) => {
      this.userForm.prefixAddress = "";
      for (let i = 0; i < data.length; i++) {
        this.userForm.prefixAddress += data[i] + "/";
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
<style>
</style>