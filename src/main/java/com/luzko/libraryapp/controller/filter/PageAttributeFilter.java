package com.luzko.libraryapp.controller.filter;

import com.luzko.libraryapp.controller.AttributeName;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Page attribute filter.
 */
@WebFilter(urlPatterns = {"/jsp/view/*"}, initParams = {
        @WebInitParam(name = "ERROR_STEP", value = "/jsp/error/errorstep.jsp")
})
public class PageAttributeFilter implements Filter {
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("ERROR_STEP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        if (requestURI.contains(AvailabilityPage.BOOK_OVERVIEW)) {
            Object book = request.getSession().getAttribute(AttributeName.BOOK);
            if (book == null) {
                response.sendRedirect(request.getContextPath() + indexPath);
            }
        } else if (requestURI.contains(AvailabilityPage.ORDERS)) {
            Object orderType = request.getSession().getAttribute(AttributeName.ORDER_TYPE);
            if (orderType == null) {
                response.sendRedirect(request.getContextPath() + indexPath);
            }
        }
        filterChain.doFilter(request, response);
    }
}
