package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        request.getSession().removeAttribute(RequestParameter.LOGIN);
        request.getSession().removeAttribute(RequestParameter.USER_ROLE);
        request.getSession().removeAttribute(RequestParameter.ALL_USERS);
        router.setPagePath(PagePath.HOME);
        router.setRouterType(RouterType.REDIRECT);

        return router;
    }
}
