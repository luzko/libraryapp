package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import com.luzko.libraryapp.util.AlertManager;
import com.luzko.libraryapp.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String ATTR_USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        try {
            if (service.checkLogin(login, password)) {
                request.setAttribute(ATTR_USER, login);
                page = PathManager.getProperty(PathManager.KEY_PAGE_MAIN);
            } else {
                request.setAttribute(AlertManager.getClearName(AlertManager.KEY_LOGIN_ERROR),
                        AlertManager.getProperty(AlertManager.KEY_LOGIN_ERROR));
                page = PathManager.getProperty(PathManager.KEY_PAGE_LOGIN);
            }
        } catch (ServiceException e) {
            logger.error(e);
            page = PathManager.getProperty(PathManager.KEY_PAGE_INDEX);
        }
        return page;
    }

}
