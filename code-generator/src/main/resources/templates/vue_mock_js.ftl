// 自行补充测试数据
{
  "/${table.beanName}/queryList":{
    "data":{
      "currentResult":0,
      "firstPage":true,
      "firstResult":0,
      "lastPage":true,
      "list":[{
<#list table.propertyList as col>
        "${col.propertyName}": "0",
</#list>
      }],
      "nextPage":1,
      "pageNo":1,
      "pageSize":100,
      "prePage":1,
      "totalCount":10,
      "totalPage":1
    },
    "msg":"",
    "status":0
  },
  "/${table.beanName}/add":{"data":null,"msg":"","status":0},
  "/${table.beanName}/update":{"data":null,"msg":"","status":0},
  "/${table.beanName}/remove":{"data":null,"msg":"","status":0},
  "/${table.beanName}/get":{
    "data":{
<#list table.propertyList as col>
        "${col.propertyName}": "0",
</#list>
    },
    "msg":"",
    "status":0
  },
}
