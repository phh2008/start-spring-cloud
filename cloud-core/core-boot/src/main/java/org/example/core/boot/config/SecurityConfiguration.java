/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.core.boot.config;


import org.example.core.boot.component.UserAuthHelper;
import org.example.core.boot.interceptor.UserAuthInterceptor;
import org.example.core.boot.props.AuthFilterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 认证拦截器配置
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/28
 */
@Order
@Configuration
@EnableConfigurationProperties({AuthFilterProperties.class})
public class SecurityConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthFilterProperties authFilterProperties;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthHelper userAuthHelper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //用户认证拦截器
        registry.addInterceptor(new UserAuthInterceptor(userAuthConfig, userAuthHelper))
                .excludePathPatterns(authFilterProperties.getExcludePatterns())
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/v2/api-docs-ext")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/404")
                .excludePathPatterns("/500")
                .excludePathPatterns("/error");
    }


}
