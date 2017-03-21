package com.wenjuan.service;

import com.wenjuan.model.VerifyCode;

public interface VerifyCodeService {

    int deleteByPrimaryKey(Integer id);

    int insert(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(VerifyCode record);

    /**
     * @param tel
     * @param usage
     * @return
     */
    VerifyCode selectValidVerifyCodeByTel(String tel, byte usage);
}
