### 认证与授权校验
 1. 认证使用Spring拦截器实现  
 2. 授权校验使用Aop拦截实现  

### 需要提供微服务实现
 具体接口类 org.example.core.authc.feign.AuthFeign  
 实现的方法：获取jwt公钥、判断是否有权限、判断是否有角色
 示例：cloud-module/cloud-sys 中有实现示例  

### 相关配置
 1. auth.excludePatterns 类型：数组，作用：要排除的url  
 2. auth.user.token-header 类型：string，作用：获取token的请求头header  
 3. auth.service-name 类型：string，作用：指定需要调用的微服务名称
