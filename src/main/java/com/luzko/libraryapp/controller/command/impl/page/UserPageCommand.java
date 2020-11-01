package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.model.entity.UserStatus;

import javax.servlet.http.HttpServletRequest;

public class UserPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserStatus userStatus = (UserStatus) request.getSession().getAttribute(RequestParameter.USER_STATUS);
        request.getSession().setAttribute(AttributeName.TYPE_PROFILE_PAGE, AttributeValue.SEE_PROFILE_PAGE);
        switch (userStatus) {
            case ACTIVE -> router.setPagePath(PagePath.USER);
            case BLOCKED -> router.setPagePath(PagePath.BLOCKED);
            case UNCONFIRMED -> router.setPagePath(PagePath.CONFIRMATION);
            default -> router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
