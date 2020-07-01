
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
├    ├── cloud-admin -- spring-cloud后台管理
├── cloud-module -- 业务模块
├    ├── cloud-auth -- 认证服务(对外提供登录认证API)
├    ├── cloud-sys -- 系统服务(用户、菜单、角色、权限、字典等基础服务)
├    ├── cloud-demo -- 示例服务
├── cloud-module-api -- 业务模块api(vo、dto、feign)
├    ├── cloud-odr-api -- 订单api
└──  └── cloud-sys-api -- 系统api
```

### cloud-sys、cloud-gateway、cloud-demo、cloud-auth 可以启动，但需要以下组件
> 1. nacos 用于注册中心，配置中心
> 2. mysql 因为 cloud-demo 整合 Mysql测试
> 3. redis 用于缓存
> 4. sentinel-dashboard.jar 控制台(可选，不启动也可以)
> 5. 各服务配置参考 cloud-module/cloud-demo 项目下的 application.yml

