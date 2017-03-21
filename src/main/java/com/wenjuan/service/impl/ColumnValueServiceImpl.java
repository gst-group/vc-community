package com.wenjuan.service.impl;

import com.wenjuan.dao.ColumnValueMapper;
import com.wenjuan.model.ColumnValue;
import com.wenjuan.service.ColumnValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("columnValueService")
public class ColumnValueServiceImpl implements ColumnValueService {

    @Resource
    private ColumnValueMapper columnValueMapper;

    @Override
    public int deleteByPrimaryKey(Integer valueid) {
        return columnValueMapper.deleteByPrimaryKey(valueid);
    }

    @Override
    public int insert(ColumnValue record) {
        return columnValueMapper.insert(record);
    }

    @Override
    public ColumnValue selectByPrimaryKey(Integer valueid) {
        return columnValueMapper.selectByPrimaryKey(valueid);
    }

    @Override
    public int updateByPrimaryKey(ColumnValue record) {
        return columnValueMapper.updateByPrimaryKey(record);
    }
}
