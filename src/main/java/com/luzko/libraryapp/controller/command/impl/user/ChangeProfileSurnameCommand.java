package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.configuration.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.service.user.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeProfileSurnameCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileSurnameCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String newSurname = request.getParameter(RequestParameter.SURNAME);

        try {
            if (userService.isUserSurnameChange(login, newSurname)) {
                request.getSession().setAttribute(RequestParameter.CHANGE_SAVED,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_SAVE_CHANGES));
                request.getSession().setAttribute(RequestParameter.SURNAME_ERROR, RequestParameter.EMPTY);
                request.getSession().setAttribute(RequestParameter.USER_SURNAME, newSurname);
            } else {
                request.getSession().setAttribute(RequestParameter.CHANGE_SAVED, RequestParameter.EMPTY);
                request.getSession().setAttribute(RequestParameter.NAME_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_SURNAME_CHANGES));
            }
            router.setPagePath(PagePath.USER);
            router.setRouterType(RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change status", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}