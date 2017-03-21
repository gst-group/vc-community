package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.User;
import com.wenjuan.service.GoodDuihuanService;
import com.wenjuan.service.ScoreHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    @Qualifier("scoreHistoryService")
    private ScoreHistoryService scoreHistoryService;

    @Autowired
    @Qualifier("goodDuihuanService")
    private GoodDuihuanService goodDuihuanService;


    /**
     * 获取该用户的商品兑换记录
     *
     * @param order    排序顺序，默认时间降序，user_id,time,handled,name,need_score,good_id与asc,desc的组合
     * @param loadTime 字符A或B开头，后面跟上时间戳，分别表示After和Before
     * @param page     页数，0为不分页
     * @return extraList中的GoodDuihuanView对象数组
     */
    @RequestMapping(value = "/exchangeList")
    public MessageBean getExchangeList(@RequestParam(defaultValue = "time desc") String order, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "A0") String loadTime) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(goodDuihuanService.getAllRecord(order, loadTime, page, user));
        return messageBean;
    }

    /***
     * 积分获取记录
     *
     * @param order    排序顺序，默认时间降序，event_value,event_name,score,time,event_id与asc,desc的组合
     * @param loadTime 字符A或B开头，后面跟上时间戳，分别表示After和Before
     * @param page     页数,0为不分页
     * @return extraList中的ScoreHistoryView对象数组
     */
    @RequestMapping(value = "/gotList")
    public MessageBean getGotList(@RequestParam(defaultValue = "time desc") String order, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "A0") String loadTime) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(scoreHistoryService.selectMyScoreHistories(order, loadTime, page, user));
        return messageBean;
    }
    //获取积分规则

    @RequestMapping("/getScoreInfo")
    public MessageBean getScoreInfo(HttpServletResponse res) throws IOException {
        String content = scoreHistoryService.getScoreInfo().toString();
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtra(content);
        return messageBean;
    }
}
