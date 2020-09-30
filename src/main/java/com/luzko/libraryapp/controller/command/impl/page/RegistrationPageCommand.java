package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        router.setPagePath(PagePath.REGISTRATION);
        router.setRouterType(RouterType.REDIRECT);
        return router;
    }
}
