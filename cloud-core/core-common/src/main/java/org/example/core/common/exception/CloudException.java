package org.example.core.common.exception;

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

    private String code;

    /**
     * 3000 default error
     *
     * @param message 消息内容
     */
    public CloudException(String message) {
        super(message);
        this.code = ResultCodeEnum.SYSTEM_ERROR.getCode();
    }

    public CloudException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCodeEnum.SYSTEM_ERROR.getCode();
    }

    /**
     * @param code    状态码
     * @param message 消息内容
     */
    public CloudException(String code, String message) {
        super(message);
        this.code = code;
    }

    public CloudException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * @param status 状态枚举
     */
    public CloudException(ResultCodeEnum status) {
        super(status.getMsg());
        this.code = status.getCode();
    }

    public CloudException(ResultCodeEnum status, Throwable cause) {
        super(status.getMsg(), cause);
        this.code = status.getCode();
    }

    public String getCode() {
        return code;
    }

}
