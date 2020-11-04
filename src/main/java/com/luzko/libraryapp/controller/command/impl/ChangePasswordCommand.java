package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String password = request.getParameter(RequestParameter.PASSWORD_OLD);
        String passwordNew = request.getParameter(RequestParameter.PASSWORD_NEW);
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        try {
            if (userService.isVerifyUser(login, password) && userService.isChangePassword(login, passwordNew)) {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_SAVE_CHANGES, locale);
                request.getSession().setAttribute(AttributeName.CHANGE_SAVED, attributeValue);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_PASSWORD_ERROR, locale);
                request.setAttribute(AttributeName.ERROR_PASSWORD_MESSAGE, attributeValue);
            }
            router.setPagePath(PagePath.USER);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change login", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
