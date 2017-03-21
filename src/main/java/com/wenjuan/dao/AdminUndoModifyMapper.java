package com.wenjuan.dao;

import com.wenjuan.model.AdminUndoModify;

public interface AdminUndoModifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUndoModify record);

    AdminUndoModify selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AdminUndoModify record);
}