### zipkin server 推荐直接下载官方提供的 jar文件
### zipkin github 主页
github https://github.com/openzipkin/zipkin

### 当前项目使用的版本：
V2.21.4

### 使用方法
https://github.com/openzipkin/zipkin/tree/master/zipkin-server

### zipkin-server mysql sql脚本
https://github.com/openzipkin/zipkin/blob/master/zipkin-storage/mysql-v1/src/main/resources/mysql.sql

### zipkin-server 启用mysql 脚本示例：
java -jar zipkin-server-2.21.4-exec.jar --zipkin.storage.type=mysql --zipkin.storage.mysql.username=root --zipkin.storage.mysql.password=root --zipkin.storage.mysql.host=localhost --zipkin.storage.mysql.port=3306

### zipkin-server 启用elasticsearch 脚本示例
java -jar zipkin-server-2.21.4-exec.jar --zipkin.storage.type=elasticsearch --zipkin.storage.elasticsearch.hosts=http://localhost:9200