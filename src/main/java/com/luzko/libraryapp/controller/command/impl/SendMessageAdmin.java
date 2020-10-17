package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.util.mail.EmailSender;

import javax.servlet.http.HttpServletRequest;

public class SendMessageAdmin implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String subject = request.getParameter(RequestParameter.SUBJECT);
        String message = request.getParameter(RequestParameter.MESSAGE);
        EmailSender.setMessageAdmin(login, subject, message);
        router.setPagePath(PagePath.BLOCKED);
        router.setRouterType(RouterType.REDIRECT);
        return router;
    }
}
