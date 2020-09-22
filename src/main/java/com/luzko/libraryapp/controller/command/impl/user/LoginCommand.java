package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        try {
            if (userService.verifyUser(login, password)) {
                Optional<User> loggedUser = userService.findByLogin(login);
                if (loggedUser.isPresent()) {
                    User user = loggedUser.get();
                    if (user.isEnabled()) {
                        UserRole userRole = user.getUserRole();
                        switch (userRole) {
                            case READER -> {
                                router.setPagePath(PagePath.READER);
                                router.setRouterType(RouterType.REDIRECT);
                            }
                            case LIBRARIAN -> {
                                router.setPagePath(PagePath.LIBRARIAN);
                                router.setRouterType(RouterType.REDIRECT);
                            }
                            case ADMIN -> {
                                List<User> users = userService.findAll();
                                request.getSession().setAttribute(RequestParameter.ALL_USERS, users);
                                router.setPagePath(PagePath.ADMIN);
                                router.setRouterType(RouterType.REDIRECT);
                            }
                        }
                    } else {
                        //TODO
                        //TODO
                        //TODO new page. Данный пользователь заблокирован. Для разблокировки обратитесь к админу(библиотекарю..);
                    }
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, "User is incorrect"); //TODO
                    router.setPagePath(PagePath.ERROR);
                    router.setRouterType(RouterType.FORWARD);
                }
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
