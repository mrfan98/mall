package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Message;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.MessageService;
import com.cskaoyan.mall.service.impl.MessageServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 17:52
 */

@WebServlet("/api/admin/message/*")
public class MessageServlet extends HttpServlet {
    Gson gson = new Gson();
    MessageService msgService = new MessageServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/message/", "");
        if("reply".equals(action)){
            reply(request,response);
        }

    }

    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Message msg = gson.fromJson(requestBody, Message.class);
        Result res = new Result();
        int result =msgService.reply(msg);
        if (result == 200) {
            res.setCode(0);
        } else {
            res.setCode(10000);
            res.setMessage("访问异常，请稍后再试");
        }
        response.getWriter().write(gson.toJson(res));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/message/", "");
        if("noReplyMsg".equals(action)){
            getNoReplyMsg(request,response);
        }else if("repliedMsg".equals(action)){
            getRepliedMsg(request,response);
        }

    }

    private void getRepliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result res = new Result();
        List<Message> messages = null;
        messages = msgService.getRepliedMsg();
        if (messages != null) {
            res.setCode(0);
            res.setData(messages);
        } else {
            res.setCode(10000);
            res.setMessage("访问异常，请稍后再试");
        }
        response.getWriter().write(gson.toJson(res));
    }

    private void getNoReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result res = new Result();
        List<Message> messages = null;
        messages = msgService.getNoReplyMsg();
        if (messages != null) {
            res.setCode(0);
            res.setData(messages);
        } else {
            res.setCode(10000);
            res.setMessage("访问异常，请稍后再试");
        }
        response.getWriter().write(gson.toJson(res));

    }


}
