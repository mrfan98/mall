package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 18:46
 */

public interface GoodsService {
    List<Goods> queryGoodsByType(int typeId)throws SQLException;

    int addGoods(Goods goods);
}
