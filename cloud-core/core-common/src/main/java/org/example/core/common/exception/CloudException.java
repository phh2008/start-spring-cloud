package org.example.core.common.exception;

import org.example.core.common.result.IResultCode;
import org.example.core.common.result.ResultCodeEnum;

/**
 * 通用异常类
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
public class CloudException extends RuntimeException {

    private static final long serialVersionUID = -1863023688308957273L;
    /**
     * 状态码
     */
    private String code;
    /**
     * 占位符参数
     * （资源国际化时可用来占位符参数）
     */
    private Object[] params;

    /**
     * 3000 default error
     *
     * @param message 消息内容
     */
    public CloudException(String message, Object... params) {
        super(message);
        this.code = ResultCodeEnum.SYSTEM_ERROR.getCode();
        this.params = params;
    }

    public CloudException(String message, Throwable cause, Object... params) {
        super(message, cause);
        this.code = ResultCodeEnum.SYSTEM_ERROR.getCode();
        this.params = params;
    }

    /**
     * @param code    状态码
     * @param message 消息内容
     */
    public CloudException(String code, String message, Object... params) {
        super(message);
        this.code = code;
        this.params = params;
    }

    public CloudException(String code, String message, Throwable cause, Object... params) {
        super(message, cause);
        this.code = code;
        this.params = params;
    }

    /**
     * @param status 状态枚举
     */
    public CloudException(IResultCode status, Object... params) {
        super(status.getMsg());
        this.code = status.getCode();
        this.params = params;
    }

    public CloudException(IResultCode status, Throwable cause, Object... params) {
        super(status.getMsg(), cause);
        this.code = status.getCode();
        this.params = params;
    }

    public String getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }

}
