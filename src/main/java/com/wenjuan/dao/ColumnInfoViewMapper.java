package com.wenjuan.dao;

import com.wenjuan.model.ColumnInfoView;

import java.util.List;

public interface ColumnInfoViewMapper {
    List<ColumnInfoView> selectColumnValueByUserId(int userId);

    ColumnInfoView selectByValueId(int valueId);

}