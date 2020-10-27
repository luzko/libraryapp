package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    int RECORDS_PER_PAGE = 5;

    Router execute(HttpServletRequest request);

    default int shownRecordsPagination(int countRecords, HttpServletRequest request) {
        String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
        definePagination(countRecords, currentPage, request);
        return (currentPage - 1) * RECORDS_PER_PAGE;
    }

    private void definePagination(int countRecords, int currentPage, HttpServletRequest request) {
        int countPage = (int) Math.ceil((double) countRecords / RECORDS_PER_PAGE);
        request.getSession().setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(RequestParameter.COUNT_PAGE, countPage);
    }
}
