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

/**
 * The type represents the command to change the username.
 */
public class ChangeProfileNameCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileNameCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        removeTempAttribute(request);
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String newName = request.getParameter(RequestParameter.NAME);
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        try {
            if (userService.isUserNameChange(login, newName)) {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_SAVE_CHANGES, locale);
                request.getSession().setAttribute(AttributeName.CHANGE_SAVED, attributeValue);
                request.getSession().setAttribute(AttributeName.USER_NAME, newName);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_NAME_CHANGES, locale);
                request.getSession().setAttribute(AttributeName.NAME_ERROR, attributeValue);
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
