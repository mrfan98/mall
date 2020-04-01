package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Spec;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 21:26
 */

public interface GoodsDao {
    List<Goods> queryGoodsByType(int typeId)throws SQLException;


    int addGoods(Goods goods);

    int queryLastId();

    void addSpeces(List<Spec> specList, int goodsId);
}
