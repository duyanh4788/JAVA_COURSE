package com.anhvu.restapi.exception;

public class RestError {
    private int status;
    private String messages;
    private long timeStamp;

    public RestError() {
    };

    public RestError(int status, String messages, long timeStamp) {
        this.status = status;
        this.messages = messages;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessages() {
        return this.messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "{" +
                " status='" + getStatus() + "'" +
                ", messages='" + getMessages() + "'" +
                ", timeStamp='" + getTimeStamp() + "'" +
                "}";
    }
}
