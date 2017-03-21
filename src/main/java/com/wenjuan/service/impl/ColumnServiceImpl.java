package com.wenjuan.service.impl;

import com.wenjuan.dao.ColumnMapper;
import com.wenjuan.model.Column;
import com.wenjuan.service.ColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("columnService")
public class ColumnServiceImpl implements ColumnService {
    @Resource
    private ColumnMapper columnMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return columnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Column record) {
        return columnMapper.insert(record);
    }

    @Override
    public Column selectByPrimaryKey(Integer id) {
        return columnMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Column record) {
        return columnMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Column> selectAllColumn() {
        return columnMapper.selectAllColumn();
    }
}
