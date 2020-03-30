package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.impl.AdminServiceImpl;
import com.cskaoyan.mall.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: zsquirrel
 * Date: 2020/3/28
 * Time: 11:07 上午
 */
@WebServlet("/api/admin/admin/*")
public class AdminServlet extends HttpServlet {

   private AdminService adminService = new AdminServiceImpl();

    Gson gson = new Gson();

    /**
     * 登录、新增的时候是post请求
     * /api/admin/admin/login  登录
     * /api/admin/admin/addAdmin  新增
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        System.out.println(action);
        if("login".equals(action)){
            login(request, response);
        }else if("addAdminss".equals(action)){
            addAdminss(request, response);
        }else if("getSearchAdmins".equals(action)){
            getSearchAdmins(request,response);
        }
    }

    private void getSearchAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody=HttpUtils.getRequestBody(request);
        Admin admin=gson.fromJson(requestBody,Admin.class);
        List<Admin> admins=adminService.querySearchAdmins(admin);
        Result result=new Result();
        result.setCode(0);
        result.setData(admins);
        response.getWriter().println(gson.toJson(result));


    }

    /**
     * 新增管理员业务逻辑
     * @param request
     * @param response
     */
    private void addAdminss(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 登录的具体业务逻辑实现
     * 思路：从请求体中获取到参数
     *      解析出admin对象
     *      数据库查询结果
     *      返回结果
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestBody = HttpUtils.getRequestBody(request);
        Admin admin = gson.fromJson(requestBody, Admin.class);
        //调用service层
        int result = adminService.login(admin);
        Result res = new Result();
        if(result == 200){
            res.setCode(0);
            Map<String, String> map = new HashMap<>();
            map.put("token", admin.getEmail());
            map.put("name", admin.getNickname());
            res.setData(map);
        }else if(result == 404){
            res.setCode(10000);
            res.setMessage("用户名或者密码错误");
        }else{
            res.setCode(10000);
            res.setMessage("当前访问异常，稍后重试");
        }
        response.getWriter().println(gson.toJson(res));
    }

    /**
     * 查询、删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/admin/", "");
        if("allAdmins".equals(action)){
            allAdmins(request, response);
        }
    }

    /**
     * 查询全部的admin管理员信息
     * @param request
     * @param response
     */
    private void allAdmins(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Admin> adminList = adminService.queryAllAdmins();
        Result result = new Result();
        result.setCode(0);
        result.setData(adminList);
        response.getWriter().println(gson.toJson(result));
    }
}
