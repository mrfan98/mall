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

import java.sql.SQLException;
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

    @Override
    public int addAdminss(Admin admin) throws SQLException {
        Admin admin1=new Admin();
        admin1.setEmail(admin.getEmail());
        int size=adminDao.querySearchAdmins(admin1).size();
        if(size!=0){
            return 0;
        }
        adminDao.addAdminss(admin);
        return 1;
    }

    @Override
    public Admin getAdminsInfo(int id) throws SQLException {
        return adminDao.getAdminsInfo(id);
    }

    @Override
    public int updateAdminss(Admin admin) throws SQLException {
        Admin admin1 = adminDao.getAdminsInfo(admin.getId());
        if(!admin1.getEmail().equals(admin.getEmail())){
            Admin admin2 = new Admin();
            admin2.setEmail(admin.getEmail());
            int size = adminDao.querySearchAdmins(admin2).size();
            if(size != 0){
                return 0;
            }
        }
        adminDao.updateAdminss(admin);
        return 1;
    }
}
