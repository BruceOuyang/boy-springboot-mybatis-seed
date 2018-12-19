// 示例页面路由
export default [
  {
    desc: '地域示例element-china-area-data',
    path: '/demo/area',
    name: 'areaExample',
    component: resolve => require(['@/pages/example/area'], resolve)
  },
  {
    desc: '回到顶部的Demo',
    path: '/demo/backTop',
    name: 'BackTopExample',
    component: resolve => require(['@/pages/example/backTopDemo'], resolve)
  }
]
