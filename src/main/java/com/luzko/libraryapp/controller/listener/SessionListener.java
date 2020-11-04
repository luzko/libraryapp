package com.luzko.libraryapp.controller.listener;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * The type Session listener.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        Object userId = sessionEvent.getSession().getAttribute(AttributeName.USER_ID);
        Object userRole = sessionEvent.getSession().getAttribute(AttributeName.USER_ROLE);
        try {
            userService.giveBooksFromReadingRoom(userId, userRole);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in return books", e);
        }
    }
}
