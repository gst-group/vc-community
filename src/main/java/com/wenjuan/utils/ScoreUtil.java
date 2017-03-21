package com.wenjuan.utils;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.*;
import com.wenjuan.service.GoodDuihuanService;
import com.wenjuan.service.GoodService;
import com.wenjuan.service.ScoreConfigService;
import com.wenjuan.service.ScoreHistoryService;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作用户积分的类
 */
public class ScoreUtil {

    public static final int SYSTEM = 14;

    /**
     * 发布日记
     */
    public static final Integer PUBLISH_DIARY = 1;

    /**
     * 完成问卷后台手动添加积分
     */
    public static final Integer QUESTIONNAIRE = 2;

    /**
     * 生日福利
     */
    public static final Integer BIRTHDAY = 3;

    /**
     * 提交帮助与反馈
     */
    public static final Integer SUBMIT_FEEDBACK = 4;

    /**
     * 每日登录少于30分钟（不包括30分钟）
     */
    public static final Integer LOG_IN_BELOW_30 = 5;

    /**
     * 30-60分钟（不包括60分钟）
     */
    public static final Integer LOG_IN_BELOW_60 = 6;

    /**
     * 60-80分钟（不包括80分钟）
     */
    public static final Integer LOG_IN_BELOW_80 = 7;

    /**
     * 80分钟以上（包括80分钟）
     */
    public static final Integer LOG_IN_UPON_80 = 8;

    /**
     * 六小时之内回复话题
     */
    public static final Integer COMMENT_BELOW_6 = 9;

    /**
     * 超过6小时但在24小时内（包括24小时）回复话题
     */
    public static final int COMMENT_BELOW_24 = 10;
    /**
     * 超过24小时但在48小时内（包括48小时）回复话题
     */
    public static final int COMMENT_BELOW_48 = 11;

    /**
     * 超过48小时回复话题
     */

    public static final int COMMENT_UPON_48 = 12;

    private static final int EXCHANGE_GOOD = 13;

    /**
     * 记录兑换积分分值
     */
    private static final Map<Integer, ScoreConfig> scoreMap = new HashMap<>();

    static {
        ScoreConfigService scoreConfigService = (ScoreConfigService) ApplicationContextUtil.getApplicationContext().getBean("scoreConfigService");
        List<ScoreConfig> scoreConfigList = scoreConfigService.selectAllConfig();
        for (ScoreConfig scoreConfig : scoreConfigList) {
            scoreMap.put(scoreConfig.getId(), scoreConfig);
        }
    }

    /**
     * 添加积分
     *
     * @param userId     用户id
     * @param scoreType  ScoreUtil类中常量，SYSTEM和QUESTIONNAIRE需指定extraValue
     * @param extraValue 系统设置，填写问卷等额外值，为增量值
     * @return
     */
    public static MessageBean addScore(int userId, int scoreType, Integer extraValue) {
        ScoreHistoryService scoreHistoryService = ApplicationContextUtil.getBean("scoreHistoryService", ScoreHistoryService.class);

        ScoreConfig scoreConfig = scoreMap.get(scoreType);
        assert scoreConfig != null;
        Integer value = scoreConfig.getValue() == -1 ? extraValue : scoreConfig.getValue();
        assert value != null;

        ScoreHistory scoreHistory = new ScoreHistory();
        scoreHistory.setEventId(scoreType);
        scoreHistory.setUserId(userId);
        scoreHistory.setScore(value);
        scoreHistoryService.insert(scoreHistory);

        User user = User.getCurrentUser();
        user.setScore(user.getScore() + value);

        return MessageBean.SUCCESS;
    }

    /**
     * 添加积分,
     *
     * @param userId    用户id
     * @param scoreType ScoreUtil类中常量,SYSTEM和QUESTIONNAIRE需指定extraValue
     * @return
     */
    public static MessageBean addScore(int userId, int scoreType) {
        ScoreConfig scoreConfig = scoreMap.get(scoreType);
        assert scoreConfig != null && scoreConfig.getValue() != -1;
        return addScore(userId, scoreType, 0);
    }


