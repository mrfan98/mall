package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Type;
import com.cskaoyan.mall.dao.TypeDao;
import com.cskaoyan.mall.dao.impl.TypeDaoImpl;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.TypeService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 23:05
 */

public class TypeServiceImpl implements TypeService {
    TypeDao typeDao=new TypeDaoImpl();
    @Override
    public List<Type> getType() {
        return typeDao.getType();
    }

    @Override
    public int addType(Type type){
        return typeDao.addType(type);

    }

    @Override
    public int deleteType(int typeId) throws SQLException {
        return 0;

    }
}
