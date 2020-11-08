package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * The interface represents command.
 */
@FunctionalInterface
public interface Command {
    /**
     * A constant that determines the number of records per page.
     */
    int RECORDS_PER_PAGE = 5;

    /**
     * Execute command.
     *
     * @param request the request
     * @return the router containing router type and page path
     */
    Router execute(HttpServletRequest request);

    /**
     * Remove temporary attributes from the session.
     *
     * @param request the request
     */
    default void removeTempAttribute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            if (AttributeName.tempAttributeList.contains(attributeName)) {
                session.removeAttribute(attributeName);
            }
        }
    }

    /**
     * Determination of the number of displayed records during pagination.
     *
     * @param countRecords record counter
     * @param request      the request
     * @return number of records shown
     */
    default int shownRecordsPagination(int countRecords, HttpServletRequest request) {
        String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
        definePagination(countRecords, currentPage, request);
        return (currentPage - 1) * RECORDS_PER_PAGE;
    }

    private void definePagination(int countRecords, int currentPage, HttpServletRequest request) {
        int countPage = (int) Math.ceil((double) countRecords / RECORDS_PER_PAGE);
        request.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(AttributeName.COUNT_PAGE, countPage);
    }
}
