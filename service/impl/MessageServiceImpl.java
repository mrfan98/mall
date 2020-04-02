package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.Message;
import com.cskaoyan.mall.dao.MessageDao;
import com.cskaoyan.mall.dao.impl.MessageDaoImpl;
import com.cskaoyan.mall.service.MessageService;

import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 21:24
 */

public class MessageServiceImpl implements MessageService {
    MessageDao msgDao = new MessageDaoImpl();
    @Override
    public int reply(Message msg) {
        return msgDao.reply(msg);
    }

    @Override
    public List<Message> getRepliedMsg() {
        return msgDao.getMsg(0);
    }

    @Override
    public List<Message> getNoReplyMsg() {
        return msgDao.getMsg(1);
    }
}
