package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BrowseLoginCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        //return PagePath.LOGIN;
        return null;
    }
}
