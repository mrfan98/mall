package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Message;

import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 21:29
 */

public interface MessageDao {
    int reply(Message msg);

    List<Message> getMsg(int i);
}
