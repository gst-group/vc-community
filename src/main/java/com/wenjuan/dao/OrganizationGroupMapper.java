package com.wenjuan.dao;

import com.wenjuan.model.OrganizationGroup;

/**
 * Created by YG on 2017/3/8.
 * 组织添加管理者
 */
public interface OrganizationGroupMapper {
    //为组织添加用户
    int insert(OrganizationGroup ogz);
    //查询组织管理员
    int selectByOAdmin(int uid);
    //查询ogid
    int selectOgid(String group);
    String getGroup(int oid);
}
