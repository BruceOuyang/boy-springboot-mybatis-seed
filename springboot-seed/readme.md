# 项目开发规范
====

## 项目开发中涉及到相关主要的技术框架
项目管理:Maven
版本管理:Git
中间件:druid
框架:spring boot,mybatis,spring cloud feign


## 项目介绍
    * 项目打成jar包形式提供外部调用，所有的子模块全部以jar的方式被web依赖
    * 项目和其他内部系统交互以http协议来实现
    * 项目对外部系统交互以http协议来实现

## 模块介绍
    ##  bean
        *  bean下面分为三块bo、po、dto。
        *  bo（Business Object）为业务对象，所有Http请求的入参对象都带上BO结尾。
        *  po（Persistence Object）为持久化对象，所有的数据库对象都以PO结尾。
        *  dto（Data Transfer Object）数据传输对象，http请求返回外部都以DTO结尾。调用外部的请求入参以ReqDTO结尾，出参以RespDTO结尾。
    ##  client
        *  所有的对接外部的Http接口都在这个包下面进行封装。
    ##  config
        *  包含所有的Spring boot的配置信息。
    ##  controller
        *  包含所有的对外提供的Http接口。
    ##  dao
        *  mapper包的增强版，包含所有的对于需要使用事务的数据库操作。
    ##  enums
        *  包含所有的枚举信息，常量信息。
    ##  exception
        *  用于定义所有的自定义异常。
    ##  global
        *  包含项目所有的公共组件，如：全局异常处理、拦截器、过滤器等。
    ##  manager
        *  client的增强版，负责对于外部对接的接口进行一次包装，供service层使用，理论上client不能被service直接调用。
    ##  mapper
        *  所有与数据库交互(CRUD)全部放在这一层
        *  所有数据处理类mapper不允许出现执行物理删除的delete方法，如果有delete方法，必须是执行逻辑删除的update语句；
        *  mapper配置中尽量避免多表大数据关联查询,逻辑尽量在JAVA代码中处理
    ##  service
        *  包含所有的业务逻辑处理，供controller层使用。
    ##  utils
        *  包含所有的工具类
     
## 编码及开发中注意事项
	* controller的RequestMapping根据app端定义value
    * 所有你编写过的任何代码一定要有注释,注释要清晰明了,避免后期维护出问题,且无用代码及时清除
    * 代码过程中拒绝硬编码,通过常量或枚举来处理,每个模块的返回信息用自己模块的枚举类
    * 方法体尽量保持简洁,类名方法及变量取名尽量做到见名知意
    * 所有异常捕获均由最外层进行抓取,内部逻辑判断以异常形式进行抛出并设定错误代码
    * Response使用场景:提供外部服务访问的接口统一用Response封装，内部不适用Response
    * 在需要的地方使用new构造对象,非必要时不要提前构造对象
    * 防御式编程思想,尽早发现问题并返回错误编码
    * 日志打印,保证入参出参具有日志输出,日志要有关键字,方便后续问题协查及定位,敏感信息脱敏打印
    * 代码中和环境无关的相关配置尽量避免放到配置文件中
    * 接口调用时必须明确各个接口响应的状态(区分好状态[通信状态和最终业务实际状态],并做正确的业务逻辑处理)
    * 接口变动及升级请做好对应的文档变更并及时同步至Git上,保证对应接口和文档中的一致性

### 消除IDE的警告
    * 没有用到的变量、方法去掉
    * 没有用到注释请直接去掉
    * List,Map 请指定具体的类型,如List<String>,Map<String,Integer>
    * 序列化要求:通过接口传递的对象必须序列化,且务必加上serialVersionUID:
        （1）setting->Inspections->Serialization issues，将serialzable class without "serialVersionUID"打上勾
        （2）将光标放到类名上，按atl＋enter键，就会提示生成serialVersionUID了。
### 类注释模板
    /**
     * @author ${USER} 
     * @date   ${DATE}.
     */
    修改模板的方法  File --> Settings --> Editor  --> File and Code Templates 选中class 再选中includes进行修改