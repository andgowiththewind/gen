<template>
  <div>
    <vs-sidebar
        absolute
        hover-expand
        v-model="active"
        open>
      <template #logo>
        <vs-avatar><img :src="LogoObj" alt=""></vs-avatar>
      </template>

      <vs-sidebar-item id="home" @click.native="changePage('home','MenuHome')">
        <template #icon>
          <i class='bx bx-home'></i>
        </template>
        首页
      </vs-sidebar-item>

      <vs-sidebar-item id="user" @click.native="changePage('user','MenuUser')">
        <template #icon>
          <i class='bx bx-user'></i>
        </template>
        用户
      </vs-sidebar-item>

      <template #footer>
        <vs-row justify="space-between">
          <vs-avatar><img :src="LogoObj" alt=""></vs-avatar>
          <vs-avatar badge-color="danger" badge-position="top-right">
            <i class='bx bx-bell'></i>
            <template #badge>
              28
            </template>
          </vs-avatar>
        </vs-row>
      </template>
    </vs-sidebar>
  </div>
</template>

<script>
import Logo from '@/assets/logo.png';
import * as commonConsts from '@/config/CommonConsts';

export default {
  name: "DashboardSidebar",
  components: {},
  data() {
    return {
      LogoObj: Logo,
      active: 'home',
    }
  },// data
  methods: {
    changePage(id, page) {
      this.active = id;
      this.$bus.$emit(commonConsts.BUS_COMPONENT_CHANGE_PAGE, page);// 触发emit
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
</style>