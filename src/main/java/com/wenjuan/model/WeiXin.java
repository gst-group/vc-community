package com.wenjuan.model;

import java.util.Date;

/**
 * Created by YG on 2016/8/29.
 * 
 * modify by DiGua on 2016-08-31 添加字段unionId/refreshToken/accessToken/lastRefreshTime
 */
public class WeiXin {
    private int id;
    private String openId;
    private String nickname;
    private String city;
    private String province;
    private String country;
    private String privilege;
    private String userid;
    private String sex;
    private String headImgURL;
    private String unionId;
    private String refreshToken;
    private String accessToken;
    private Date lastRefreshTime;
    

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {

        return sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenId() {
        return openId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public String getPrivilege() {
        return privilege;
    }

    public String getUserid() {
        return userid;
    }

	public String getHeadImgURL() {
		return headImgURL;
	}

	public void setHeadImgURL(String headImgURL) {
		this.headImgURL = headImgURL;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getLastRefreshTime() {
		return lastRefreshTime;
	}

	public void setLastRefreshTime(Date lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
    
    
}
