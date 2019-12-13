package com.muguangli.core;

/**
 * 
 * @Description:
 * @Author:wy
 * @Since:2016年9月28日
 * @Version:1.1.0
 */
public class RespJson {

    private String code;

    private String msg;

    private Object data;

    public RespJson(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespJson(ErrorEnum em) {
        this.code = em.getErrorCode();
        this.msg = em.getMsg();
    }

    public RespJson() {
        this(ErrorEnum.SUCCESS);
    }

    public RespJson(Object data) {
        this.data = data;
        this.code = ErrorEnum.SUCCESS.getErrorCode();
        this.msg = ErrorEnum.SUCCESS.getMsg();
    }

    /**
     * @param data
     *            用于增加,修改等方法,直接放回int类型的包装
     */
    public RespJson(int data) {
        if (data == 1) {
            this.data = data;
            this.code = ErrorEnum.SUCCESS.getErrorCode();
            this.msg = ErrorEnum.SUCCESS.getMsg();
        } else {
            this.data = data;
            this.code = ErrorEnum.ERROR.getErrorCode();
            this.msg = ErrorEnum.ERROR.getMsg();
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
