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
     * 1000-请求成功
     */
    SUCCESS("1000", "请求成功"),
    /**
     * 2000-请求失败
     */
    ERROR("2000", "请求失败"),

    /**
     * 3000-系统异常
     */
    SYSTEM_ERROR("3000", "系统异常"),

    /**
     * 4000-业务错误
     */
    BIZ_ERROR("4000", "业务错误"),
    NOT_FOUND("4004", "无法找到资源"),
    METHOD_NOT_ALLOWED("4005", "请求方式不支持"),
    UNAUTHORIZED("4006", "无权限"),
    UNAUTHENTICATED("4007", "未登录，请登录认证"),
    AUTH_EXPIRED("4008", "登录过期，请重新登录认证"),
    UNSUPPORTED_MEDIA_TYPE("4015", "不支持的媒体类型"),

    /**
     * 5000-参数错误
     */
    PARAM_ERROR("5000", "参数错误"),
    PARAM_PARSE_ERROR("5001", "参数解析异常"),
    PARAM_EMPTY("5002", "参数为空"),

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
