import Vue from 'vue'
import Router from 'vue-router'
import demoRouter from './demo'

Vue.use(Router)

export default new Router({
  routes: [
    // 示例
    ...demoRouter
  ]
})
