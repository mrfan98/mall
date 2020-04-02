package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Spec;
import com.cskaoyan.mall.dao.GoodsDao;
import com.cskaoyan.mall.dao.OrderDao;
import com.cskaoyan.mall.dao.impl.GoodsDaoImpl;
import com.cskaoyan.mall.service.GoodsService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 18:47
 */

public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao=new GoodsDaoImpl();

    @Override
    public void deleteGoods(int id) throws SQLException {

    }

    @Override
    public List<Goods> queryGoodsByType(int typeId)throws SQLException {
        return goodsDao.queryGoodsByType(typeId);
    }

    @Override
    public void addGoods(Goods goods) {
        List<Spec> specList = goods.getSpecList();
        double price = specList.get(0).getUnitPrice();
        for (int i = 1; i < specList.size(); i++) {
            if(specList.get(i).getUnitPrice() < price){
                price = specList.get(i).getUnitPrice();
            }
        }
        goods.setPrice(price);
        int result = goodsDao.addGoods(goods);
        if(result == 200){
            int goodsId = goodsDao.queryLastId();
            goodsDao.addSpeces(goods.getSpecList(), goodsId);

        }
    }

    @Override
    public Goods getGoodInfo(int id) throws SQLException {
        return null;
    }


    @Override
    public void updateGoods(Goods goods) throws SQLException {

    }
}
