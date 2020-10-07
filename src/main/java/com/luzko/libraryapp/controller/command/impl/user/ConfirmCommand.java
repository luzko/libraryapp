package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.configuration.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.user.UserStatus;
import com.luzko.libraryapp.service.user.UserService;
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
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        String codeConfirm = request.getParameter(RequestParameter.CODE);

        try {
            if (userService.isCodeConfirmCorrect(login, codeConfirm)) {
                request.getSession().setAttribute(RequestParameter.USER_STATUS, UserStatus.ACTIVE);
                router.setPagePath(PagePath.USER);
                router.setRouterType(RouterType.REDIRECT);
            } else {
                //TODO повторная отсылка пароля сразу же??
                request.getSession().setAttribute(RequestParameter.PARAM_CONFIRM_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_INCORRECT_CODE));
                router.setRouterType(RouterType.REDIRECT);
                router.setPagePath(PagePath.CONFIRMATION);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Confirm command error", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
        }
        return router;
    }
}
