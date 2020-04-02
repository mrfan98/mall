package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.OrderParam;
import com.cskaoyan.mall.bean.OrderResult;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.OrderService;
import com.cskaoyan.mall.service.impl.OrderServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 张凡 on 2020/4/1 14:59
 */

@WebServlet("/api/admin/order/*")
public class OrderServlet extends HttpServlet {
    Gson gson = new Gson();
    OrderService orderService = new OrderServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");
        if("ordersByPage".equals(action)){
            ordersByPage(request, response);
        }else if("addGoods".equals(action)){

        }

    }

    private void ordersByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        OrderParam orderParam = gson.fromJson(requestBody, OrderParam.class);
        System.out.println(orderParam);
        OrderResult orderResult = orderService.ordersByPage(orderParam);
        Result result = new Result();
        result.setCode(0);
        result.setData(orderResult);
        response.getWriter().println(gson.toJson(result));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/order/", "");

    }
}
