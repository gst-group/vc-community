package com.wenjuan.model;

/**
 * Created by YG on 2017/3/8.
 */
public class OrganizationUser {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private int ogid;
    private int uid;

    public int getId() {
        return id;
    }

    public int getOgid() {
        return ogid;
    }

    public int getUid() {
        return uid;
    }
}
