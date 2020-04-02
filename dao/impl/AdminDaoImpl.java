/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 2:52 下午
 */
package com.cskaoyan.mall.dao.impl;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.dao.AdminDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public int login(Admin admin) {
        //sql
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin query = null;
        try {
            query = runner.query("select * from admin where email = ? and pwd = ?",
                    new BeanHandler<>(Admin.class),
                    admin.getEmail(),
                    admin.getPwd());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        if(query != null){
            return 200;
        }
        return 404;
    }

    @Override
    public List<Admin> queryAllAdmins() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Admin> adminList = null;
        try {
            adminList = runner.query("select * from admin", new BeanListHandler<Admin>(Admin.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    @Override
    public List<Admin> querySearchAdmins(Admin admin) {
        String baseSql="select * from admin where 1 = 1";
        List<Object> params=new ArrayList<>();
        if(!StringUtils.isEmpty(admin.getEmail())){
            baseSql=baseSql+" and email like ?";
            params.add("%"+admin.getEmail()+"%");
        }
        if(!StringUtils.isEmpty(admin.getNickname())){
            baseSql=baseSql+" and nickname like ?";
            params.add("%"+admin.getNickname()+"%");
        }
        System.out.println(baseSql);
        QueryRunner runner=new QueryRunner(DruidUtils.getDataSource());
        List<Admin> admins=null;
        try {
            admins=runner.query(baseSql,new BeanListHandler<Admin>(Admin.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void addAdminss(Admin admin) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        runner.update("insert into admin values (null,?,?,?)",admin.getEmail(),admin.getNickname(),admin.getPwd());

    }

    @Override
    public Admin getAdminsInfo(int id) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Admin admin = runner.query("select * from admin where id = ?",new BeanHandler<>(Admin.class), id);
        return admin;
    }

    @Override
    public void updateAdminss(Admin admin) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        runner.update("update admin set email = ?,nickname = ? ,pwd = ? where id = ?",
                admin.getEmail(),admin.getNickname(),admin.getPwd(),admin.getId());
    }

    @Override
    public int deleteAdmins(int id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        int result = 0;
        try {
            result = queryRunner.update("delete from admin where id = ?",id);
        } catch (SQLException e) {
            return -1;
        }
        return result;

    }

    @Override
    public String getPwd(String email) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        String query = runner.query("select pwd from admin where email = ?",
                new BeanHandler<>(String.class), email);
        return query;
    }

    @Override
    public int updatePwd(String newPwd, String email) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        int i = runner.update("update admin set pwd = ? where email = ?", newPwd, email);
        return i;
    }
}
