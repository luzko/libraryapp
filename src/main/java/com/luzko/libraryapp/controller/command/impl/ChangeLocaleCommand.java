package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(RequestParameter.LOCALE);

        if (locale.equals(RequestParameter.RUS)) {
            request.getSession().setAttribute(RequestParameter.LOCALE, RequestParameter.ENG);
        } else if (locale.equals(RequestParameter.ENG)) {
            request.getSession().setAttribute(RequestParameter.LOCALE, RequestParameter.RUS);
        }

        router.setPagePath(PagePath.HOME);
        router.setRouterType(RouterType.FORWARD);
        return router;
    }
}
