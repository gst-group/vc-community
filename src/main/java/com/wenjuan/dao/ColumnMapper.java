package com.wenjuan.dao;

import com.wenjuan.model.Column;

import java.util.List;

public interface ColumnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Column record);

    List<Column> selectAllColumn();
}