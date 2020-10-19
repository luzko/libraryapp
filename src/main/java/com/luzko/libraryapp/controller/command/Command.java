package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.router.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command action.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);

    /**
     * Define parameter pagination.
     *
     * @param request       the request
     * @param countRecords  the count records
     * @param currentPage   the current page
     * @param recordPerPage the record per page
     */
    default void definePagination(HttpServletRequest request, int countRecords, int currentPage, int recordPerPage) {
        int countPage = (int) Math.ceil((double) countRecords / recordPerPage);
        request.getSession().setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(RequestParameter.COUNT_PAGE, countPage);
    }
}
