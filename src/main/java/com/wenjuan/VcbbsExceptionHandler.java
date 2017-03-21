package com.wenjuan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenjuan.bean.MessageBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

public class VcbbsExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MessageBean messageBean;
        if (ex != null && ex.getMessage() != null && ex.getMessage().contains("Duplicate entry")) {
            messageBean = MessageBean.DATA_ALREADY_EXIST;
        } else {
            messageBean = MessageBean.SYSTEM_ERROR;
            if (ex != null && ex.getMessage() != null) {
                messageBean.setExtra(ex.getMessage());
            } else if (ex != null) {
                messageBean.setExtra(ex.getClass().getName());
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json");
        try {
            String jsonStr = new ObjectMapper().writeValueAsString(messageBean);
            Writer writer = response.getWriter();
            writer.write(jsonStr);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
