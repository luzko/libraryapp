package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        if (locale.equals(AttributeName.RUS)) {
            request.getSession().setAttribute(AttributeName.LOCALE, AttributeName.ENG);
        } else if (locale.equals(AttributeName.ENG)) {
            request.getSession().setAttribute(AttributeName.LOCALE, AttributeName.RUS);
        }
        router.setPagePath(PagePath.HOME);
        return router;
    }
}
