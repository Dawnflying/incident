package com.frankxiaofei.incident_managment.exceptions;

import lombok.Data;

@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    public BizException(String message) {
        super(message);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
