package com.cskaoyan.mall.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderParam;
import com.cskaoyan.mall.bean.OrderResult;
import com.cskaoyan.mall.dao.OrderDao;
import com.cskaoyan.mall.dao.impl.OrderDaoImpl;
import com.cskaoyan.mall.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张凡 on 2020/4/1 16:34
 */

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public OrderResult ordersByPage(OrderParam orderParam) {
        String suffix = "";
        List<Object> params = new ArrayList<>();
        if(orderParam.getState() != -1){
            suffix = suffix + " and stateId = ?";
            params.add(orderParam.getState());
        }
        if(!StringUtils.isEmpty(orderParam.getMoneyLimit1())){
            suffix = suffix + " and amount <= ?";
            params.add(orderParam.getMoneyLimit1());
        }
        if(!StringUtils.isEmpty(orderParam.getMoneyLimit2())){
            suffix += " and amount >= ?";
            params.add(orderParam.getMoneyLimit2());
        }
        if(!StringUtils.isEmpty(orderParam.getGoods())){
            suffix += " and goods like ?";
            params.add("%" + orderParam.getGoods() + "%");
        }
        if(!StringUtils.isEmpty(orderParam.getAddress())){
            suffix += " and address like ?";
            params.add("%" + orderParam.getAddress() + "%");
        }
        if(!StringUtils.isEmpty(orderParam.getName())){
            suffix += " and name like ?";
            params.add("%" + orderParam.getName() + "%");
        }
        if(!StringUtils.isEmpty(orderParam.getId())){
            suffix += " and id = ?";
            params.add(orderParam.getId());
        }
        String totalSql = "select count(id) from order where 1 = 1 ";
        System.out.println(totalSql + suffix);
        Integer count = orderDao.queryTotalCount(totalSql + suffix, params);

        String pageSql = " limit ? offset ?";
        params.add(orderParam.getPagesize());
        params.add((orderParam.getCurrentPage() - 1) * orderParam.getPagesize());
        String ordersSql = "select * from order where 1 = 1 ";
        System.out.println(ordersSql + suffix + pageSql);
        List<Order> orders = orderDao.queryPageOrders(ordersSql + suffix + pageSql, params);
        OrderResult orderResult = new OrderResult();
        orderResult.setTotal(count);
        orderResult.setOrders(orders);
        return orderResult;

    }
}
