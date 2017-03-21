package com.wenjuan.service.impl;

import com.wenjuan.dao.HelpMapper;
import com.wenjuan.model.Help;
import com.wenjuan.service.HelpService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("helpService")
public class HelpServiceImpl implements HelpService {

    @Resource
    HelpMapper helpMapper;

    public int deleteByPrimaryKey(Integer id) {
        return helpMapper.deleteByPrimaryKey(id);
    }

    public int insert(Help record) {
        return helpMapper.insert(record);
    }

    public Help selectByPrimaryKey(Integer id) {
        return helpMapper.selectByPrimaryKey(id);
    }

    public List<Help> selectAllHelps() {
        return helpMapper.selectAllHelps();
    }

    public int toggleVisible(int id) {
        return helpMapper.toggleVisible(id);
    }

    public int updateByPrimaryKey(Help record) {
        return helpMapper.updateByPrimaryKey(record);
    }
}
