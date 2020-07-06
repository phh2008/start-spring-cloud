package org.example.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.example.core.common.mp.base.BaseColumnEntity;
import org.example.core.common.mp.base.BaseEntity;
import org.example.core.common.mp.base.BaseService;
import org.example.core.common.mp.base.BaseServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
public class CodeGenerator {

    /**
     * 生成代码入口
     * <p>
     * 注：如果使用 idea 不能使用Scanner控制台输入时，修改 idea64.exe.vmoptions
     * 在文件未尾加上 -Deditable.java.test.console=true 重启 idea 即可
     */
    @Test
    public void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        //创建人
        gc.setAuthor(scanner("创建人@author"));
        gc.setOpen(false);
        //gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        String jdbcName = scanner("DB类型(mysql5、mysql8、sqlServer、postGreSql)");
        String hostPort = scanner("host:port ");
        String dbName = scanner("DB名称");
        DataSourceConfig dsc = new DataSourceConfig();
        String jdbcUrl = "";
        String driverName = "";
        switch (jdbcName.toLowerCase()) {
            case "mysql5":
                driverName = "com.mysql.jdbc.Driver";
                jdbcUrl = String.format("jdbc:mysql://%s/%s?useUnicode=true&useSSL=false&characterEncoding=utf8", hostPort, dbName);
                break;
            case "mysql8":
                driverName = "com.mysql.cj.jdbc.Driver";
                jdbcUrl = String.format("jdbc:mysql://%s/%s?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&allowPublicKeyRetrieval=true", hostPort, dbName);
                break;
            case "sqlserver":
                driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                jdbcUrl = String.format("jdbc:sqlserver://%s;DatabaseName=%s", hostPort, dbName);
                break;
            case "postgresql":
                driverName = "org.postgresql.Driver";
                jdbcUrl = String.format("jdbc:postgresql://%s/%s", hostPort, dbName);
                break;
            default:
                System.err.println("请输入正确的数据库类型！！！");
                throw new MybatisPlusException("请输入正确的数据库类型！！！");
        }
        dsc.setDriverName(driverName);
        dsc.setUrl(jdbcUrl);
        //TODO 数据库账号密码
        dsc.setUsername(scanner("DB账号"));
        dsc.setPassword(scanner("DB密码"));
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        //TODO 包名
        pc.setParent("org.example");
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        //TODO 公共父类
        strategy.setSuperEntityClass(BaseColumnEntity.class);
        strategy.setSuperServiceClass(BaseService.class);
        strategy.setSuperServiceImplClass(BaseServiceImpl.class);
        strategy.setSuperControllerClass("org.example.core.boot.handler.BaseController");
        //TODO 写于父类中的公共字段
        strategy.setSuperEntityColumns("createBy", "createdAt", "updateBy", "updatedAt", "delFlag");

        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //数据表前辍：模块名_
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


}
