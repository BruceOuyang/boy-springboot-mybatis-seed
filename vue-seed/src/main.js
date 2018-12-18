import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VueAxios from 'vue-axios'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/base.css'

import store from './assets/js/store/store.all.js'
import {computeDiff, timeUtil, urlUtil} from './assets/js/utils/util.js'

import App from './App'
import router from './router'
import formatter from './assets/js/utils/format.js'
import settings from './assets/js/settings.js'

settings.initDomain()

Vue.config.productionTip = false
Vue.config.devtools = true

// 设置 vue 插件
Vue.use(VueAxios, axios)
Vue.use(Vuex)
Vue.use(ElementUI)

// 设置内置属性
Vue.prototype.store = store
Vue.prototype.timeUtil = timeUtil
Vue.prototype.urlUtil = urlUtil
Vue.prototype.computeDiff = computeDiff
Vue.prototype.formatter = formatter

// 定义了全局的eventBus
const EventBus = new Vue()
Object.defineProperties(Vue.prototype, {
  $bus: {
    get: function () {
      return EventBus
    }
  }
})

// 全局数据过滤器
Object.keys(formatter).forEach(function (key, index, arr) {
  Vue.filter(key, formatter[key])
})

// 添加响应拦截器
axios.interceptors.response.use(
  response => {
    let data = response.data
    if (data.status === 0) {
      return Promise.resolve(data.data)
    } else {
      new Vue().$message.error({message: data.msg || '操作失败', duration: 0, showClose: true})
      return Promise.reject(data)
    }
  },
  error => {
    if (error.response && error.response.status === 403) {
      settings.toLogin()
    } else if (error.response && error.response.status === 404) {
      new Vue().$confirm('无权访问或您已登录超时，是否重新登录？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        settings.toLogin()
      }).catch(() => {})
    } else {
      console.error(error)
      new Vue().$message.error({
        message: '网络异常，请联系管理员，或退出重新登录',
        duration: 0,
        showClose: true
      })
    }
    return Promise.reject(error)
  })

// 启动一个 vue 示例
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>',
  beforeCreate: function () {
    this.store.dispatch('dictionary/fetchData').then(() => {})
    // TODO 这里可以加载完成所有的数据，然后渲染页面，需要在Promise.all中定义所有的数据/枚举调用接口
    Promise.all([]).then(() => {
    })
  }
})
