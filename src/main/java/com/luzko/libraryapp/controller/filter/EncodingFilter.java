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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Encoding filter.
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Parameter"),
        @WebInitParam(name = "locale", value = "en_US", description = "Locale Parameter")
})
public class EncodingFilter implements Filter {
    private String code;
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(AttributeName.ENCODING);
        locale = filterConfig.getInitParameter(AttributeName.LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String sessionLocale = (String) session.getAttribute(AttributeName.LOCALE);
        if (sessionLocale == null) {
            session.setAttribute(AttributeName.LOCALE, locale);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}