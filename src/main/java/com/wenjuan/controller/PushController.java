package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.model.User;
import com.wenjuan.service.NotifiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class PushController {
    @Autowired
    @Qualifier("notifiesService")
    private NotifiesService notifiesService;

    @RequestMapping("/getAll")
    public MessageBean getAll(@RequestParam(defaultValue = "A0") String loadId,
                              @RequestParam(defaultValue = "create_time desc") String order,
                              @RequestParam(defaultValue = "1") int page) {
        User user = User.getCurrentUser();
        if (user == null) {
            return MessageBean.UNLOGIN;
        }
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(notifiesService.selectNotifyViews(loadId, order, page, user));
        return messageBean;
    }
}
