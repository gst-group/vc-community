package com.wenjuan.service.impl;

import com.wenjuan.dao.ColumnInfoViewMapper;
import com.wenjuan.model.ColumnInfoView;
import com.wenjuan.service.ColumnInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("columnInfoService")
public class ColumnInfoServiceImpl implements ColumnInfoService {
    @Resource
    private ColumnInfoViewMapper columnInfoViewMapper;

    @Override
    public List<ColumnInfoView> selectColumnValueByUserId(int userId) {
        return columnInfoViewMapper.selectColumnValueByUserId(userId);
    }

    @Override
    public ColumnInfoView selectByValueId(int valueId) {
        return columnInfoViewMapper.selectByValueId(valueId);
    }
}
