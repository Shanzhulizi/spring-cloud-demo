package com.lm.common;

import lombok.Data;

@Data
public class R {
    private Integer code; // 响应状态码
    private String msg; // 响应消息
    private Object data; // 响应数据

    public R() {
    }

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R ok() {
        return new R(200, "Success", null);
    }

    public static R ok(Object data) {
        return new R(200, "Success", data);
    }

    public static R error(String msg) {
        return new R(500, msg, null);
    }

}
