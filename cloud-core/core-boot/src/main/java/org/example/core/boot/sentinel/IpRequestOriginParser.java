package org.example.core.boot.sentinel;


import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * sentinel 以IP来做限制
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/14
 */
public class IpRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        System.out.println(">>>>>>>>>>>>>>>>>>ip:" + httpServletRequest.getRemoteAddr());
        return httpServletRequest.getRemoteAddr();
    }

}
