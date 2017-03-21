package com.wenjuan.model;

/**
 * Created by YG on 2017/3/8.
 */
public class OrganizationGroup {
    public int getId() {
        return id;
    }



    public int getOAdmin() {
        return OAdmin;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    private int id;
    private int OAdmin;
    private String group;
    public void setId(int id) {
        this.id = id;
    }

    public void setOAdmin(int OAdmin) {
        this.OAdmin = OAdmin;
    }
}
