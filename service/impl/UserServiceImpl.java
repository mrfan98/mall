package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.dao.UserDao;
import com.cskaoyan.mall.dao.impl.UserDaoImpl;
import com.cskaoyan.mall.service.UserService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 19:13
 */

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public List<User> getSearchUser(String nickname) throws SQLException {
        return userDao.getSearchUser(nickname);
    }

    @Override
    public int deleteUser(int id) throws SQLException {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> allUsers() throws SQLException {
        return userDao.allUsers();
    }
}
