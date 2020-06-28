package org.example.core.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 返值包装对象
 *
 * @author phh
 * @version V1.0
 * @date 2020/6/24
 */
@ApiModel(description = "返回信息")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private String code;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;
    @ApiModelProperty(value = "承载数据")
    private T data;
    @ApiModelProperty(value = "返回消息", required = true)
    private String msg;

    private Result() {
    }

    private Result(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCodeEnum.SUCCESS.getCode().equals(code);
    }

    /**
     * 1000 快速返回 SUCCESS
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok() {
        return new Result<T>(ResultCodeEnum.SUCCESS);
    }

    /**
     * SUCCESS
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> ok(T data) {
        return ok(data, ResultCodeEnum.SUCCESS.getMsg());
    }

    /**
     * SUCCESS
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> ok(T data, String msg) {
        return data(data, ResultCodeEnum.SUCCESS.getCode(), msg);
    }

    /**
     * SUCCESS
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> data(T data, String code, String msg) {
        return new Result<T>(code, data, msg);
    }

    /**
     * data
     *
     * @param data       数据
     * @param resultCode 状态码
     * @param <T>        T 泛型
     * @return Result
     */
    public static <T> Result<T> data(T data, IResultCode resultCode) {
        return new Result<T>(resultCode, data);
    }

    /**
     * of result code
     *
     * @param resultCode 业务代码
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> of(IResultCode resultCode) {
        return new Result<T>(resultCode);
    }

    /**
     * of result code
     *
     * @param resultCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return Result
     */
    public static <T> Result<T> of(IResultCode resultCode, String msg) {
        return new Result<T>(resultCode, msg);
    }

    /**
     * 失败
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return Result
     */
    public static <T> Result<T> of(String code, String msg) {
        return new Result<T>(code, null, msg);
    }


    /**
     * 根flag返回是否成功
     *
     * @param flag 成功状态
     * @return Result
     */
    public static <T> Result<T> ofStatus(boolean flag) {
        return flag ? ok() : fail();
    }

    /**
     * 4000 快速业务失败 BIZ_ERROR
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        return new Result<>(ResultCodeEnum.BIZ_ERROR);
    }

    /**
     * 4000 业务失败
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return Result
     */
    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCodeEnum.BIZ_ERROR, msg);
    }

    /**
     * 2000 请求失败
     *
     * @param <T>
     * @return
     */
    public static <T> Result error() {
        return new Result<>(ResultCodeEnum.ERROR);
    }

    /**
     * 3000 系统错误
     *
     * @param <T>
     * @return
     */
    public static <T> Result sysError() {
        return new Result<>(ResultCodeEnum.SYSTEM_ERROR);
    }

    public String getCode() {
        return code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public boolean isSuccess() {
        this.success = ResultCodeEnum.SUCCESS.getCode().equals(code);
        return this.success;
    }

    public Result<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
