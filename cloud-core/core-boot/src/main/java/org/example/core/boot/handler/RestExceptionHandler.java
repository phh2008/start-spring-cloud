package org.example.core.boot.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.core.common.exception.CloudException;
import org.example.core.common.result.Result;
import org.example.core.common.result.ResultCodeEnum;
import org.example.core.tool.utils.MessageSourceUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 全局Controller异常处理
 *
 * @author phh
 * @version V1.0
 * @date 2018/2/2
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {


    /**
     * 404
     *
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Result<Object> err404(HttpServletRequest req, NoHandlerFoundException ex) {
        log.error("NoHandlerFoundException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        ResultCodeEnum errorCode = ResultCodeEnum.NOT_FOUND;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), errorCode.getMsg()));
    }

    /**
     * 405
     *
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result<Object> err405(HttpServletRequest req, HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));

        ResultCodeEnum errorCode = ResultCodeEnum.METHOD_NOT_ALLOWED;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), errorCode.getMsg()));
    }

    /**
     * 521
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Result<Object> err521(HttpServletRequest req, HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException, url：{}, error：{}", req.getRequestURI(), ex.toString());
        String error = Objects.toString(ex.getMessage());
        ResultCodeEnum errorCode = ResultCodeEnum.PARAM_PARSE_ERROR;
        if (error.startsWith("Required request body is missing")) {
            errorCode = ResultCodeEnum.PARAM_EMPTY;
        }
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), errorCode.getMsg()));
    }

    /**
     * 415
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Result<Object> err415(HttpServletRequest req, HttpMediaTypeNotSupportedException ex) {
        log.error("HttpMediaTypeNotSupportedException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        ResultCodeEnum errorCode = ResultCodeEnum.UNSUPPORTED_MEDIA_TYPE;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), errorCode.getMsg()));
    }

    /**
     * 参数不存在
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result<Object> err521(HttpServletRequest req, MissingServletRequestParameterException ex) {
        log.error("MissingServletRequestParameterException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        ResultCodeEnum errorCode = ResultCodeEnum.MISS_PARAM;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), "缺少参数[" + ex.getParameterName() + "]"));
    }

    /**
     * 参数类型错误
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public Result<Object> err522(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        log.error("MethodArgumentTypeMismatchException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        ResultCodeEnum errorCode = ResultCodeEnum.PARAM_TYPE_ERROR;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), "[" + ex.getName() + "]参数错误"));
    }

    /**
     * 参数校验不通过 [@Valid]
     *
     * @param req
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result argumentNotValidHandler(HttpServletRequest req, MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        String errorMsg = errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        ResultCodeEnum errorCode = ResultCodeEnum.PARAM_NOT_VALID;
        return Result.of(errorCode.getCode(), MessageSourceUtils.getMsg(errorCode.getCode(), errorMsg, new String[]{errorMsg}));
    }

    /**
     * @param req
     * @param ex
     * @return
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<Object> errorHandler(HttpServletRequest req, Exception ex) throws Exception {
        String errMsg = String.format("Exception, url:%s, error:%s", req.getRequestURI(),
                Objects.toString(ex.getMessage(), ex.toString()));
        log.error(errMsg, ex);
        String msg = Objects.toString(ex.getMessage(), ex.toString());
        log.error("Exception, url：{}, error：{}", req.getRequestURI(), msg);
        int len = msg.indexOf(",");
        if (len <= 0) {
            len = msg.length();
        }
        //过长的截取部分信息
        String error = msg.substring(0, Math.min(50, len));
        return Result.fail(error);
    }

    /**
     * 自定义异常
     *
     * @param req
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = CloudException.class)
    public Result<Object> customExceptionHandler(HttpServletRequest req, CloudException ex) {
        log.error("CloudException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        return Result.of(ex.getCode(), MessageSourceUtils.getMsg(ex.getCode(), ex.getMessage()));
    }

    /**
     * 未认证(未登录)
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({UnauthenticatedException.class})
    public Result<Object> authorizationExceptionHandler(HttpServletRequest req, UnauthenticatedException ex) {
        log.error("UnauthenticatedException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        return Result.of(ResultCodeEnum.UNAUTHENTICATED);
    }*/

    /**
     * 未授权(无权限)
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({UnauthorizedException.class})
    public Result<Object> authorizationExceptionHandler(HttpServletRequest req, UnauthorizedException ex) {
        log.error("UnauthorizedException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        return Result.of(ResultCodeEnum.UNAUTHORIZED);
    }*/

    /**
     * 未认证|授权异常
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler({AuthorizationException.class})
    public Result<Object> authorizationExceptionHandler(HttpServletRequest req, AuthorizationException ex) {
        log.error("AuthorizationException, url：{}, error：{}", req.getRequestURI(), Objects.toString(ex.getMessage(), ex.toString()));
        return Result.of(ResultCodeEnum.UNAUTHORIZED);
    }*/

}
