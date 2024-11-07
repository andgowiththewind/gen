import Vue from 'vue'
import VueI18n from 'vue-i18n'
// 引入不同语言的翻译文件
import en from './en.json'
import zh from './zh.json'

// 引入 VueI18n 插件
Vue.use(VueI18n)

const messages = {
    en,
    zh
}

const i18n = new VueI18n({
    locale: 'zh', // 默认语言
    fallbackLocale: 'en', // 备用语言
    messages // 设置地区信息
})

export default i18n