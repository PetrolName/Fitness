package com.cheng.baselib.retroft;

/*
 * 文件名:    ApiException
 * 创建时间:  2017/5/19 on 20:45
 * 描述:     TODO 自定义exception 用于访问是是
 */
public class ApiException extends RuntimeException {

    private int code;


    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String message) {
        super(new Throwable(message));

    }
}