
#### 代码生成器
 + 入口： src/test/ org.example.generator.CodeGenerator.generator
 + 配置：可在此类中修改数据类型，包名，基类，author 等信息
 + 生成的代码目录在本项目中 src/main/
 + 注意：如果是 mysql5 与 mysql8 要更改 pom.xml 驱动版本
 + 如果使用 idea 不能使用Scanner控制台输入时，修改 idea64.exe.vmoptions 
    在文件未尾加上-Deditable.java.test.console=true 重启 idea 即可
