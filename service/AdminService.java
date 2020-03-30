/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:49 下午
 */
package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;

public interface AdminService {
    int login(Admin admin);

    List<Admin> queryAllAdmins();

    List<Admin> querySearchAdmins(Admin admin);
}
