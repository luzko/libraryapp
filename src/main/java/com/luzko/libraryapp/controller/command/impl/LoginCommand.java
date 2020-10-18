package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.util.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.CommandException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.entity.UserStatus;
import com.luzko.libraryapp.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        try {
            if (userService.verifyUser(login, password)) {
                Optional<User> userOptional = userService.findByLogin(login);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    router = defineRouterByStatus(user, request);
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_INCORRECT_USER));
                    router.setPagePath(PagePath.ERROR);
                    router.setRouterType(RouterType.FORWARD);
                }
            } else {
                request.setAttribute(RequestParameter.ERROR_LOGIN_PASSWORD_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_LOGIN_ERROR));
                router.setPagePath(PagePath.LOGIN);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException | CommandException e) {
            logger.log(Level.ERROR, "Error in login", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private Router defineRouterByStatus(User user, HttpServletRequest request) throws ServiceException, CommandException {
        UserStatus userStatus = user.getUserStatus();
        Router router = new Router();
        HttpSession session = request.getSession();
        session.setAttribute(RequestParameter.USER_ID, user.getUserId());
        session.setAttribute(RequestParameter.LOGIN, user.getLogin());
        session.setAttribute(RequestParameter.USER_ROLE, user.getUserRole());
        session.setAttribute(RequestParameter.USER_STATUS, userStatus);
        session.setAttribute(RequestParameter.USER_NAME, user.getName());
        session.setAttribute(RequestParameter.USER_SURNAME, user.getSurname());
        session.setAttribute(RequestParameter.EMAIL, user.getEmail());

        session.setAttribute(RequestParameter.TYPE_PROFILE_PAGE, RequestParameter.SEE_PROFILE_PAGE);

        switch (userStatus) {
            case ACTIVE -> {
                router = defineRouterByRole(user, request);
            }
            case BLOCKED -> {
                router.setPagePath(PagePath.BLOCKED);
                router.setRouterType(RouterType.REDIRECT);
            }
            case UNCONFIRMED -> {
                router.setPagePath(PagePath.CONFIRMATION);
                router.setRouterType(RouterType.REDIRECT);
            }
            default -> throw new CommandException("User status is incorrect");
        }
        return router;
    }

    private Router defineRouterByRole(User user, HttpServletRequest request) throws ServiceException, CommandException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        UserRole userRole = user.getUserRole();
        Router router = new Router();
        switch (userRole) {
            case READER, LIBRARIAN -> {
                router.setPagePath(PagePath.USER);
                router.setRouterType(RouterType.REDIRECT);
            }
            case ADMIN -> {
                String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
                int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
                int recordsPerPage = Integer.parseInt(RequestParameter.RECORD_PAGE);
                List<User> users = userService.findAll();
                definePagination(request, users.size(), currentPage, recordsPerPage);
                int recordsView = (currentPage - 1) * recordsPerPage;
                users = users.subList(recordsView, Math.min(recordsView + recordsPerPage, users.size()));
                request.setAttribute(RequestParameter.ALL_USERS, users);
                router.setPagePath(PagePath.ADMIN);
                router.setRouterType(RouterType.FORWARD);
            }
            default -> throw new CommandException("User role is incorrect");
        }
        return router;
    }
}
