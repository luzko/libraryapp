package com.luzko.libraryapp.filter;

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
        UserRole userRole = (UserRole) request.getSession().getAttribute("userRole");

        String page = null;
        if (list[list.length - 1].contains(EXTENSION_FILE)) {
            page = list[list.length - 1];
        }

        boolean isErrorPageRedirect = (userRole == null && !AvailabilityPage.availableGustPage.contains(page)) ||
                (UserRole.READER.equals(userRole) && !AvailabilityPage.availableReaderPage.contains(page)) ||
                (UserRole.LIBRARIAN.equals(userRole) && !AvailabilityPage.availableLibrarianPage.contains(page)) ||
                (UserRole.ADMIN.equals(userRole) && !AvailabilityPage.availableAdminPage.contains(page));

        if (isErrorPageRedirect) {
            response.sendRedirect(request.getContextPath() + indexPath);
        }
        filterChain.doFilter(request, response);
    }
}
