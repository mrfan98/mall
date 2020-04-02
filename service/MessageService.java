package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Message;

import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 21:24
 */

public interface MessageService {
    int reply(Message msg);

    List<Message> getRepliedMsg();

    List<Message> getNoReplyMsg();
}