    @Deprecated
    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, newValue);
    }

    @Deprecated
    public static void updateScoreUtil(int type, int score) throws Exception {
        //登录时长
        if (type == 11) {
            //<30
            setFinalStatic(ScoreUtil.class.getField(" LOG_IN_BELOW_30"), score);
        } else if (type == 12) {
            //30-60
            setFinalStatic(ScoreUtil.class.getField(" LOG_IN_BELOW_60"), score);
        } else if (type == 13) {
            //60-80
            setFinalStatic(ScoreUtil.class.getField(" LOG_IN_BELOW_80"), score);
        } else if (type == 14) {
            //>80
            setFinalStatic(ScoreUtil.class.getField(" LOG_IN_UPON_60"), score);

        }
        //发言回复
        if (type == 31) {
            setFinalStatic(ScoreUtil.class.getField("COMMENT_BELOW_6"), score);
        } else if (type == 32) {
            setFinalStatic(ScoreUtil.class.getField("COMMENT_BELOW_24"), score);
        } else if (type == 33) {
            setFinalStatic(ScoreUtil.class.getField("COMMENT_BELOW_48"), score);
        } else if (type == 34) {
            setFinalStatic(ScoreUtil.class.getField("COMMENT_UPON_48"), score);
        }
        if (type == 2) {
            //生日
            setFinalStatic(ScoreUtil.class.getField("BIRTHDAY"), score);
        } else if (type == 4) {
            //提交
            setFinalStatic(ScoreUtil.class.getField("COMMENT_BELOW_6"), score);
        }
    }

    public static ScoreConfig getScoreConfig(int type) {
        int constantValue = 0;
        if (type == 11) {
            //<30
            constantValue = LOG_IN_BELOW_30;
        } else if (type == 12) {
            //30-60
            constantValue = LOG_IN_BELOW_60;
        } else if (type == 13) {
            //60-80
            constantValue = LOG_IN_BELOW_80;
        } else if (type == 14) {
            //>80
            constantValue = LOG_IN_UPON_80;


        }
        //发言回复
        if (type == 31) {
            constantValue = COMMENT_BELOW_6;
        } else if (type == 32) {
            constantValue = COMMENT_BELOW_24;
        } else if (type == 33) {
            constantValue = COMMENT_BELOW_48;
        } else if (type == 34) {
            constantValue = COMMENT_UPON_48;
        }
        if (type == 2) {
            //生日
            constantValue = BIRTHDAY;
        } else if (type == 4) {
            //提交
            constantValue = SUBMIT_FEEDBACK;
        }
        return scoreMap.get(constantValue);
    }

    /**
     * 更新系统默认积分值
     */
    public static void updateScore(int type, int score) {
        ScoreConfig scoreConfig = getScoreConfig(type);
        scoreConfig.setValue(score);
        ScoreConfigService scoreConfigService = ApplicationContextUtil.getBean("scoreConfigService", ScoreConfigService.class);
        scoreConfigService.updateByPrimaryKey(scoreConfig);
    }

    public static MessageBean exchange(int goodId, User user) {
        GoodService goodService = ApplicationContextUtil.getBean("goodService", GoodService.class);
        GoodDuihuanService goodDuihuanService = ApplicationContextUtil.getBean("goodDuihuanService", GoodDuihuanService.class);
        ScoreHistoryService scoreHistoryService = ApplicationContextUtil.getBean("scoreHistoryService", ScoreHistoryService.class);

        Good good = goodService.selectByPrimaryKey(goodId);
        if (good == null) {
            return MessageBean.GOOD_NOT_EXIST;
        }
        if (user.getScore() < good.getNeedScore()) {
            return MessageBean.LACK_OF_SCORE;
        }

        ScoreHistory scoreHistory = new ScoreHistory();
        scoreHistory.setScore(-1 * good.getNeedScore());
        scoreHistory.setEventId(EXCHANGE_GOOD);
        scoreHistory.setUserId(user.getId());
        scoreHistoryService.insert(scoreHistory);//触发器更新用户积分数据

        GoodDuihuan goodDuihuan = new GoodDuihuan();
        goodDuihuan.setUserId(user.getId());
        goodDuihuan.setGoodId(goodId);
        goodDuihuanService.insert(goodDuihuan);
        return MessageBean.SUCCESS;
    }

}
