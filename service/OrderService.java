package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.OrderParam;
import com.cskaoyan.mall.bean.OrderResult;

/**
 * Created by 张凡 on 2020/4/1 16:32
 */

public interface OrderService {
    OrderResult ordersByPage(OrderParam orderParam);
}
