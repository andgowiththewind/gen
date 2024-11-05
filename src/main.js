import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Vuesax from 'vuesax';
import moment from "moment";
import 'vuesax/dist/vuesax.css';
import 'boxicons/css/boxicons.min.css';

Vue.use(Vuesax);

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: (h) => h(App),
    beforeCreate() {
        moment.locale('zh-cn');//全局使用moment.js,使用时示例:`this.$moment(new Date()).format('YYYY-MM-DD HH:mm:ss')`
        Vue.prototype.$moment = moment;
        Vue.prototype.$bus = this;// 安装全局事件总线
    }
}).$mount('#app');
