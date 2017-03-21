package com.wenjuan.service.impl;

import com.wenjuan.dao.ScoreConfigMapper;
import com.wenjuan.model.ScoreConfig;
import com.wenjuan.service.ScoreConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("scoreConfigService")
public class ScoreConfigServiceImpl implements ScoreConfigService {
    @Resource
    ScoreConfigMapper scoreConfigMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return scoreConfigMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScoreConfig record) {
        return scoreConfigMapper.insert(record);
    }

    @Override
    public ScoreConfig selectByPrimaryKey(Integer id) {
        return scoreConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ScoreConfig> selectAllConfig() {
        return scoreConfigMapper.selectAllConfig();
    }

    @Override
    public int updateByPrimaryKey(ScoreConfig record) {
        return scoreConfigMapper.updateByPrimaryKey(record);
    }

    @Override
    public Map selectCotent() {
        // TODO Auto-generated method stub
        return scoreConfigMapper.selectCotent();
    }

    @Override
    public void updateContent(Map map) {
        // TODO Auto-generated method stub
        scoreConfigMapper.updateContent(map);
    }


}
