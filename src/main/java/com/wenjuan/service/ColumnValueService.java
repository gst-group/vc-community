package com.wenjuan.service;

import com.wenjuan.model.ColumnValue;

public interface ColumnValueService {

    int deleteByPrimaryKey(Integer valueid);

    int insert(ColumnValue record);

    ColumnValue selectByPrimaryKey(Integer valueid);

    int updateByPrimaryKey(ColumnValue record);
}
