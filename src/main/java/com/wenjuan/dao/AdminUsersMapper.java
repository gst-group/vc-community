package com.wenjuan.dao;

import com.wenjuan.model.AdminUsers;

public interface AdminUsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUsers record);

    AdminUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminUsers record);

    int updateByPrimaryKey(AdminUsers record);
}