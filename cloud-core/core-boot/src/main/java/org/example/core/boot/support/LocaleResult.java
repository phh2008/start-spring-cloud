package org.example.core.boot.support;


import org.example.core.common.result.Result;
import org.example.core.common.result.ResultCodeEnum;
import org.example.core.tool.utils.MessageSourceUtils;

/**
 * error 国际化
 *
 * @author phh
 * @version V1.0
 * @date 2020/7/10
 */
public class LocaleResult {

    private static final ResultCodeEnum SUCCESS = ResultCodeEnum.SUCCESS;
    private static final ResultCodeEnum BIZ_ERROR = ResultCodeEnum.BIZ_ERROR;
    private static final ResultCodeEnum SYSTEM_ERROR = ResultCodeEnum.SYSTEM_ERROR;

    public static <T> Result<T> ok(T data) {
        return Result.data(data, SUCCESS.getCode(), MessageSourceUtils.getMsg(SUCCESS.getCode(), SUCCESS.getMsg()));
    }

    public static <T> Result<T> ok() {
        return Result.data(null, SUCCESS.getCode(), MessageSourceUtils.getMsg(SUCCESS.getCode(), SUCCESS.getMsg()));
    }

    public static <T> Result bizError() {
        return Result.of(BIZ_ERROR.getCode(), MessageSourceUtils.getMsg(BIZ_ERROR.getCode(), BIZ_ERROR.getMsg()));
    }

    public static <T> Result sysError() {
        return Result.of(SYSTEM_ERROR.getCode(), MessageSourceUtils.getMsg(SYSTEM_ERROR.getCode(), SYSTEM_ERROR.getMsg()));
    }

    /**
     * @param data           数据
     * @param code           状态码
     * @param args           消息参数
     * @param defaultMessage 默认消息
     * @param <T>            数据
     * @return Result<T>
     */
    public static <T> Result<T> of(T data, String code, String defaultMessage, Object[] args) {
        return Result.data(data, code, MessageSourceUtils.getMsg(code, defaultMessage, args));
    }

    public static <T> Result<T> of(String code, String defaultMessage, Object[] args) {
        return Result.data(null, code, MessageSourceUtils.getMsg(code, defaultMessage, args));
    }

    public static <T> Result<T> of(String code, String defaultMessage) {
        return Result.data(null, code, MessageSourceUtils.getMsg(code, defaultMessage));
    }

    public static <T> Result<T> of(String code, Object[] args) {
        return Result.data(null, code, MessageSourceUtils.getMsg(code, args));
    }

    public static <T> Result<T> of(String code) {
        return Result.data(null, code, MessageSourceUtils.getMsg(code));
    }

}
