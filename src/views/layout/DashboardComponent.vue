<template>
  <div class="dashboard-container">
    <div class="header">
      <!-- 上部 10% 的内容 -->
      上部内容
    </div>
    <div class="content">
      <!-- 中间 80% 的内容 -->
      <component :is="currentPage"></component>
    </div>
    <div class="footer">
      <!-- 下部 10% 的内容 -->
      下部内容
    </div>
  </div>
</template>

<script>
import * as commonConsts from '@/config/CommonConsts';
import MenuHome from '@/views/menu/MenuHome.vue';
import MenuUser from '@/views/menu/MenuUser.vue';


export default {
  name: "DashboardComponent",
  components: {MenuHome, MenuUser},
  data() {
    return {
      currentPage: 'MenuHome',
    }
  },// data
  methods: {},// methods
  watch: {
    // 'searchParamVo.topPath': {handler: function (val, oldVal) {if (val) {this.searchParamVo.topPath = val;this.searchParamVo.topPath = '';}}, deep: true},
  },// watch
  mounted() {
    // 绑定事件，监听页面切换
    this.$bus.$on(commonConsts.BUS_COMPONENT_CHANGE_PAGE, (page) => this.currentPage = page);
  },// mounted
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}


.dashboard-container {
  display: flex;
  flex-direction: column; /* 设置为纵向排列 */
  height: 98vh; /* 占满整个视口高度 */
  overflow: hidden;
}

.header {
  flex: 0 0 50px; /* 固定占用 10% 的高度 */
  background-color: #faf9f8; /* 可选：背景色 */
}

.content {
  flex: 1; /* 填满剩余空间，等同于 flex: 0 1 80% 表现 */
  overflow-y: auto; /* 允许竖向滚动 */
  background-color: #ffffff; /* 可选：背景色 */
}

.footer {
  flex: 0 0 50px; /* 固定占用 10% 的高度 */
  background-color: #133556; /* 可选：背景色 */
}
</style>