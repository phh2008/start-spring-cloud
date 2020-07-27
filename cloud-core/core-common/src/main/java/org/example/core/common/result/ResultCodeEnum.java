package org.example.core.common.result;


/**
 * 常用错误码
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public enum ResultCodeEnum implements IResultCode {

    /**
     * 0000-请求成功
     */
    SUCCESS("0000", "请求成功"),
    /**
     * 1000-请求失败
     */
    ERROR("1000", "请求失败"),

    /**
     * 2000-系统异常
     */
    SYSTEM_ERROR("2000", "系统异常"),
    UPLOAD_ERROR("2001", "上传文件错误"),

    /**
     * 3000-用户级别错误
     */
    NOT_FOUND("3001", "无法找到资源"),
    METHOD_NOT_ALLOWED("3002", "请求方式不支持"),
    UNAUTHORIZED("3003", "无权限"),
    UNAUTHENTICATED("3004", "未登录，请登录认证"),
    AUTH_EXPIRED("3005", "登录过期，请重新登录认证"),
    UNSUPPORTED_MEDIA_TYPE("3006", "不支持的媒体类型"),

    PARAM_ERROR("3101", "参数错误"),
    PARAM_PARSE_ERROR("3102", "参数解析异常"),
    PARAM_EMPTY("3103", "参数为空"),
    MISS_PARAM("3104", "缺少参数[?]"),
    PARAM_TYPE_ERROR("3105", "[?]参数类型错误"),
    PARAM_NOT_VALID("3106", "[?]参数校验无效"),

    /**
     * 4000-业务级别错误
     */
    BIZ_ERROR("4000", "业务错误"),
    ;

    private String code;

    private String msg;

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
