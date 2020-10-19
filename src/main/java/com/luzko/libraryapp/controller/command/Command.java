package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request);

    default void definePagination(HttpServletRequest request, int countRecords, int currentPage, int recordPerPage) {
        int countPage = (int) Math.ceil((double) countRecords / recordPerPage);
        request.getSession().setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(RequestParameter.COUNT_PAGE, countPage);
    }
}
