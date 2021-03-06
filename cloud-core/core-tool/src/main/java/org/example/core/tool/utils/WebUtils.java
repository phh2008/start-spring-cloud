package org.example.core.tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.example.core.tool.utils.StringConst.COMMA;
import static org.example.core.tool.utils.StringConst.UNKNOWN;

/**
 * web 工具类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/27
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

    private static final List<String> IP_HEADERS = Arrays.asList("X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR");

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (requestAttributes == null) ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    public static void write(HttpServletResponse response, String contentType, String content) {
        response.setHeader("Content-type", contentType);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().write(content);
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    public static void writeJson(HttpServletResponse response, String content) {
        write(response, MediaType.APPLICATION_JSON_VALUE, content);
    }

    public static void writeHtml(HttpServletResponse response, String content) {
        write(response, MediaType.TEXT_HTML_VALUE, content);
    }


    /**
     * 设置cookie
     *
     * @param response        HttpServletResponse
     * @param name            cookie name
     * @param value           cookie value
     * @param maxAgeInSeconds maxAge
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAgeInSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAgeInSeconds);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 清除 某个指定的cookie
     *
     * @param response HttpServletResponse
     * @param key      cookie key
     */
    public static void removeCookie(HttpServletResponse response, String key) {
        setCookie(response, key, null, 0);
    }

    /**
     * 读取cookie
     *
     * @param request HttpServletRequest
     * @param name    cookie name
     * @return cookie value
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 读取cookie
     *
     * @param name cookie name
     * @return cookie value
     */
    public static String getCookieValue(String name) {
        HttpServletRequest request = WebUtils.getRequest();
        Assert.notNull(request, "request from RequestContextHolder is null");
        return getCookieValue(request, name);
    }


    public static String getIp(final HttpServletRequest request) {
        Optional<String> ipOptional = IP_HEADERS.stream()
                .map(request::getHeader)
                .filter(e -> !StringUtils.isEmpty(e) && !UNKNOWN.equalsIgnoreCase(e))
                .findFirst();
        return ipOptional.map(e -> e.indexOf(COMMA) > 0 ? e.split(COMMA)[0] : e)
                .orElse(request.getRemoteAddr());
    }

}
