package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Type;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 23:04
 */

public interface TypeService {
    List<Type> getType();

    int addType(Type type);


    int deleteType(int typeId)throws SQLException;
}
