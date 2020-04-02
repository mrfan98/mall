package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.dao.OrderDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 16:36
 */

public class OrderDaoImpl implements OrderDao {
    @Override
    public Integer queryTotalCount(String s, List<Object> params) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        Long count = null;
        try {
            count = runner.query(s, new ScalarHandler<>(), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count.intValue();
    }

    @Override
    public List<Order> queryPageOrders(String s, List<Object> params) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Order> orders = null;
        try {
            orders = runner.query(s, new BeanListHandler<Order>(Order.class), params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;

    }
}
