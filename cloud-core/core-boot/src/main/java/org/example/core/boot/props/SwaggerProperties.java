package org.example.core.boot.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述
 *
 * @author huihui.peng
 * @version V1.0
 * @date 2020/8/12
 */
@Data
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private Api api = new Api();

    private boolean enable;

    private String basePackages = "org.example";


    @Data
    public static class Api {
        private String title;
        private String version;
    }

}
