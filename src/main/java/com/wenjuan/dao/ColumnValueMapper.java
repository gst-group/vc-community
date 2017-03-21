package com.wenjuan.dao;

import java.util.List;

import com.wenjuan.model.ColumnValue;

public interface ColumnValueMapper {
    int deleteByPrimaryKey(Integer valueid);

    int insert(ColumnValue record);

    ColumnValue selectByPrimaryKey(Integer valueid);

    int updateByPrimaryKey(ColumnValue record);
    
    List<ColumnValue> selectByUid();
}