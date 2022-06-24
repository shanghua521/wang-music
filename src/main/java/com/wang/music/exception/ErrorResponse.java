package com.wang.music.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String message;

    private Object trace;

    public ErrorResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
