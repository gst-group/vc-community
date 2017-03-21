package com.wenjuan.utils;

import com.wenjuan.bean.AvailBean;
import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.ArticleEnable;
import com.wenjuan.service.ArticleEnableService;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

/**
 * Created by Gao Xun on 2016/6/17.
 */
public class ArticleUtil {
    public static MessageBean setAvail(ArticleEnableService articleEnableService, int id, String availUserOrGrp) {
        if (!StringUtils.isEmpty(availUserOrGrp)) {
            try {
                AvailBean availBean = (AvailBean) JSONObject.toBean(JSONObject.fromObject(availUserOrGrp), AvailBean.class);
                for (String str : availBean.getGroup()) {
                    if (str != null) {
                        ArticleEnable articleEnable = new ArticleEnable();
                        articleEnable.setArticleId(id);
                        articleEnable.setEnable(1);
                        articleEnable.setName(str);
                        articleEnableService.insert(articleEnable);
                    }
                }
                for (String str : availBean.getUser()) {
                    if (str != null) {
                        ArticleEnable articleEnable = new ArticleEnable();
                        articleEnable.setArticleId(id);
                        articleEnable.setEnable(2);
                        articleEnable.setName(str);
                        articleEnableService.insert(articleEnable);
                    }
                }
            } catch (Exception e) {
                return MessageBean.SYSTEM_ERROR;
            }
        }
        return MessageBean.SUCCESS;
    }
}
