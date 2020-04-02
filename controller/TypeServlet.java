package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.bean.Type;
import com.cskaoyan.mall.service.TypeService;
import com.cskaoyan.mall.service.impl.TypeServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
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
        if("addType".equals(action)){
            addType(request,response);
        }

    }

    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parseRequest = HttpUtils.getRequestBody(request);
        Type type = gson.fromJson(parseRequest, Type.class);
        Result result = new Result();
        int flag = typeService.addType(type);
            if(flag == 200){
                result.setCode(0);
            } else {
                result.setCode(10000);
                if(flag==300)
                result.setMessage("类名重复");
                else{
                    result.setMessage("服务器繁忙，请重试");
                }
                response.getWriter().println(gson.toJson(result));
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/type/", "");
        if("getType".equals(action)){
            getType(request, response);
        }else if("deleteType".equals(action)){
            deleteType(request,response);
        }

    }

    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        String s = request.getParameter("typeId");
        if (s == null || s.length() == 0) {
            result.setCode(10000);
            result.setMessage("参数错误");
            response.getWriter().println(gson.toJson(result));
            return;
        }
        try {
            int typeId = Integer.parseInt(s);
            int flag = typeService.deleteType(typeId);
            if (flag == 1) {
                result.setCode(0);
            } else {
                result.setCode(10000);
                result.setMessage("删除失败,没有此id");
            }
        } catch (SQLException e) {
            result.setCode(10000);
            result.setMessage("服务器繁忙，请重试");
        } catch (NumberFormatException e){
            result.setCode(10000);
            result.setMessage("参数错误");
        } finally {
            response.getWriter().println(gson.toJson(result));
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
