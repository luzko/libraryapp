package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;

public class CreateBookPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        request.setAttribute(RequestParameter.CREATE_TYPE, createType);
        router.setPagePath(PagePath.CREATE_BOOK);
        router.setRouterType(RouterType.FORWARD);
        return router;
    }
}
