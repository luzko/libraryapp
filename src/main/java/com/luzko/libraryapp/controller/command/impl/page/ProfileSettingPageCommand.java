package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ProfileSettingPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        request.getSession().setAttribute(AttributeName.TYPE_PROFILE_PAGE, AttributeValue.CHANGE_PROFILE_PAGE);
        router.setPagePath(PagePath.USER);
        return router;
    }
}
