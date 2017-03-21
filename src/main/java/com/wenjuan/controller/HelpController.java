package com.wenjuan.controller;

import com.wenjuan.bean.MessageBean;
import com.wenjuan.dao.HelpMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/help")
public class HelpController {

    @Resource
    HelpMapper helpMapper;

    /**
     * 返回所有帮助信息
     *
     * @return extraList中的帮助信息Help对象数组。
     */
    @RequestMapping(value = "/getAll")
    public MessageBean getAll() {
        MessageBean messageBean = MessageBean.SUCCESS.clone();
        messageBean.setExtraList(helpMapper.selectAllHelps().toArray());
        return messageBean;
    }
}
