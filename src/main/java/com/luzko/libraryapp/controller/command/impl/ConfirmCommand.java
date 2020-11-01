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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

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
                request.getSession().setAttribute(AttributeName.PARAM_CONFIRM_ERROR,
                        ConfigurationManager.getMessageProperty(AttributeValue.PATH_INCORRECT_CODE));
                router.setPagePath(PagePath.CONFIRMATION);
            }
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Confirm command error", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
