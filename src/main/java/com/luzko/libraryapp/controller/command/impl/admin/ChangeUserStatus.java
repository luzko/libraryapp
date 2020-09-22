package com.luzko.libraryapp.controller.command.impl.admin;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeUserStatus implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserStatus.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String login = request.getParameter(RequestParameter.LOGIN);
        Boolean isEnable = Boolean.valueOf(request.getParameter(RequestParameter.ENABLED));

        try {
            if (userService.changeUserStatus(login, isEnable)) {
                List<User> users = userService.findAll();
                request.getSession().setAttribute(RequestParameter.ALL_USERS, users);
                router.setPagePath(PagePath.ADMIN);
                router.setRouterType(RouterType.REDIRECT);
            } else {
                //TODO что-то пошло не так..
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change status", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
