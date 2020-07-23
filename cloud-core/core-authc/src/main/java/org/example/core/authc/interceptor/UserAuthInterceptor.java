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
package org.example.core.authc.interceptor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.core.authc.component.UserAuthHelper;
import org.example.core.authc.config.UserAuthConfig;
import org.example.core.common.annotation.WithoutAuthentication;
import org.example.core.common.context.UserContextHandler;
import org.example.core.common.jwt.IJwtInfo;
import org.example.core.common.result.Result;
import org.example.core.common.result.ResultCodeEnum;
import org.example.core.tool.utils.JsonUtils;
import org.example.core.tool.utils.StringUtils;
import org.example.core.tool.utils.WebUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 用户认证校验
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
@Slf4j
@AllArgsConstructor
public class UserAuthInterceptor extends HandlerInterceptorAdapter {

    private UserAuthConfig userAuthConfig;

    private UserAuthHelper userAuthHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        WithoutAuthentication withoutAuthentication = handlerMethod.getBeanType().getAnnotation(WithoutAuthentication.class);
        if (withoutAuthentication == null) {
            withoutAuthentication = handlerMethod.getMethodAnnotation(WithoutAuthentication.class);
        }
        if (withoutAuthentication != null) {
            return true;
        }
        String token = request.getHeader(userAuthConfig.getTokenHeader());
        if (StringUtils.isBlank(token)) {
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(userAuthConfig.getTokenHeader())) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(token)) {
            IJwtInfo info = userAuthHelper.getInfoFromToken(token);
            UserContextHandler.setToken(token);
            UserContextHandler.setJwtInfo(info);
            return true;
        }
        log.warn("客户端认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                request.getRequestURI(),
                WebUtils.getIp(request),
                JsonUtils.writeAsString(request.getParameterMap()));
        Result<?> result = Result.of(ResultCodeEnum.UNAUTHENTICATED);
        WebUtils.writeJson(response, Objects.requireNonNull(JsonUtils.writeAsString(result)));
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHandler.remove();
    }

}
