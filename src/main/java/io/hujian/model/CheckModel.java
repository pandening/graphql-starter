package io.hujian.model;

/**
 * Created by hujian06 on 2017/11/2.
 *
 * the check model
 */
public class CheckModel {

    private int code;
    private String message;

    public CheckModel(int code, String msg) {
        this.code = code;
        this.message = msg;
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
