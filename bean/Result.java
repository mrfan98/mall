/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 3:00 下午
 */
package com.cskaoyan.mall.bean;

/**
 * 专门用来封装结果的
 */
public class Result {

    private Integer code;

    private Object data;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
