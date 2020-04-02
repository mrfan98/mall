package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 19:16
 */

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> getSearchUser(String nickname) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> userList = runner.query("select * from user where nickname like ?",
                new BeanListHandler<>(User.class), "%" + nickname + "%");
        return userList;
    }

    @Override
    public int deleteUser(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int update = runner.update("delete from user where id = ?", id);
        return update;
    }

    @Override
    public List<User> allUsers() throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<User> userList = runner.query("select * from user", new BeanListHandler<>(User.class));
        return userList;
    }
}
