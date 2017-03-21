package com.wenjuan.service;

import com.wenjuan.model.ColumnInfoView;

import java.util.List;

public interface ColumnInfoService {
    List<ColumnInfoView> selectColumnValueByUserId(int userId);

    ColumnInfoView selectByValueId(int valueId);
}
