/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:52 下午
 */
package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Admin;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    int login(Admin admin);

    List<Admin> queryAllAdmins();

    List<Admin> querySearchAdmins(Admin admin);

    void addAdminss(Admin admin) throws SQLException;

    Admin getAdminsInfo(int id) throws SQLException;

    void updateAdminss(Admin admin)throws SQLException;

    int deleteAdmins(int id);

    String getPwd(String email)throws SQLException;

    int updatePwd(String newPwd, String email)throws SQLException;
}
