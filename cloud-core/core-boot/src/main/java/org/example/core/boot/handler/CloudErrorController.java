package org.example.core.boot.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.core.common.result.Result;
import org.example.core.tool.utils.JsonUtils;
import org.example.core.tool.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * 描述
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/1
 */
@Slf4j
@RestController
public class CloudErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        log.info("/error");
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, false);
        //attributes:  {"timestamp":"2020-07-01 17:05:44","status":404,"error":"Not Found","message":"No message available","path":"/xx"}
        String contentType = Objects.toString(request.getContentType(), "");
        if (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || WebUtils.isAjax(request)) {
            String sb = Objects.toString(attributes.get("error"), "") +
                    " " +
                    Objects.toString(attributes.get("message"), "系统异常");
            return Result.fail(sb);
        }
        log.error(JsonUtils.writeAsString(attributes));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/error");
        modelAndView.addAllObjects(attributes);
        modelAndView.setStatus(HttpStatus.OK);
        return modelAndView;
    }


}
