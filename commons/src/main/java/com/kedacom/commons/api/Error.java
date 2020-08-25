package com.kedacom.commons.api;

/**
 * @Author:suxiongwei
 * @Description:资源错误
 * @Date:15:01 2017-12-28
 */
public class Error {
    /**
     * 自定义错误码
     */
    private int code;

    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
