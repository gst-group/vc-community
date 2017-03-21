package com.wenjuan.service.impl;

import com.wenjuan.dao.NotifiesMapper;
import com.wenjuan.model.Notifies;
import com.wenjuan.model.NotifiesUser;
import com.wenjuan.model.NotifiesUserView;
import com.wenjuan.model.User;
import com.wenjuan.service.NotifiesService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.CommonUtil;
import com.wenjuan.utils.ConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("notifiesService")
public class NotifiesServiceImpl implements NotifiesService {
    @Resource
    NotifiesMapper notifiesMapper;

    public int deleteByPrimaryKey(Integer id) {
        return notifiesMapper.deleteByPrimaryKey(id);
    }

    public int insert(Notifies record) {
        return notifiesMapper.insert(record);
    }

    public Notifies selectByPrimaryKey(Integer id) {
        return notifiesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Notifies> selectNotifys(Map map) {
        // TODO Auto-generated method stub
        return notifiesMapper.selectNotifys(map);
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        return notifiesMapper.count();
    }


    @Override
    public int selectBytitle(String content) {
        // TODO Auto-generated method stub
        return notifiesMapper.selectBytitle(content);
    }

    @Override
    public void inNU(Map map) {
        // TODO Auto-generated method stub
        notifiesMapper.inNU(map);
    }

    public NotifiesUserView[] selectNotifyViews(String loadId, String order, int page, User user) {
        int countPerPage = ConfigUtil.NUMBER_OF_ARTICLE_ITEM_PER_PAGE;
        Map<String, Object> config = new HashMap<>();
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("id", CommonUtil.splitInteger(loadId));
        config.put("order", order);
        config.put("startIndex", page == 0 ? null : (page - 1) * countPerPage);
        config.put("pageLimited", countPerPage);
        config.put("userId", user.getId());
        NotifiesUserView[] notifiesUserViews = notifiesMapper.selectNotifyViews(config);
        if (notifiesUserViews != null && notifiesUserViews.length > 0) {
            int lastId = notifiesUserViews[0].getNotifyId();
            if (user.getQueryLastPushId() < lastId) {
                User newUserInfo = new User();
                newUserInfo.setId(user.getId());
                newUserInfo.setQueryLastPushId(lastId);
                ApplicationContextUtil.getUserService().updateByPrimaryKey(newUserInfo);
            }
        }
        return notifiesUserViews;
    }

    public int insertNotifyUserByList(List<NotifiesUser> notifiesUsers) {
        return notifiesMapper.insertNotifyUserByList(notifiesUsers);
    }

    @Override
    public List<Notifies> selectNotifysSeach(Map map) {
        // TODO Auto-generated method stub
        return notifiesMapper.selectNotifysSeach(map);
    }

    @Override
    public int countSeach(String key) {
        // TODO Auto-generated method stub
        return notifiesMapper.countSeach(key);
    }

    @Override
    public int selectPushCount(String loadId, User user) {
        if (user == null) {
            return 0;
        }
        Map<String, Object> config = new HashMap<>();
        config.put("userName", user.getName());
        config.put("comparator", CommonUtil.splitComparator(loadId));
        config.put("id", CommonUtil.splitInteger(loadId));
        return notifiesMapper.selectPushCount(config);
    }
}
