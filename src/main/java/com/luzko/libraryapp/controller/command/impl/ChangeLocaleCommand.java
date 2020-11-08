package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * The type represents the command to change the locale.
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        if (locale.equals(AttributeValue.RUS)) {
            request.getSession().setAttribute(AttributeName.LOCALE, AttributeValue.ENG);
        } else if (locale.equals(AttributeValue.ENG)) {
            request.getSession().setAttribute(AttributeName.LOCALE, AttributeValue.RUS);
        }
        router.setPagePath(PagePath.HOME);
        return router;
    }
}
