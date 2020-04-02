package com.cskaoyan.mall.dao;

import com.cskaoyan.mall.bean.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 23:08
 */

public interface TypeDao {
    List<Type> getType();

    int deleteType(int typeId)throws SQLException;
    int addType(Type type);
}
