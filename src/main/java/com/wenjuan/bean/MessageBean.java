package com.wenjuan.bean;

import java.lang.reflect.Field;

public class MessageBean implements Cloneable {
    public static final MessageBean SUCCESS = new MessageBean(0, "success");
    public static final MessageBean SYSTEM_ERROR = new MessageBean(1, "系统错误");
    public static final MessageBean INVALID_REQUEST = new MessageBean(2, "非法请求");
    public static final MessageBean ERROR_USERNAME_PASSWORD = new MessageBean(3, "用户名或密码错误");
    public static final MessageBean ERROR_OPERATION = new MessageBean(4, "操作失败");
    public static final MessageBean INVALID_FIELD = new MessageBean(5, "%s不合要求");
    public static final MessageBean USER_ALREADY_EXIST = new MessageBean(6, "用户已存在");
    public static final MessageBean USER_NOT_EXIST = new MessageBean(7, "用户不存在");
    public static final MessageBean ARTICLE_NOT_EXIST = new MessageBean(8, "文章不存在");
    public static final MessageBean DIARY_NOT_EXIST = new MessageBean(9, "日记不存在");
    public static final MessageBean COMMENT_NOT_EXIST = new MessageBean(9, "评论不存在");
    public static final MessageBean UNLOGIN = new MessageBean(10, "用户未登录");
    public static final MessageBean GOOD_NOT_EXIST = new MessageBean(11, "商品不存在");
    public static final MessageBean INVALID_IMAGE = new MessageBean(12, "图片不合法");
    public static final MessageBean INVALID_AUTHOR = new MessageBean(13, "不是您发布的");
    public static final MessageBean MISMATCH = new MessageBean(14, "%s与%s不匹配");
    public static final MessageBean PERMISSION_DENIED = new MessageBean(15, "权限不足");
    public static final MessageBean FEEDBACK_NOT_EXIST = new MessageBean(16, "反馈不存在");
    public static final MessageBean DATA_ALREADY_EXIST = new MessageBean(17, "数据已存在");
    public static final MessageBean LACK_OF_SCORE = new MessageBean(18, "积分不足");
    public static final MessageBean GROUP_ALREADY_EXISTS = new MessageBean(19, "群组已存在");
    public static final MessageBean GROUP_NOT_EXISTS = new MessageBean(20, "群组不存在");
    public static final MessageBean USER_NOT_IN_TARGET_GROUP = new MessageBean(21, "用户不在指定的讨论组内");
    public static final MessageBean SEND_SMS_ERROR = new MessageBean(22, "短信验证码发送失败");
    public static final MessageBean ID_NOT_EXIST = new MessageBean(23, "指定ID不存在");
    public static final MessageBean ADMIN_CANNOT_LOGIN = new MessageBean(24, "该管理员不能登录APP端，请联系超级管理员");
    public static final MessageBean TARGET_NOT_EXIST = new MessageBean(25, "目标不存在");

    private int code;
    private String message;
    private Object extra;
    /**
     * 附加信息，例如文章列表中的列表
     */
    private Object[] extraList;

    public MessageBean() {
    }

    public MessageBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据状态码获取返回数据 ,0为失败
     *
     * @param effectRow        数据操作影响的行数
     * @param errorMessageType 失败的时候返回的数据类型
     * @return
     */
    public static MessageBean getMessageBean(int effectRow, ErrorMessageType errorMessageType) {
        MessageBean messageBean = null;
        if (effectRow == 0) {
            try {
                Field field = MessageBean.class.getDeclaredField(errorMessageType.toString());
                field.setAccessible(true);
                messageBean = ((MessageBean) (field.get(null))).clone();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            messageBean = MessageBean.SUCCESS.clone();
        }
        return messageBean == null ? SYSTEM_ERROR : messageBean;
    }

    public static MessageBean getInvalidField(String fieldName) {
        MessageBean messageBean = MessageBean.INVALID_FIELD.clone();
        messageBean.setMessage(String.format(messageBean.getMessage(), fieldName));
        return messageBean;
    }

    public static MessageBean getMismatch(String field1, String field2) {
        MessageBean messageBean = MessageBean.MISMATCH.clone();
        messageBean.setMessage(String.format(messageBean.getMessage(), field1, field2));
        return messageBean;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Object[] getExtraList() {
        return extraList;
    }

    public void setExtraList(Object[] extraList) {
        this.extraList = extraList;
    }

    @Override
    public MessageBean clone() {
        try {
            return (MessageBean) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public enum ErrorMessageType {
        ERROR_OPERATION,
        SYSTEM_ERROR,
        INVALID_REQUEST,
        ERROR_USERNAME_PASSWORD,
        INVALID_FIELD,
        USER_ALREADY_EXIST,
        USER_NOT_EXIST,
        ARTICLE_NOT_EXIST,
        DIARY_NOT_EXIST,
        COMMENT_NOT_EXIST,
        GOOD_NOT_EXIST,
        UNLOGIN,
        INVALID_IMAGE,
        PERMISSION_DENIED,
        INVALID_AUTHOR,
        FEEDBACK_NOT_EXIST,
        GROUP_ALREADY_EXISTS,
        LACK_OF_SCORE,
        DATA_ALREADY_EXIST,
        GROUP_NOT_EXISTS,
        USER_NOT_IN_TARGET_GROUP,
        SEND_SMS_ERROR,
        ID_NOT_EXIST,
        ADMIN_CANNOT_LOGIN
    }
}
