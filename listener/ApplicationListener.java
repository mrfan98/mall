package com.cskaoyan.mall.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 张凡 on 2020/3/31 18:32
 */
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties=new Properties();
        InputStream inputStream=ApplicationListener.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
            String domain=(String)properties.get("domain");
            servletContextEvent.getServletContext().setAttribute("domain",domain);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
