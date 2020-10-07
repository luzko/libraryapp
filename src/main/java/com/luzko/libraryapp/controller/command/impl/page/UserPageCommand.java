package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.model.entity.user.UserStatus;

import javax.servlet.http.HttpServletRequest;

public class UserPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserStatus userStatus = (UserStatus) request.getSession().getAttribute(RequestParameter.USER_STATUS);
        request.getSession().setAttribute(RequestParameter.TYPE_PROFILE_PAGE, RequestParameter.SEE_PROFILE_PAGE);
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
                router.setPagePath(PagePath.ERROR);
                router.setRouterType(RouterType.REDIRECT);
            }
        }
        return router;
    }
}
