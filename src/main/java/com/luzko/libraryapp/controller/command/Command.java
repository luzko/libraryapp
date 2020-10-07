package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);

}
