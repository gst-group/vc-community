package com.wenjuan.service.impl;

import com.wenjuan.dao.ScoreHistoryMapper;
import com.wenjuan.model.ScoreHistory;
import com.wenjuan.model.User;
import com.wenjuan.service.ScoreHistoryService;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("scoreHistoryService")
public class ScoreHistoryServiceImpl implements ScoreHistoryService {
    @Resource
    ScoreHistoryMapper scoreHistoryMapper;

    public int deleteByPrimaryKey(Integer id) {
        return this.scoreHistoryMapper.deleteByPrimaryKey(id);
    }

    public int insert(ScoreHistory record) {
        return this.scoreHistoryMapper.insert(record);
    }

    public ScoreHistory selectByPrimaryKey(Integer id) {
        return this.scoreHistoryMapper.selectByPrimaryKey(id);
    }

    public List<ScoreHistory> selectScoreHistories() {
        return this.scoreHistoryMapper.selectScoreHistories();
    }

    public List<ScoreHistory> selectMyScoreHistories(String order, String loadTime, int page, User user) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = new HashMap<>();
        config.put("userId", user.getId());
        config.put("comparator", CommonUtil.splitComparator(loadTime));
        config.put("order", order);
        config.put("time", StringUtils.isEmpty(loadTime) ? null : loadTime.substring(1));
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);

        return this.scoreHistoryMapper.selectMyScoreHistories(config);
    }

    @Override
    public String getScoreInfo() {
        // TODO Auto-generated method stub
        return this.scoreHistoryMapper.getScoreInfo();
    }
}
