package com.wenjuan.service;

import com.wenjuan.model.OrganizationGroup;
import com.wenjuan.model.OrganizationUser;

import java.util.Map;

/**
 * Created by YG on 2017/3/8.
 */
public interface OrganizationUserService {
    void insert(OrganizationUser organization);
    int getCountByUidAndOgid(Map map);

}
