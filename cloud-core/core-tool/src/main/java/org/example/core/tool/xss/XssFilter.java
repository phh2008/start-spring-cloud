package org.example.core.tool.xss;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * xss 过滤器
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
@Slf4j
public class XssFilter implements Filter {

    public static final String KEY_EXCLUDES = "excludes";

    public XssFilter(XssProperties xssProperties) {
        this.xssProperties = xssProperties;
    }

    private XssProperties xssProperties;

    @Override
    public void init(FilterConfig config) {
        log.info("xss filter init.");
        String excludes = config.getInitParameter(KEY_EXCLUDES);
        if (excludes != null && excludes.length() > 0) {
            String[] arr = excludes.split(",");
            xssProperties.getExcludePatterns().addAll(Arrays.asList(arr));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
        if (isSkip(path)) {
            chain.doFilter(request, response);
        } else {
            XssHttpRequestWrapper xssRequest = new XssHttpRequestWrapper((HttpServletRequest) request);
            chain.doFilter(xssRequest, response);
        }
    }

    private boolean isSkip(String path) {
        return xssProperties.getExcludePatterns()
                .stream()
                .anyMatch(path::startsWith);
    }

    @Override
    public void destroy() {
    }

}
