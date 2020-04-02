package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Order;

import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 16:36
 */

public interface OrderDao {
    Integer queryTotalCount(String s, List<Object> params);

    List<Order> queryPageOrders(String s, List<Object> params);
}
