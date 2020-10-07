package com.luzko.libraryapp.controller.command.impl.admin;

import com.luzko.libraryapp.configuration.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.user.User;
import com.luzko.libraryapp.service.user.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeUserStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserStatusCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter(RequestParameter.LOGIN);
        String userStatus = request.getParameter(RequestParameter.USER_STATUS);

        try {
            boolean isChangeUserStatus = userService.changeUserStatus(login, userStatus);
            List<User> users = userService.findAll();
            request.getSession().setAttribute(RequestParameter.ALL_USERS, users);
            router.setPagePath(PagePath.ADMIN);
            router.setRouterType(RouterType.REDIRECT);
            if (!isChangeUserStatus) {
                logger.log(Level.WARN, "User status is not change");
                request.setAttribute(RequestParameter.ERROR_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_STATUS_CHANGES));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change status", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
