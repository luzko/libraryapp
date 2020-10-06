package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.service.user.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeProfileLoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String newLogin = request.getParameter(RequestParameter.LOGIN);
        UserService userService = UserServiceImpl.getInstance();

        try {
            if (userService.isUserLoginChange(login, newLogin)) {
                request.getSession().setAttribute(RequestParameter.CHANGE_SAVED, "Change saved");
                request.getSession().setAttribute(RequestParameter.LOGIN_ERROR, "");
                request.getSession().setAttribute(RequestParameter.LOGIN, newLogin);
            } else {
                request.getSession().setAttribute(RequestParameter.CHANGE_SAVED, "");
                request.getSession().setAttribute(RequestParameter.LOGIN_ERROR, "User login is not change");
            }
            router.setPagePath(PagePath.USER);
            router.setRouterType(RouterType.REDIRECT);
        } catch (ServiceException e) {
            //logger.log(Level.ERROR, "Error in change status", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e); //TODO нужно ли e?
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }

        return router;
    }
}
