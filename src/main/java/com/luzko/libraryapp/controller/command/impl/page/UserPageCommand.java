package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.model.entity.UserStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        UserStatus userStatus = (UserStatus) request.getSession().getAttribute(RequestParameter.USER_STATUS);

        switch (userStatus) {
            case ACTIVE -> {
                router.setPagePath(PagePath.USER);
                router.setRouterType(RouterType.FORWARD);
            }
            case BLOCKED -> {
                router.setPagePath(PagePath.BLOCKED);
                router.setRouterType(RouterType.FORWARD);
            }
            case UNCONFIRMED -> {
                router.setPagePath(PagePath.CONFIRMATION);
                router.setRouterType(RouterType.FORWARD);
            }
            default -> {
                //TODO error..
            }
        }
        return router;
    }
}
