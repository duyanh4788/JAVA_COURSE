package com.anhvu.crm.exception;

public class RestError {
    private int status;
    private String message;
    private long timeStamp;

    public RestError() {
    }

    public RestError(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long l) {
        this.timeStamp = l;
    }

    @Override
    public String toString() {
        return "{" +
                " status='" + getStatus() + "'" +
                ", message='" + getMessage() + "'" +
                ", timeStamp='" + getTimeStamp() + "'" +
                "}";
    }

}
