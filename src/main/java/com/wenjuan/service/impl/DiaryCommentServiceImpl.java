package com.wenjuan.service.impl;

import com.wenjuan.dao.DiaryCommentMapper;
import com.wenjuan.model.Diary;
import com.wenjuan.model.DiaryComment;
import com.wenjuan.model.User;
import com.wenjuan.service.DiaryCommentService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import com.wenjuan.vo.DiaryCommentView;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("diaryCommentService")
public class DiaryCommentServiceImpl implements DiaryCommentService {
    @Resource
    DiaryCommentMapper diaryCommentMapper;

    public int deleteByPrimaryKey(Integer id) {
        return diaryCommentMapper.deleteByPrimaryKey(id);
    }

    public int insert(DiaryComment record) {
        int effectRow = diaryCommentMapper.insert(record);
        DiaryCommentView diaryCommentView = diaryCommentMapper.selectByPrimaryKey(record.getId());
        if (diaryCommentView.getCommentBase() == null) {
            DiaryComment diaryComment = new DiaryComment();
            diaryComment.setId(diaryCommentView.getId());
            diaryComment.setCommentBase(diaryCommentView.getId());
            diaryCommentMapper.updateByPrimaryKey(diaryComment);
        }
        return effectRow;
    }

    public DiaryCommentView selectByPrimaryKey(Integer id) {
        return diaryCommentMapper.selectByPrimaryKey(id);
    }

    public List<DiaryCommentView> selectByDiaryId(Integer id, boolean topLevel) {
        return diaryCommentMapper.selectByDiaryId(id, topLevel);
    }

    public List<DiaryCommentView> selectNewComment(Integer userId) {
        return diaryCommentMapper.selectNewComment(userId);
    }

    public int updateCommentViewTime(int userId) {
        return diaryCommentMapper.updateCommentViewTime(userId);
    }

    public List<DiaryCommentView> selectCommentAfter(Integer articleId, int lastCommentId) {
        return diaryCommentMapper.selectCommentAfter(articleId, lastCommentId);
    }

    public List<DiaryCommentView> selectCommentUnderComment(Integer commentId) {
        return diaryCommentMapper.selectCommentUnderComment(commentId);
    }

    @Override
    public List<Diary> selectCommentMsg(boolean isReceive, String loadTime, int page, User user, String order,int ogid) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = getParameterMap(loadTime, user);
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("ogid",ogid);
        List<Diary> diaries;
        if (isReceive) {
            diaries = diaryCommentMapper.selectMyReceiveMsg(config);
            for (Diary diary : diaries) {
                if (diary.getExtra() != null) {
                    diary.setComments(diaryCommentMapper.selectCommentBaseComment(Integer.parseInt(diary.getExtra())));
                } else if (diary.getAuthor().equals(user.getId())) {
                    diary.setComments(diaryCommentMapper.selectByDiaryId(diary.getId(), false));
                }
            }
            User newUserInfo = new User();
            newUserInfo.setQueryLastDiaryComment(new Date());
            newUserInfo.setId(user.getId());
            ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
        } else {
            diaries = diaryCommentMapper.selectMySendMsg(config);
            config = getParameterMap(loadTime, user);
            config.put("isReceive", isReceive);
            for (Diary diary : diaries) {
                config.put("commentBase", diary.getExtra());
                diary.setComments(diaryCommentMapper.selectLastNewCommentBaseUser(config));
            }
        }

        return diaries;
    }

    @Override
    public int selectMyReceiveMsgCount(String loadTime, User user) {
        if (user == null) return 0;
        Integer num = diaryCommentMapper.selectMyReceiveMsgCount(getParameterMap(loadTime, user));
        return num == null ? 0 : num;
    }

    private Map<String, Object> getParameterMap(String loadTime, User user) {
        Map<String, Object> config = new HashMap<>();
        config.put("userId", user.getId());
        config.put("comparator", CommonUtil.splitComparator(loadTime));
        config.put("time", CommonUtil.splitInteger(loadTime));
        return config;
    }
}
