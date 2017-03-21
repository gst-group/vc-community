package com.wenjuan.dao;

import com.wenjuan.model.VerifyCode;
import org.apache.ibatis.annotations.Param;

public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(VerifyCode record);

    VerifyCode selectValidVerifyCodeByTel(@Param("tel") String tel, @Param("usage") byte usage);
}