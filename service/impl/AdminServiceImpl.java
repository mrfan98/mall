/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:49 下午
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.dao.impl.AdminDaoImpl;
import com.cskaoyan.mall.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();


    public int login(Admin admin) {
        return adminDao.login(admin);
    }


    public List<Admin> queryAllAdmins() {
        return adminDao.queryAllAdmins();
    }

    @Override
    public List<Admin> querySearchAdmins(Admin admin) {
        return adminDao.querySearchAdmins(admin);
    }
}
