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

public class ChangeProfileNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileNameCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String newName = request.getParameter(RequestParameter.NAME);
        try {
            if (userService.isUserNameChange(login, newName)) {
                request.getSession().setAttribute(AttributeName.CHANGE_SAVED,
                        ConfigurationManager.getMessageProperty(AttributeValue.PATH_SAVE_CHANGES));
                //request.getSession().setAttribute(AttributeName.NAME_ERROR, RequestParameter.EMPTY);
                request.getSession().setAttribute(AttributeName.USER_NAME, newName);
            } else {
                //request.getSession().setAttribute(AttributeName.CHANGE_SAVED, RequestParameter.EMPTY);
                request.getSession().setAttribute(AttributeName.NAME_ERROR,
                        ConfigurationManager.getMessageProperty(AttributeValue.PATH_NAME_CHANGES));
            }
            router.setPagePath(PagePath.USER);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change name", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
