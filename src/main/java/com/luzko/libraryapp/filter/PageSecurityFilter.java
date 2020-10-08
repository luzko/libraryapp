package com.luzko.libraryapp.filter;

import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.model.entity.user.UserRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/view/*"}, initParams = {
        @WebInitParam(name = "ERROR_STEP", value = "/jsp/error/errorstep.jsp")
})

public class PageSecurityFilter implements Filter {

    private static final String DELIMITER_PATH = "/";
    private static final String EXTENSION_FILE = ".jsp";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("ERROR_STEP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String[] list = request.getRequestURI().split(DELIMITER_PATH);
        String userRole = (String) request.getSession().getAttribute(RequestParameter.USER_ROLE);

        String page = null;
        if (list[list.length - 1].contains(EXTENSION_FILE)) {
            page = list[list.length - 1];
        }

        if (userRole == null) {
            userRole = "";
        }

        boolean isErrorPageRedirect = (userRole.isEmpty() && !AvailabilityPage.availableGustPage.contains(page)) ||
                (userRole.equals(UserRole.READER.toString()) && !AvailabilityPage.availableReaderPage.contains(page)) ||
                (userRole.equals(UserRole.LIBRARIAN.toString()) && !AvailabilityPage.availableLibrarianPage.contains(page)) ||
                (userRole.equals(UserRole.ADMIN.toString()) && !AvailabilityPage.availableAdminPage.contains(page));

        if (isErrorPageRedirect) {
            response.sendRedirect(request.getContextPath() + indexPath);
        }
        filterChain.doFilter(request, response);
    }
}
