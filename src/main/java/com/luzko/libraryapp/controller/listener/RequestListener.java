package com.luzko.libraryapp.controller.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@WebListener
public class RequestListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        System.out.println(1);

        ServletRequest servletRequest = requestEvent.getServletRequest();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();


        Enumeration<String> keys = session.getAttributeNames();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key);
        }
        System.out.println(2);
    }
}
