package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.Message;
import com.cskaoyan.mall.dao.MessageDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 21:29
 */

public class MessageDaoImpl implements MessageDao {
    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int reply(Message msg) {
        try {
            runner.update("update message set replyCont = ? where id = ?", msg.getContent(), msg.getId());
            return 200;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }

    @Override
    public List<Message> getMsg(int i) {
        try {
            return runner.query("select * from message where state = ？", new BeanListHandler<Message>(Message.class),i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
