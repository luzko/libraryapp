package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.model.validator.ValueValidator;
import com.luzko.libraryapp.util.mail.EmailSender;

import javax.servlet.http.HttpServletRequest;

public class SendMessageAdmin implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        String subject = request.getParameter(RequestParameter.SUBJECT);
        String message = request.getParameter(RequestParameter.MESSAGE);
        String typeMessage = request.getParameter(RequestParameter.TYPE_MESSAGE);
        if (ValueValidator.isValidMessage(message) && ValueValidator.isValidSubject(subject)) {
            EmailSender.sendMessageAdmin(login, subject, message);
        }
        router.setPagePath(typeMessage.equals(AttributeName.ACTIVE) ? PagePath.USER : PagePath.BLOCKED);
        return router;
    }
}
