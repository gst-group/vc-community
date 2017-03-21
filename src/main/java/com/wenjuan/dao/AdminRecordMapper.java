package com.wenjuan.dao;

import com.wenjuan.model.AdminRecord;

import java.util.List;


public interface AdminRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRecord record);

    AdminRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdminRecord record);

    List<AdminRecord> selectAll(String id);

    int count(String id);
}