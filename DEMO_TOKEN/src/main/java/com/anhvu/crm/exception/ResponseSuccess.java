package com.anhvu.crm.exception;

import org.springframework.http.ResponseEntity;

public class ResponseSuccess {
    private String message;
    private Object data;

    public ResponseSuccess(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static ResponseEntity<ResponseSuccess> build(String message, Object data) {
        ResponseSuccess response = new ResponseSuccess(message, data);
        return ResponseEntity.ok(response);
    }
}
