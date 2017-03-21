package com.wenjuan.service.impl;

import com.wenjuan.dao.GoodMapper;
import com.wenjuan.model.Good;
import com.wenjuan.service.GoodService;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodService")
public class GoodServiceImpl implements GoodService {
    @Resource
    GoodMapper goodMapper;

    public int deleteByPrimaryKey(Integer id) {
        return goodMapper.deleteByPrimaryKey(id);
    }

    public int insert(Good record) {
        return goodMapper.insert(record);
    }

    public Good selectByPrimaryKey(Integer id) {
        return goodMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(Good record) {
        return goodMapper.updateByPrimaryKey(record);
    }

    public List<Good> getAllGood(int page, Integer countPerPage, String loadId, String order) {
        countPerPage = countPerPage == null ? ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE : countPerPage;
        HashMap config = new HashMap();
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("order", order);

        return goodMapper.getAllGood(config);
    }

    public List<Good> GoodFy(Map map) {
        return goodMapper.GoodFy(map);
    }
}
