package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.bean.Type;
import com.cskaoyan.mall.service.TypeService;
import com.cskaoyan.mall.service.impl.TypeServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 张凡 on 2020/3/31 22:56
 */

@WebServlet("/api/admin/type/*")
public class TypeServlet extends HttpServlet {
    TypeService typeService = new TypeServiceImpl();
    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/type/", "");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/type/", "");
        if("getType".equals(action)){
            getType(request, response);
        }

    }

    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Type> typeList = typeService.getType();
        Result result = new Result();
        result.setCode(0);
        result.setData(typeList);
        response.getWriter().println(gson.toJson(result));


    }
}
