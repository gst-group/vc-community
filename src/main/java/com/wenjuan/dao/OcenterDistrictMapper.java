package com.wenjuan.dao;

import com.wenjuan.model.OcenterDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OcenterDistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OcenterDistrict record);

    OcenterDistrict selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(OcenterDistrict record);

    List<OcenterDistrict> selectByLevel(@Param("level") int level, @Param("upCode") int upCode);
}