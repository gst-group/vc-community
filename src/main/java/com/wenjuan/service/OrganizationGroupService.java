package com.wenjuan.service;

import com.wenjuan.model.OrganizationGroup;

/**
 * Created by YG on 2017/3/8.
 */
public interface OrganizationGroupService {
    int insert(OrganizationGroup organization);
    int selectByOAdmin(int uid);
    //查询ogid
    int selectOgid(String group);
    String getGroup(int oid);

}
