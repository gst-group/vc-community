package com.wenjuan.service.impl;

import com.wenjuan.dao.GoodDuihuanMapper;
import com.wenjuan.model.GoodDuihuan;
import com.wenjuan.model.GoodDuihuanView;
import com.wenjuan.model.User;
import com.wenjuan.service.GoodDuihuanService;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodDuihuanService")
public class GoodDuihuanServiceImpl implements GoodDuihuanService {

    @Resource
    GoodDuihuanMapper goodDuihuanMapper;

    public int deleteByPrimaryKey(GoodDuihuan key) {
        return goodDuihuanMapper.deleteByPrimaryKey(key);
    }

    public int insert(GoodDuihuan record) {
        return goodDuihuanMapper.insert(record);
    }

    public GoodDuihuan selectByPrimaryKey(GoodDuihuan key) {
        return goodDuihuanMapper.selectByPrimaryKey(key);
    }

    public int updateByPrimaryKey(GoodDuihuan record) {
        return goodDuihuanMapper.updateByPrimaryKey(record);
    }


    public List<GoodDuihuanView> getAllRecord(String order, String loadTime, int page, User user) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = new HashMap<>();
        config.put("userId", user.getId());
        config.put("comparator", CommonUtil.splitComparator(loadTime));
        config.put("order", order);
        config.put("time", CommonUtil.splitString(loadTime));
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);

        return goodDuihuanMapper.getAllRecord(config);
    }


    public List<GoodDuihuanView> getAll(Map map) {
        // TODO Auto-generated method stub
        return goodDuihuanMapper.getAll(map);
    }

    @Override
    public int getAllCount() {
        // TODO Auto-generated method stub
        return goodDuihuanMapper.getAllcount();
    }

    @Override
    public List<GoodDuihuanView> getUserAll(Map map) {
        // TODO Auto-generated method stub
        return goodDuihuanMapper.getUserAll(map);
    }

    @Override
    public int getUsercount(int uid) {
        // TODO Auto-generated method stub
        return goodDuihuanMapper.getUsercount(uid);
    }

}
