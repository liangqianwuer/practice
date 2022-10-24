package com.xunc.common.returnbody;

/**
 * 请求结果消息
 * @author 魏子超
 */
public enum Message {
    /**
     *
     */
    SUCCESS("success"), ERROR("error");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getValue() {
        return message;
    }

}
