package com.common.module.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 通用返回结果封装类，用于统一 REST API 返回格式。
 * 支持链式构建、泛型数据、常用状态码常量及日志记录。
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(Result.class);

    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAIL = 500;
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_UNAUTHORIZED = 401;
    public static final int CODE_FORBIDDEN = 403;

    private boolean success;
    private int code;
    private String msg;
    private T data;

    private Result() {
        // 默认构造器，用于 Jackson 反序列化
    }

    /**
     * 构建成功响应
     * @param data 返回的数据
     * @return Result<T>
     */
    public static <T> Result<T> success(Object data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功");
        result.setData((T) data);
        return result;
    }

    /**
     * 构建成功响应（无数据）
     * @return Result<Void>
     */
    public static Result<Void> success() {
        return success(null);
    }

    /**
     * 构建失败响应
     * @param code 错误码
     * @param msg  错误信息
     * @return Result<Void>
     */
    public static Result<Void> fail(int code, String msg) {
        logger.warn("API failed with code: {}, message: {}", code, msg);
        Result<Void> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 构建系统内部错误响应
     * @return Result<Void>
     */
    public static Result<Void> internalError() {
        return fail(CODE_FAIL, "系统内部错误");
    }

    /**
     * 构建参数校验失败响应
     * @return Result<Void>
     */
    public static Result<Void> badRequest() {
        return fail(CODE_BAD_REQUEST, "请求参数错误");
    }

    /**
     * 链式设置数据
     * @param data 数据
     * @return this
     */
    public Result<T> data(T data) {
        this.data = data;
        return this;
    }

    /**
     * 链式设置消息
     * @param msg 消息
     * @return this
     */
    public Result<T> msg(String msg) {
        this.msg = msg;
        return this;
    }

    /**
     * 链式设置状态码
     * @param code 状态码
     * @return this
     */
    public Result<T> code(int code) {
        this.code = code;
        return this;
    }
}
