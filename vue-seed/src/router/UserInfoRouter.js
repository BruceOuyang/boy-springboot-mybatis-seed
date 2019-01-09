// 用户信息表页面路由
export default [
  {
    desc: '用户信息表',
    path: '/UserInfo/list',
    component: resolve => require(['@/pages/user/UserInfo'], resolve)
  }
]