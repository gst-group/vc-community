package com.wenjuan.model;

/**
 * Created by admin on 2016/10/29.
 */
public class SystemInfo {
    private int id;

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    private String name;

    public int getOgid() {
        return ogid;
    }

    private String value;
    private int ogid;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
