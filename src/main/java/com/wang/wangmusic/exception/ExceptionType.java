package com.wang.wangmusic.exception;

public enum ExceptionType {
    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "未登录"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"),

    USER_NAME_DUPLICATE(40001001, "用户名已存在"),
    USER_NOT_FOUNT(40401001, "用户不存在");

    private final Integer code;
    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
