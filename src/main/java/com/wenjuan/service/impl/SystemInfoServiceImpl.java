package com.wenjuan.service.impl;

import com.wenjuan.dao.SystemInfoMapper;
import com.wenjuan.model.SystemInfo;
import com.wenjuan.service.SystemInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import javax.annotation.Resource;

/**
 * Created by admin on 2016/10/29.
 */
@Service("SystemInfoService")
public class SystemInfoServiceImpl implements SystemInfoService {

    @Override
    public void insertSystemInfo(Map map) {
        systemInfoMapperMapper.insertSystemInfo(map);
    }

    @Override
    public void isnertDiaryInfo(Map map) {
        systemInfoMapperMapper.isnertDiaryInfo(map);
    }

    @Resource
    SystemInfoMapper systemInfoMapperMapper;
    @Override
    public SystemInfo getSystemInfoByid(Map map) {
        return systemInfoMapperMapper.getSystemInfoByid(map);
    }

    @Override
    public void update(SystemInfo S) {
        systemInfoMapperMapper.update(S);
    }
    @Override
    public int getDiaryVisible(int ogid) {
        return systemInfoMapperMapper.getDiaryVisible(ogid);
    }

    @Override
    public void UpdateDiaryVisible(Map map) {
        systemInfoMapperMapper.UpdateDiaryVisible(map);
    }
}
