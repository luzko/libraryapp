package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().invalidate();
        router.setPagePath(PagePath.HOME);
        router.setRouterType(RouterType.REDIRECT);
        return router;
    }
}