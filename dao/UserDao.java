package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 19:16
 */

public interface UserDao {
    List<User> getSearchUser(String nickname)throws SQLException;

    int deleteUser(int id)throws SQLException;

    List<User> allUsers() throws SQLException;

}
