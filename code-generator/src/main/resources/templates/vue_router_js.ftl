// ${table.tableComment}页面路由
export default [
  {
    desc: '${table.tableComment}',
    path: '/${table.beanName}/list',
    component: resolve => require(['@/pages/${cfg.tableInclude}/${table.beanName}'], resolve)
  }
]