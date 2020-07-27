
## 工程结构
```
Start-spring-cloud
├── cloud-core -- 框架模块
├    ├── core-authc -- 认证与授权配置
├    ├── core-boot -- springboot通用配置
├    ├── core-elasticsearch -- ES封装
├    ├── core-common -- 各种baseEntity,mapper,service,result,annotation
├    └── core-tool -- 工具类
├── cloud-gateway -- 网关
├── cloud-devops -- 运维中心(各种 admin 控制台)
├    ├── code-generator -- 代码生成器
├── cloud-module -- 业务模块
├    ├── cloud-auth -- 认证服务(对外提供登录认证API)
├    ├── cloud-sys -- 系统服务(比如用户、菜单、角色、权限、字典等基础服务)
├    ├── cloud-demo -- 示例服务
├── cloud-module-api -- 业务模块api(vo、dto、feign等)
└──  └── cloud-sys-api -- 系统api
```

### 项目已整合的功能
1. 网关       spring cloud gateway
2. 服务调用   openFeign
3. 负载均衡   ribbon
4. 熔断降级   sentinel
5. 注册中心   nacos
6. 配置中心   nacos
7. 链路追踪   spring-sleuth+zipkin
8. 缓存       redis，(spring-cache)
9. 认证       jwt
10. ORM        mybatis-plus
11. druid 数据库连接池与监控
12. 全局异常处理
13. 权限校验
14. API文档     swagger2
15. 分布式事务 seata V1.2.0

### 待完善
1. redis key命名规范，集群下redis是没有多数据库，需要命名规范来实现 namespace隔离各服务缓存数据，或者使用不同 redis实例存储
2. 错误码编码规范

### 项目配置
- 各服务配置参考 cloud-module/cloud-demo 项目下的 application.yml
需要注意的是 nacos 配置中心的 dataId，namespace，group 

### swagger文档地址  
> http://localhost:port/doc.html

### druid 数据库连接池监控地址
> http://localhost:port/druid

### 备注：cloud-sys、cloud-gateway、cloud-demo、cloud-auth 可以启动，但需要先启用以下组件
1. nacos 用于注册中心，配置中心
2. mysql 因为 cloud-demo 整合 Mysql测试
3. redis 用于缓存
4. zipkin-server 链路追踪数据收集
5. sentinel-dashboard.jar 控制台(可选，不启动也可以)
6. 启用分布式事务，需要启动 seata 服务端

### 本项目版本号修改
1.修改最外层 pom.xml 中的 <version>1.0-SNAPSHOT</version>  
2.在项目根目录执行 mvn clean -N versions:update-child-modules
执行完以上步骤会把所有子项目中的 parent 节点中的 version 改为统一版本号  

### 打包
+ 开发环境 mvn clean install -Dmaven.test.skip=true -Pdev  
+ 测试环境 mvn clean install -Dmaven.test.skip=true -Ptest  
+ 生产环境 mvn clean install -Dmaven.test.skip=true -Pprd  
+ 不同环境具体查看顶层的 pom.xml 中配置的 profiles




