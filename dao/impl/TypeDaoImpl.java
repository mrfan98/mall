package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.Type;
import com.cskaoyan.mall.dao.TypeDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 23:08
 */

public class TypeDaoImpl implements TypeDao {
    @Override
    public List<Type> getType() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Type> typeList = null;
        try {
            typeList = runner.query("select * from type", new BeanListHandler<Type>(Type.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public int deleteType(int typeId) throws SQLException {
        return 0;
    }

    @Override
    public int addType(Type type) {
        try {
            QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
            Long query = null;
            query = runner.query("select count(id) from type where name=?", new ScalarHandler<Long>(), type.getName());
            if (query == 0) {
                runner.update("insert into type values(null,?)", type.getName());
                return 200;
            }
            return 300;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 404;
    }
}
