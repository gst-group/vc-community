package com.wenjuan.service.impl;

import com.wenjuan.dao.VerifyCodeMapper;
import com.wenjuan.model.VerifyCode;
import com.wenjuan.service.VerifyCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("verifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Resource
    VerifyCodeMapper verifyCodeMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return verifyCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(VerifyCode record) {
        return verifyCodeMapper.insert(record);
    }

    @Override
    public VerifyCode selectByPrimaryKey(Integer id) {
        return verifyCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(VerifyCode record) {
        return verifyCodeMapper.updateByPrimaryKey(record);
    }

    @Override
    public VerifyCode selectValidVerifyCodeByTel(String tel, byte usage) {
        return verifyCodeMapper.selectValidVerifyCodeByTel(tel, usage);
    }
}
