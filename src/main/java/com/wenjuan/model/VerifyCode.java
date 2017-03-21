package com.wenjuan.model;

import java.util.Date;

public class VerifyCode {
    private Integer id;

    private String tel;

    private String code;

    private Byte usage;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Byte getUsage() {
        return usage;
    }

    public void setUsage(Byte usage) {
        this.usage = usage;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}