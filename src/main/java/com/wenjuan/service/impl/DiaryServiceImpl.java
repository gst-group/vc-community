package com.wenjuan.service.impl;

import com.wenjuan.dao.DiaryCommentMapper;
import com.wenjuan.dao.DiaryMapper;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.model.Diary;
import com.wenjuan.model.User;
import com.wenjuan.service.DiaryService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import com.wenjuan.vo.DiaryView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {
    @Resource
    DiaryMapper diaryMapper;

    @Resource
    DiaryCommentMapper diaryCommentMapper;

    @Resource
    UserMapper userMapper;

    public int deleteByPrimaryKey(Integer id) {
        return diaryMapper.deleteByPrimaryKey(id);
    }

    public int insert(Diary record) {
        return diaryMapper.insert(record);
    }

    public DiaryView selectByPrimaryKey(Integer id) {
        return diaryMapper.selectByPrimaryKey(id);
    }

    /**
     * @param type         0：自己发布的，1：全部可见的
     * @param user         不验证用户是否存在
     * @param order        time,follow_count,reply_count，author等与desc,asc的组合
     * @param page         页数，0时不分页
     * @param countPerPage 每页项数，null时为默认值
     * @param loadId       字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的文章
     * @return
     */
    public List<?> selectDiaries(int type, User user, String order, int page, Integer countPerPage, String loadId,int ogid) {
        if (user == null) {
            return null;
        }
        countPerPage = countPerPage == null ? ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE : countPerPage;
        HashMap<String, Object> config = new HashMap<>();
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("userId", user.getId());
        config.put("ogid",ogid);
        List<DiaryView> diaries = null;
        switch (type) {
            case 0:
                diaries = diaryMapper.selectMyDiaries(config);
                break;
            case 1:
                diaries = diaryMapper.selectDiaries(config);
        }
        if (diaries != null) {
            for (Diary diary : diaries) {
                diary.setComments(diaryCommentMapper.selectByDiaryId(diary.getId(), false));
                diary.setLikeUsers(userMapper.selectFollowUserByDiaryId(diary.getId()));
            }
            if (page == 1 && diaries.size() > 0) {
                int lastId = diaries.get(0).getId();
                if (user.getQueryLastDiaryId() < lastId) {
                    User newUserInfo = new User();
                    newUserInfo.setId(user.getId());
                    newUserInfo.setQueryLastDiaryId(lastId);
                    ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
                }
            }
        }
        return diaries;
    }

    @Override
    public int selectDiariesCount(int type, User user, String loadId,int ogid) {
        HashMap<String, Object> config = new HashMap<>();
        config.put("ogid",ogid);
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("userId", user == null ? null : user.getId());
        switch (type) {
            case 0:
                return diaryMapper.selectMyDiariesCount(config);
            case 1:
                return diaryMapper.selectDiariesCount(config);
            default:
                return 0;
        }
    }

    public List<?> selectMyFavor(Map map) {
        return diaryMapper.selectMyFavor(map);
    }

    public int diaryCount(int ogid) {

        return diaryMapper.diaryCount(ogid);
    }

    @Override
    public int toggleVisible(Integer id) {
        return diaryMapper.toggleVisible(id);
    }

    public int updateByPrimaryKey(Diary record) {
        return diaryMapper.updateByPrimaryKey(record);
    }

    public List<Diary> diarySearch(String content, int page,int ogid) {
        int itemPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map config = new HashMap();
        config.put("ogid",ogid);
        config.put("key", CommonUtil.processLikeParameter(content));
        config.put("startIndex", page == 0 ? null : (page - 1) * itemPerPage);
        config.put("pageLimited", itemPerPage);
        return diaryMapper.diarySearch(config);
    }

    @Override
    public void top(Map map) {
        // TODO Auto-generated method stub
        diaryMapper.top(map);
    }
}
