/**
 * User: zsquirrel
 * Date: 2020/4/1
 * Time: 10:33 上午
 */
package com.cskaoyan.mall.bean;

import java.util.List;

/**
 * 返回给前端的order订单数据的一个封装结果
 */
public class OrderResult {

    private Integer total;

    private List<Order> orders;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
