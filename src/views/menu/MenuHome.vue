<template>
  <div>
    <h1>MenuHome</h1>
    <p>{{ $t('i18n_s63ytg') }}</p>
    <button @click="changeLanguage('zh')">中文</button>
    <button @click="changeLanguage('en')">English</button>
  </div>
</template>

<script>

export default {
  name: "MenuHome",
  components: {},
  data() {
    return {}
  },// data
  methods: {
    changeLanguage(language) {
      this.$i18n.locale = language
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