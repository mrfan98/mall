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
import java.util.Map;

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

    @Override
    public int deleteAdmins(int id) {
        return adminDao.deleteAdmins(id);
    }

    @Override
    public String changePwd(Map map) throws SQLException {
        String email =(String) map.get("adminToken");
        String pwd = (String) map.get("oldPwd");
        String daoPwd = adminDao.getPwd(email);
        if(pwd == null || pwd.equals(daoPwd)){
            return "旧密码错误";
        }
        String newPwd = (String) map.get("newPwd");
        String confirmPwd = (String) map.get("confirmPwd");
        if(newPwd == null || !newPwd.equals(confirmPwd)){
            return "两次输入不一致或者为空";
        }
        int flag = adminDao.updatePwd(newPwd,email);
        if(flag == 0){
            return "没有这个用户";
        }
        return "修改成功";

    }
}
