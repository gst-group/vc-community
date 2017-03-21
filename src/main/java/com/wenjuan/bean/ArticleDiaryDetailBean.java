package com.wenjuan.bean;

import com.wenjuan.model.CommentBase;
import com.wenjuan.model.User;

import java.util.List;

/**
 * 传输要显示的日记或文章以及其“赞“用户名及部分评论
 */
public class ArticleDiaryDetailBean {

    /**
     * 文章时为Article，日记时为Diary
     */
    private Object object;

    /**
     * 只显示部分用户
     */
    private List<User> followUser;

    /**
     * 赞的用户数量
     */
    private Integer followUserCount;

    /**
     * 只显示部分实例
     * 文章时为ArticleComment，日记时为DiaryComment
     */
    private List<CommentBase> comments;

    /**
     * 赞数量
     */
    private Integer commentCount;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<User> getFollowUser() {
        return followUser;
    }

    public void setFollowUser(List<User> followUser) {
        this.followUser = followUser;
    }

    public List<CommentBase> getComments() {
        return comments;
    }

    public void setComments(List<CommentBase> comments) {
        this.comments = comments;
    }

    public Integer getFollowUserCount() {
        return followUserCount;
    }

    public void setFollowUserCount(Integer followUserCount) {
        this.followUserCount = followUserCount;
    }
}
