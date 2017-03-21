package com.wenjuan.service.impl;

import com.wenjuan.dao.OrganizationUserMapper;
import com.wenjuan.model.OrganizationGroup;
import com.wenjuan.model.OrganizationUser;
import com.wenjuan.service.OrganizationUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by YG on 2017/3/8.
 */
@Service("organizationUserService")
public class OrganizationUserServiceImpl implements OrganizationUserService {
    @Resource
    private OrganizationUserMapper organizationUserMapper;

    @Override
    public int getCountByUidAndOgid(Map map) {
        return organizationUserMapper.getCountByUidAndOgid(map);
    }

    @Override
    public void insert(OrganizationUser organizationUser) {
        organizationUserMapper.insert(organizationUser);
    }


}
