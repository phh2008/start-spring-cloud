### Seata 分布式事务解决方案
> github主页：https://github.com/seata/seata  
> 文档主页：http://seata.io/zh-cn/docs/user/quickstart.html  

### 本项目中集成 seata V1.2.0 (git seata 分支)
```
如果无需集成 seata ,取消以下配置和 pom 中的 seata 坐标
seata:
  enabled: false # 为false不启用seata
  tx-service-group: my_test_tx_group
  enable-auto-data-source-proxy: true
  config:
    type: nacos
    nacos:
      server-addr: 127.0.0.1:8848
      group: SEATA_GROUP
      namespace: seata
  registry:
    type: nacos
    nacos:
      application: seata-server
      server-addr: 127.0.0.1:8848
      namespace: seata
      cluster: default

<!-- seata -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
    <exclusions>
        <exclusion>
            <groupId>io.seata</groupId>
            <artifactId>seata-spring-boot-starter</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>io.seata</groupId>
    <artifactId>seata-spring-boot-starter</artifactId>
    <version>${seata.version}</version>
</dependency>

```