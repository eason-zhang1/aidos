## 项目介绍
> 基于`Spring Cloud`的`Hoxton.SR3`版本开发的学习项目

### 权限配置:`aidos-rbac`
> 基于`Spring Security`的权限校验,具体配置参照`AsbstractSecurityConfig`.

### 用户中心: `aidos-user-center`
> 基于`Spring session`结合`adios-rbac`实现用户单点化。

### 网关中心: `aidos-gateway-center`

### 支持中心: `aidos-common-support`
> 常用的工具包, 以及自定义的组件

* exception 包
    >全局异常定义

* jpa 包
    > Jpa相关的超类
    
* web 包
    > 全局web异常捕获的定义
          
* lock 包
    > 基于Redisson分布式锁

### 全局日志: `aidos-log`
`application-logd.yaml`
```yaml
logging:
  file:
    path: D://data//logs
    name: ${logging.file.path}//${spring.application.name}.log
  level:
    club.godnest.aidos: debug
    org.springframework.orm: debug
```
根据自己电脑系统和日志输出要求, 配置如上信息。

在其他module中, 导入`aido-log`时, 应指定如下配置
* spring.profiles.active指定logd
* maven的plugin指定`maven-resources-plugin`


## TODO LIST
* 项目启动
* 服务调用
* 前端
* 链路追踪
* 配置中心

> 如有内容补充, 请邮件联系(邮件在代码中)