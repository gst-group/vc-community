package com.wenjuan.model;

import java.util.Date;

public class Group {

    private static Group ungroup = new Group() {{
        setId(-1);
        setName("未分组");
    }};

    private Integer id;

    private String name;

    private String logo;

    private Date createTime;

    private Boolean addBackground;

    private Boolean showForeground;

    public static Group getUngroup() {
        return ungroup;
    }

    public static void setUngroup(Group ungroup) {
        Group.ungroup = ungroup;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOgid() {
        return ogid;
    }

    public void setOgid(int ogid) {
        this.ogid = ogid;
    }

    public Boolean getAddBackground() {
        return addBackground;
    }

    public void setAddBackground(Boolean addBackground) {
        this.addBackground = addBackground;
    }

    public Boolean getShowForeground() {
        return showForeground;
    }

    public void setShowForeground(Boolean showForeground) {
        this.showForeground = showForeground;
    }
    private  int ogid;
}