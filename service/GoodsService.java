package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Goods;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 18:46
 */

public interface GoodsService {

    void deleteGoods(int id)throws SQLException;

    List<Goods> queryGoodsByType(int typeId)throws SQLException;

    void addGoods(Goods goods) throws SQLException;

    Goods getGoodInfo(int id) throws SQLException;

    void updateGoods(Goods goods) throws SQLException;
}
