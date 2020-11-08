package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.UserStatus;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.ConfigurationManager;
import com.luzko.libraryapp.util.mail.EmailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type represents the command to verify a user account.
 */
public class ConfirmCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ConfirmCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        String codeConfirm = request.getParameter(RequestParameter.CODE);
        try {
            if (userService.isCodeConfirmCorrect(login, codeConfirm)) {
                request.getSession().setAttribute(AttributeName.USER_STATUS, UserStatus.ACTIVE);
                router.setPagePath(PagePath.USER);
            } else {
                sendCodeConfirm(router, userService, request);
            }
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Confirm command error", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private void sendCodeConfirm(Router router, UserService userService, HttpServletRequest request) throws ServiceException {
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        String email = (String) request.getSession().getAttribute(AttributeName.EMAIL);
        String codeConfirm = userService.findCodeConfirm(login);
        EmailSender.sendMessageConfirm(email, codeConfirm);
        String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_INCORRECT_CODE,
                (String) request.getSession().getAttribute(AttributeName.LOCALE));
        request.getSession().setAttribute(AttributeName.PARAM_CONFIRM_ERROR, attributeValue);
        router.setPagePath(PagePath.CONFIRMATION);
    }
}
