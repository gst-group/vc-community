package com.wenjuan.service.impl;

import com.wenjuan.dao.OrganizationGroupMapper;
import com.wenjuan.model.OrganizationGroup;
import com.wenjuan.service.OrganizationGroupService;
import com.wenjuan.service.OrganizationUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YG on 2017/3/8.
 */
@Service("organizationGroupService")
public class OrganizationGroupServiceImpl implements OrganizationGroupService {
    @Resource
    private OrganizationGroupMapper organizationGroupMapper;
    @Override
    public int insert(OrganizationGroup organization) {
        return organizationGroupMapper.insert(organization);
    }

    @Override
    public int selectOgid(String group) {
        return organizationGroupMapper.selectOgid(group);
    }

    @Override
    public String getGroup(int oid) {
        return organizationGroupMapper.getGroup(oid);
    }

    @Override
    public int selectByOAdmin(int uid) {
        return organizationGroupMapper.selectByOAdmin(uid);
    }
}
