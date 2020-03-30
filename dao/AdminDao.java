/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:52 下午
 */
package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;

public interface AdminDao {
    int login(Admin admin);

    List<Admin> queryAllAdmins();

    List<Admin> querySearchAdmins(Admin admin);
}
