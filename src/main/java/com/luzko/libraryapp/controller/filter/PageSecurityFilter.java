package com.luzko.libraryapp.controller.filter;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.entity.UserStatus;

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
 * The type represents the page security filter.
 */
@WebFilter(urlPatterns = {"/jsp/view/*"}, initParams = {
        @WebInitParam(name = "ERROR_STEP", value = "/jsp/error/error404.jsp")
})
public class PageSecurityFilter implements Filter {
    private static final String ERROR_STEP = "ERROR_STEP";
    private static final String DELIMITER_PATH = "/";
    private static final String EXTENSION_FILE = ".jsp";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter(ERROR_STEP);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String[] list = request.getRequestURI().split(DELIMITER_PATH);
        UserRole userRole = (UserRole) request.getSession().getAttribute(AttributeName.USER_ROLE);
        UserStatus userStatus = (UserStatus) request.getSession().getAttribute(AttributeName.USER_STATUS);
        String page = null;

        if (list[list.length - 1].contains(EXTENSION_FILE)) {
            page = list[list.length - 1];
        }

        boolean isErrorPageRedirect = (userRole == null && !AvailabilityPage.availableGustPageList.contains(page)) ||
                (UserRole.READER.equals(userRole) && !AvailabilityPage.availableReaderPageList.contains(page)) ||
                (UserRole.LIBRARIAN.equals(userRole) && !AvailabilityPage.availableLibrarianPageList.contains(page)) ||
                (UserRole.ADMIN.equals(userRole) && !AvailabilityPage.availableAdminPageList.contains(page));

        if (!isErrorPageRedirect && userStatus == UserStatus.BLOCKED) {
            isErrorPageRedirect = !AvailabilityPage.availableBlockedPageList.contains(page);
        }
        if (!isErrorPageRedirect && userStatus == UserStatus.UNCONFIRMED) {
            isErrorPageRedirect = !AvailabilityPage.availableUnconfirmedPageList.contains(page);
        }
        if (isErrorPageRedirect) {
            response.sendRedirect(request.getContextPath() + indexPath);
        }
        filterChain.doFilter(request, response);
    }
}
