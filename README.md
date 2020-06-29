
## 工程结构
```
Start-spring-cloud
├── cloud-core -- 公用模块
├    ├── core-boot -- springboot通用配置
├    ├── core-elasticsearch -- ES封装
├    ├── core-common -- 各种baseEntity,mapper,service,result,annotation
├    └── core-tool -- 工具类
├── cloud-gateway -- 网关
├── cloud-devops -- 运维中心(各种 admin 控制台)
├    ├── code-generator -- 代码生成器
├    ├── cloud-admin -- spring-cloud后台管理
├── cloud-module -- 业务模块
├    ├── cloud-auth -- 认证服务
├    ├── cloud-sys -- 系统服务
├    ├── cloud-odr -- 订单服务
├── cloud-module-api -- 业务模块api(vo、dto、feign)
├    ├── cloud-odr-api -- 订单api
└──  └── cloud-sys-api -- 系统api  
```