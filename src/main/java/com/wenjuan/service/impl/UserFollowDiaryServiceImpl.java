package com.wenjuan.service.impl;

import com.wenjuan.dao.UserFollowDiaryMapper;
import com.wenjuan.model.User;
import com.wenjuan.model.UserFollowDiary;
import com.wenjuan.service.UserFollowDiaryService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userFollowDiaryService")
public class UserFollowDiaryServiceImpl implements UserFollowDiaryService {
    @Resource
    UserFollowDiaryMapper userFollowDiaryMapper;

    public static Map<String, Object> getParameterMap(String loadTime, int userId) {
        HashMap<String, Object> config = new HashMap<>();
        config.put("time", (loadTime == null || loadTime.isEmpty()) ? null : Integer.parseInt(loadTime.substring(1)));
        config.put("comparator", (loadTime == null || loadTime.isEmpty()) ? null : loadTime.startsWith("A") ? ">" : "<");
        config.put("userId", userId);
        return config;
    }

    @Override
    public int deleteByPrimaryKey(Integer userid, Integer diaryid) {
        return userFollowDiaryMapper.deleteByPrimaryKey(userid, diaryid);
    }

    @Override
    public int insert(UserFollowDiary record) {
        return userFollowDiaryMapper.insert(record);
    }

    @Override
    public UserFollowDiary selectByPrimaryKey(Integer userid, Integer diaryid) {
        return userFollowDiaryMapper.selectByPrimaryKey(userid, diaryid);
    }

    @Override
    public List<UserFollowDiary> diaryPariseFY(Map map) {
        // TODO Auto-generated method stub
        return userFollowDiaryMapper.diaryPariseFY(map);
    }

    @Override
    public int diaryPariseCount(int id) {
        // TODO Auto-generated method stub
        return userFollowDiaryMapper.diaryPariseCount(id);
    }

    @Override
    public List<UserFollowDiary> selectFollowInfo(boolean followed, int userId, int page, String order, String loadTime,int ogid) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = getParameterMap(loadTime, userId);
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("followed", followed);
        config.put("ogid",ogid);
        List<UserFollowDiary> result = userFollowDiaryMapper.selectFollowInfo(config);
        if (followed) {
            User newUserInfo = new User();
            newUserInfo.setQueryLastDiaryLike(new Date());
            newUserInfo.setId(userId);
            ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
        }
        return result;
    }

    public int selectFollowCount(String loadTime, int userId) {
        return userFollowDiaryMapper.selectFollowCount(getParameterMap(loadTime, userId));
    }

    public List<UserFollowDiary>  selectAll(int id){
        return userFollowDiaryMapper.selectAll(id);
    }
}
