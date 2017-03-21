package com.wenjuan.dao;

import com.wenjuan.model.WeiXin;

/**
 * Created by YG on 2016/8/29.
 */
public interface WeiXinMapper {
    void insert(WeiXin weixin);

    int se(WeiXin weiXin);

    WeiXin selectAll(String openid);

    void UpdateUserid(WeiXin weiXin);
    
    void update(WeiXin weixin);
}
