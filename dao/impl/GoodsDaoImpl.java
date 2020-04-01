package com.cskaoyan.mall.dao.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Spec;
import com.cskaoyan.mall.dao.GoodsDao;
import com.cskaoyan.mall.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 21:34
 */

public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Goods> queryGoodsByType(int typeId) throws SQLException {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        List<Goods> goodsList = null;
        if (typeId == -1) {
            goodsList = runner.query("select * from goods", new BeanListHandler<>(Goods.class));
        } else {
            goodsList = runner.query("select * from goods where typeId=?", new BeanListHandler<>(Goods.class), typeId);
        }
        return goodsList;
    }

    @Override
    public int addGoods(Goods goods) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        try {
            runner.update("insert into goods values (null,?,?,?,?,?,null)",
                    goods.getName(),
                    goods.getImg(),
                    goods.getDesc(),
                    goods.getPrice(),
                    goods.getTypeId());
        } catch (SQLException e) {
            e.printStackTrace();
            return 500;
        }
        return 200;
    }

    @Override
    public int queryLastId() {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        BigInteger query = null;
        try {
            query = runner.query("select last_insert_id()", new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query.intValue();
    }

    @Override
    public void addSpeces(List<Spec> specList, int goodsId) {
        QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
        for (Spec spec : specList) {
            try {
                runner.update("insert into spec values (null,?,?,?,?)",
                        spec.getSpecName(),
                        spec.getStockNum(),
                        spec.getUnitPrice(),
                        goodsId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
