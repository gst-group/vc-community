package com.wenjuan.dao;

import com.wenjuan.model.Good;

import java.util.List;
import java.util.Map;

public interface GoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Good record);

    Good selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Good record);

    List<Good> getAllGood(Map config);

    List<Good> GoodFy(Map map);
}