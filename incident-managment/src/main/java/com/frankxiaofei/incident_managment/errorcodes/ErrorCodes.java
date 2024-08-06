package com.frankxiaofei.incident_managment.errorcodes;

/**
 * Error codes for the application
 */
public enum ErrorCodes {
    ENTITY_ALREADY_EXISTS(602, "Entity already exists"),
    ENTITY_NOT_FOUND(601, "Entity not found");

    private int code;
    private String message;

    ErrorCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
