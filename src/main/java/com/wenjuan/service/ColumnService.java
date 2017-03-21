package com.wenjuan.service;

import com.wenjuan.model.Column;

import java.util.List;

public interface ColumnService {

    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Column record);

    List<Column> selectAllColumn();
}
