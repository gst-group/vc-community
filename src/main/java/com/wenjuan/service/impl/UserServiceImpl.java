package com.wenjuan.service.impl;

import com.wenjuan.controller.WeiXinController;
import com.wenjuan.dao.UserMapper;
import com.wenjuan.dao.UserSessionMapper;
import com.wenjuan.model.User;
import com.wenjuan.model.UserView;
import com.wenjuan.service.OrganizationGroupService;
import com.wenjuan.service.UserService;
import com.wenjuan.utils.ApplicationContextUtil;
import com.wenjuan.utils.HxUtil;
import com.wenjuan.vo.UserBriefInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Resource
    private UserSessionMapper userSessionMapper;

    private Random random = new Random(System.currentTimeMillis());

    public int deleteByPrimaryKey(Integer id) {
//        User user = userMapper.selectByPrimaryKey(id);
//        new HxUtil().deleteUser(user);
        return this.userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User record) {
        record.setRole(1);//普通用户
        HxUtil hxUtil = new HxUtil();
        try {
            hxUtil.insertUser(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
        User proUser = ApplicationContextUtil.getBean("userService", UserService.class).selectByName(record.getName());
        if(proUser!=null){
            return this.userMapper.updateByPrimaryKey(record);
        }else{
            return this.userMapper.insert(record);

        }


    }

    public User selectByPrimaryKey(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public User selectByUsernamePassword(String username, String password) {
        return this.userMapper.selectByUsernamePassword(username, password);
    }

    @Override
    public User selectByName(String tel) {
        return userMapper.selectByName(tel);
    }

    @Override
    public User[] selectByNames(String[] names) {
        if (names == null || names.length == 0) {
            return new User[0];
        }
        return userMapper.selectByNames(names);
    }

    @Override
    public User selectByToken(String token) {
        User user = userMapper.selectByToken(token);
        if (user != null) {
            userSessionMapper.update(user.getId());
        }else{
            logger.info("----------------null----------------------");
        }
        return user;
    }

    public int updateByPrimaryKey(User record) {
        assert record.getId() != null;
        if (record.getPassword() != null) {
            User user = userMapper.selectByPrimaryKey(record.getId());
            if (!user.getPassword().equals(record.getPassword())) {
                new HxUtil().updateUserInfo(user);
            }
        }
        return this.userMapper.updateByPrimaryKey(record);
    }

    /**
     * 切换用户订阅商品状态
     *
     * @param userid
     * @return
     */
    public int toggleNotifyNewGood(int userid) {
        return this.userMapper.toggleNotifyNewGood(userid);
    }

    public List<User> getAllUserInfo() {
        return this.userMapper.getAllUserInfo();
    }

    @Override
    public List<User> getDiaryFavors(Integer id) {
        return userMapper.getDiaryFavors(id);
    }

    @Override
    public List<User> getArticleFavors(Integer id) {
        return userMapper.getArticleFavors(id);
    }

    @Override
    public List<User> getFavorMe(Integer id) {
        return userMapper.getFavorMe(id);
    }

    @Override
    public List<User> getUserListUnderGrp(Map<String, Object> config) {
        return userMapper.getUserListUnderGrp(config);
    }

    @Override
    public List<UserBriefInfo> selectFollowUserByDiaryId(Integer id) {
        return userMapper.selectFollowUserByDiaryId(id);
    }

    @Override
    public List<UserBriefInfo> selectFollowUserByArticleId(Integer id) {
        return userMapper.selectFollowUserByArticleId(id);
    }

    @Override
    public List<UserBriefInfo> selectUserInfoByList(List<String> ids) {
        return userMapper.selectUserInfoByList(ids);
    }

    @Override
    public int getNewMsgCount(Integer id, int type) {
        return userMapper.getNewMsgCount(id, type);
    }

    @Override
    public int insertAdminUser(User user) {
        user.setRole(0);
        return userMapper.insert(user);
    }

    @Override
    public List<User> adminUserList(Map map) {
        return userMapper.adminUserList(map);
    }

    @Override
    public int adminCount() {
        return userMapper.adminCount();
    }

    @Override
    public List<User> filter(Map<String, Object> config) {
        config.put("ogid",ApplicationContextUtil.getBean("organizationGroupService", OrganizationGroupService.class).selectByOAdmin(User.getCurrentUser().getId()));
        return userMapper.filter(config);
    }

    @Override
    public List<User> unGroupedUser(Map config) {
        return userMapper.unGroupedUser(config);
    }

    @Override
    public List<User> selectSubscribeGoodUser() {
        return userMapper.selectSubscribeGoodUser();
    }

    @Override
    public List<User> selectUserByGroups(List<String> grps) {
        if (grps == null || grps.size() == 0) {
            return new ArrayList<>();
        }
        return userMapper.selectUserByGroups(grps);
    }

    @Override
    public List<UserView> selectUserViewByGroups(List<String> grps) {
        return userMapper.selectUserViewByGroups(grps);
    }

    public List<UserBriefInfo> selectUserBriefInfoByNameList(List<String> names) {
        if (names == null || names.size() == 0) {
            return new ArrayList<>();
        }
        return userMapper.selectUserBriefInfoByNameList(names);
    }

    @Override
    public List<String> getExistDegree() {
        return userMapper.getExistDegree();
    }

    @Override
    public List<String> selectUserNameUnderGroup(String grpName) {
        return userMapper.selectUserNameUnderGroup(grpName);
    }

    @Override
    public int getRootType(int id) {
        return userMapper.getRootType(id);
    }
}
