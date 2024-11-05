<template>
  <div class="login-container">
    <div v-show="dialogVShow">
      <vs-dialog
          blur
          not-close
          prevent-close
          overflow-hidden
          v-model="active">
        <template #header><h4 class="not-margin"><b>gen-copilot</b></h4></template>
        <div class="con-form">
          <vs-input v-model="email" placeholder="Email">
            <template #icon>@</template>
          </vs-input>
          <vs-input type="password" v-model="password" placeholder="Password">
            <template #icon>
              <i class='bx bxs-lock'></i>
            </template>
          </vs-input>
          <div class="flex">
            <vs-checkbox v-model="remember">Remember Me</vs-checkbox>
            <a href="#">Forgot Password?</a>
          </div>
        </div>
        <template #footer>
          <div class="footer-dialog">
            <vs-button block @click="signInOrSignUp">
              sign in OR sign up
            </vs-button>
            <div class="new">
              New Here? <a href="#">Create New Account</a>
            </div>
          </div>
        </template>
      </vs-dialog>
    </div>
    <div @click="handleAvatarClick">
      <img :src="LogoObj" alt="">
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex';
import Logo from '@/assets/logo.png';

export default {
  name: "GenLogin",
  components: {},
  data() {
    return {
      LogoObj: Logo,
      active: true, // 关闭后重新打开会丢失样式,改为 dialogVShow 控制;active保持为true
      email: '',
      password: '',
      remember: false,
      dialogVShow: false,
    }
  },// data
  methods: {
    ...mapActions('auth', ['saveToken']),
    handleAvatarClick() {
      this.dialogVShow = true;
    },
    signInOrSignUp() {
      console.log('signInOrSignUp')
    },
  },// methods
  watch: {
    // 'searchParamVo.topPath': {handler: function (val, oldVal) {if (val) {this.searchParamVo.topPath = val;this.searchParamVo.topPath = '';}}, deep: true},
  },// watch
  mounted() {
    // this.init();
  },// mounted
}
</script>

<style scoped>
[v-cloak] {
  display: none;
}

.login-container {
  overflow: hidden;
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  margin: 0px;
  font-family: 'Rubik Mono One', sans-serif;
  background: linear-gradient(to left, #00857d, #f50771);
  /* Flexbox for centering */
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
}

.login-container >>> .not-margin {
  margin: 0px;
  font-weight: normal;
  padding: 10px;
}

.login-container >>> .con-form {
  width: 100%;
}

.login-container >>> .con-form .flex {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.login-container >>> .con-form .flex a {
  font-size: 0.8rem;
  opacity: 0.7;
}

.login-container >>> .con-form .flex a:hover {
  opacity: 1;
}

.login-container >>> .con-form .vs-checkbox-label {
  font-size: 0.8rem;
}

.login-container >>> .con-form .vs-input-content {
  margin: 10px 0px;
  width: calc(100%);
}

.login-container >>> .con-form .vs-input-content .vs-input {
  width: 100%;
}

.login-container >>> .footer-dialog {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: calc(100%);
}

.login-container >>> .footer-dialog .new {
  margin: 0px;
  margin-top: 20px;
  padding: 0px;
  font-size: 0.7rem;
}

.login-container >>> .footer-dialog .new a {
  color: var(--vs-primary) !important; /* 确保已定义此 CSS 变量 */
  margin-left: 6px;
}

.login-container >>> .footer-dialog .new a:hover {
  text-decoration: underline;
}

.login-container >>> .footer-dialog .vs-button {
  margin: 0px;
}
</style>