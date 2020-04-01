package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.GoodsService;
import com.cskaoyan.mall.service.impl.GoodsServiceImpl;
import com.cskaoyan.mall.utils.FileUploadUtils;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 张凡 on 2020/3/31 18:41
 */

@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {
    GoodsService goodsService = new GoodsServiceImpl();
    Gson gson = new Gson();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if("imgUpload".equals(action)){
            imgUpload(request, response);
        }else if("addGoods".equals(action)){
            addGoods(request, response);
        }


    }

    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Goods goods = gson.fromJson(requestBody, Goods.class);
        int result = goodsService.addGoods(goods);
        Result rs = new Result();
        if(result == 200){
            rs.setCode(0);
        }
        response.getWriter().println(gson.toJson(rs));

    }

    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String domain = (String) request.getServletContext().getAttribute("domain");
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file = (String) map.get("file");
        file = domain + file;
        Result result = new Result();
        result.setCode(0);
        result.setData(file);
        response.getWriter().println(gson.toJson(result));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if("getGoodsByType".equals(action)){
            getGoodsByType(request, response);
        }

    }

    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result=new Result();
        String s=request.getParameter("typeId");
        if (s == null || s.length() == 0) {
            result.setCode(10000);
            result.setMessage("参数错误");
            response.getWriter().println(gson.toJson(result));
            return;
        }
        try {
            int typeId = Integer.parseInt(s);
            List<Goods> goodsList = goodsService.queryGoodsByType(typeId);
            result.setCode(0);
            result.setData(goodsList);
        }catch(SQLException e){
            result.setCode(10000);
            result.setMessage("服务器繁忙");
        }catch(NumberFormatException e){
            result.setCode(10000);
            result.setMessage("参数错误");
        }finally {
            response.getWriter().println(gson.toJson(result));

        }
    }
}
