package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.util.PathManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.KEY_PAGE_INDEX);
        request.getSession().invalidate();
        return page;
    }
}
