package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileSettingPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        request.getSession().setAttribute(RequestParameter.TYPE_PROFILE_PAGE, RequestParameter.CHANGE_PROFILE_PAGE);
        router.setPagePath(PagePath.USER);
        router.setRouterType(RouterType.FORWARD);
        return router;
    }
}
