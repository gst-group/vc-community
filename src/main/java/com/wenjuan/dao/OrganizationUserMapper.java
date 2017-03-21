package com.wenjuan.dao;

import com.wenjuan.model.OrganizationGroup;
import com.wenjuan.model.OrganizationUser;

import java.util.Map;

/**
 * Created by YG on 2017/3/8.
 * 组织添加用户
 */
public interface OrganizationUserMapper {
    //为组织添加用户
    void insert(OrganizationUser ogz);
    //查询用户是否已存在组织
    int getCountByUidAndOgid(Map map);
}
