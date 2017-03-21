package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.Good;
import com.wenjuan.model.User;
import com.wenjuan.service.GoodService;
import com.wenjuan.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/good")
public class GoodController {
    @Autowired
    @Qualifier("goodService")
    private GoodService goodService;

    /**
     * 返回所有商品信息。
     *
     * @param loadId 字符A或B开头，后面跟上id，分别表示After和Before,例如A0表示id为0之后的商品
     * @param page   页数,0不分页
     * @param order  time，id,need_score,exchange_count,number等与desc,asc的组合
     * @return extraList中的Good对象数组
     */
    @RequestMapping(value = "/getAll")
    public MessageBean getAll(@RequestParam(defaultValue = "A0") String loadId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "time desc") String order) {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        if (loadId == null) {
            loadId = null;
        }
        messageBean.setExtraList(goodService.getAllGood(page, null, loadId, order).toArray());
        return messageBean;
    }

    /**
     * 获取商品详细信息。
     *
     * @param id 商品id
     * @return extra中的Good对象
     */
    @RequestMapping(value = "/getDetail")
    public MessageBean getDetail(@RequestParam int id) {
        Good good = goodService.selectByPrimaryKey(id);
        if (good == null) {
            return MessageBean.GOOD_NOT_EXIST;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(good);
        return messageBean;
    }

    /**
     * 兑换商品
     *
     * @param id 商品id
     * @return
     */
    @RequestMapping(value = "/exchange")
    public MessageBean exchange(@RequestParam int id) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        return ScoreUtil.exchange(id, user);
    }
}
