<template>
  <div class="container">
    <div class="sidebar">
      <!-- 左边内容 -->
      <DashboardSidebar></DashboardSidebar>
    </div>
    <div class="main">
      <!-- 右边内容 -->
      <DashboardComponent></DashboardComponent>
    </div>
  </div>
</template>

<script>
import DashboardSidebar from '@/views/layout/DashboardSidebar.vue';
import DashboardComponent from '@/views/layout/DashboardComponent.vue';

export default {
  name: "GenDashboard",
  components: {DashboardSidebar, DashboardComponent},
  data() {
    return {}
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
.container {
  display: flex; /* 使用 Flexbox 布局 */
  width: 100%; /* 确保容器占满可用空间 */
}

.sidebar {
  width: 45px; /* 设置左边 div 的固定宽度 */
  background-color: #f0f0f0; /* 可选：设置背景色以便视觉区分 */
}

.main {
  flex: 1; /* 使右边 div 占据剩余空间;等价于 flex: 1 1 auto */
  background-color: #ffffff; /* 可选：设置背景色以便视觉区分 */
}
</style>
