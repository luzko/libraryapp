package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.user.UserStatus;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.service.user.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        //UserRole userRole = (UserRole) request.getSession().getAttribute(RequestParameter.USER_ROLE);
        String codeConfirm = request.getParameter(RequestParameter.CODE);
        UserService userService = UserServiceImpl.getInstance();

        try {
            if (userService.isCodeConfirmCorrect(login, codeConfirm)) {
                request.getSession().setAttribute(RequestParameter.USER_STATUS, UserStatus.ACTIVE);
                //if (userRole.equals(UserRole.LIBRARIAN)) {
                    router.setPagePath(PagePath.USER);
                    router.setRouterType(RouterType.REDIRECT);
                //} else if (userRole.equals(UserRole.READER)) {
                //    router.setPagePath(PagePath.USER);
                //    router.setRouterType(RouterType.REDIRECT);
                //}
            } else {
                //TODO повторная отсылка пароля сразу же??
                //TODO запрос на отслыку нового сообщения этому юзеру..
                request.getSession().setAttribute(RequestParameter.PARAM_CONFIRM_ERROR, "message"); //TODO
                router.setRouterType(RouterType.REDIRECT);
                router.setPagePath(PagePath.CONFIRMATION);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
            //TODO error
        }
        return router;
    }
}
