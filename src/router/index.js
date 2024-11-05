import Vue from 'vue';
import Router from 'vue-router';

// 页面组件引入
import GenLogin from '@/views/layout/GenLogin.vue';
import GenDashboard from '@/views/layout/GenDashboard.vue';

// 使用 Vue Router
Vue.use(Router);

// 定义路由
const routes = [
    {
        path: '/',
        name: 'GenLogin',
        component: GenLogin,
    },
    {
        path: '/dashboard',
        name: 'GenDashboard',
        component: GenDashboard,
    },
];

// 实例化路由
const router = new Router({
    mode: 'history',
    routes,
});

export default router;
