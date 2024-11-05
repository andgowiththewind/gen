<template>
  <div class="login-container">
    <vs-dialog v-model="active">
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
          <vs-checkbox v-model="remember">Remember me</vs-checkbox>
          <a href="#">Forgot Password?</a>
        </div>
      </div>
      <template #footer>
        <div class="footer-dialog">
          <vs-button block>
            Sign In
          </vs-button>
          <div class="new">
            New Here? <a href="#">Create New Account</a>
          </div>
        </div>
      </template>
    </vs-dialog>
    <img :src="LogoObj" alt="">
  </div>
</template>

<script>
import Logo from '@/assets/logo.png';

export default {
  name: "GenLogin",
  components: {},
  data() {
    return {
      LogoObj: Logo,
      active: true,
      email: '',
      password: '',
      remember: false
    }
  },// data
  methods: {},// methods
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



</style>

