package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Result;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.impl.UserServiceImpl;
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
 * Created by 张凡 on 2020/4/1 19:01
 */

@WebServlet("/api/admin/user/*")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/user/", "");
        if("allUser".equals(action)){
            allUsers(request,response);
        }else if("deleteUser".equals(action)){
            deleteUser(request,response);
        }else if("searchUser".equals(action)){
            getSearchUser(request,response);
        }

    }

    private void getSearchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nickname = request.getParameter("word");
        Result result = new Result();
        if(nickname == null){
            result.setCode(10000);
            result.setMessage("参数错误");
            response.getWriter().println(gson.toJson(result));
            return;
        }
        try {
            List<User> userList = userService.getSearchUser(nickname);
            result.setCode(0);
            result.setData(userList);
        } catch (SQLException e) {
            result.setCode(10000);
            result.setMessage("服务器繁忙");
        } finally {
            response.getWriter().println(gson.toJson(result));
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getParameter("id");
        Result result = new Result();
        if(s == null || s.length() == 0){
            result.setCode(10000);
            result.setMessage("参数错误");
            return;
        }
        try {
            int id = Integer.parseInt(s);
            int flag = userService.deleteUser(id);
            if(flag == 1){
                result.setCode(0);
            } else {
                result.setCode(10000);
                result.setMessage("没有这个用户");
            }
        } catch (NumberFormatException e) {
            result.setCode(10000);
            result.setMessage("参数错误");
        } catch (SQLException e) {
            result.setCode(10000);
            result.setMessage("服务器繁忙");
        } finally {
            response.getWriter().println(gson.toJson(result));
        }

    }

    private void allUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = new Result();
        try {
            List<User> userList = userService.allUsers();
            result.setCode(0);
            result.setData(userList);
        } catch (SQLException e) {
            result.setCode(10000);
            result.setMessage("当前服务繁忙");
        } finally {
            response.getWriter().println(gson.toJson(result));
        }

    }
}
