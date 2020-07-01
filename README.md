
## 工程结构
```
Start-spring-cloud
├── cloud-core -- 框架模块
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
> 1. 网关       spring cloud gateway
> 2. 服务调用   openFeign
> 3. 负载均衡   ribbon
> 4. 熔断降级   sentinel
> 5. 注册中心   nacos
> 6. 配置中心   nacos
> 7. 缓存       redis，(spring-cache)
> 8. 认证       jwt
> 9. ORM        mybatis-plus
> 10. druid 数据库连接池与监控
> 10. 全局异常处理
> 11. 权限校验
> 12. API文档     swagger2

### 待完善
> 1. 链路追踪 zipkin
> 2. 单元测试用例
> 3. 性能测试用例

### 项目配置
> 各服务配置参考 cloud-module/cloud-demo 项目下的 application.yml
> 需要注意的是 nacos 配置中心的 dataId，namespace，group 

### 备注：cloud-sys、cloud-gateway、cloud-demo、cloud-auth 可以启动，但需要先启用以下组件
> 1. nacos 用于注册中心，配置中心
> 2. mysql 因为 cloud-demo 整合 Mysql测试
> 3. redis 用于缓存
> 4. sentinel-dashboard.jar 控制台(可选，不启动也可以)

