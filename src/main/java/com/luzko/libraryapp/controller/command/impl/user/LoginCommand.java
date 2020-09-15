package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        try {
            if (service.checkLogin(login, password)) {
                router.setPagePath(PagePath.MAIN);
                router.setRouterType(RouterType.REDIRECT);
            } else {
                request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE, "Incorrect login or password");
                router.setPagePath(PagePath.LOGIN);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in login", e);
            request.setAttribute(RequestParameter.ERROR_MESSAGE, e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
