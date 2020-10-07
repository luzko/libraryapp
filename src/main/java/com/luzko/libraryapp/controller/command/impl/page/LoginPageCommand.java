package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;

public class LoginPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PagePath.LOGIN);
        router.setRouterType(RouterType.FORWARD);
        return router;
    }
}
