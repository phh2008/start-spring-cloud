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

### 服务端认证实现
```
//jwt 配置
@Data
@Configuration
public class JwtConfig {
    @Value("${jwt.rsa-secret}")
    private String userSecret;
    @Value("${jwt.expire-second:2592000}")
    private Integer expireSecond;

    private byte[] userPubKey;
    private byte[] userPriKey;
}

//生成密钥对
Map<String, byte[]> keyMap = RsaKeyHelper.generateKey(jwtConfig.getUserSecret());
jwtConfig.setUserPriKey(keyMap.get("pri"));
jwtConfig.setUserPubKey(keyMap.get("pub"));

//获取公钥接口，供其它微服务调用
@ApiOperation(value = "获取公钥", httpMethod = "POST", response = Result.class)
@RequestMapping(value = "/userPubKey", method = RequestMethod.POST)
public Result<byte[]> getUserPublicKey() {
	return Result.ok(jwtConfig.getUserPubKey());
}

//登录验证，使用私钥生成 token
JwtInfo jwtInfo = new JwtInfo();
jwtInfo.setId(String.valueOf(userInfoVO.getUserId()));
jwtInfo.setRealName(userInfoVO.getRealName());
jwtInfo.setUserName(userInfoVO.getUsername());
String token = JwtHelper.createToken(jwtInfo, jwtConfig.getUserPriKey(), jwtConfig.getExpireSecond());
```
