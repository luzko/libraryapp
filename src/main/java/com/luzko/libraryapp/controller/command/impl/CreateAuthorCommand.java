package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.service.AuthorService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CreateAuthorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateAuthorCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        String authorName = request.getParameter(RequestParameter.AUTHOR_NAME);
        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        try {
            if (authorService.add(authorName)) {
                request.getSession().setAttribute(RequestParameter.CORRECT_DATA_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_AUTHOR_CORRECT));
                request.getSession().setAttribute(RequestParameter.ERROR_DATA_MESSAGE, RequestParameter.EMPTY);
            } else {
                request.getSession().setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_AUTHOR_DATA));
                request.getSession().setAttribute(RequestParameter.CORRECT_DATA_MESSAGE, RequestParameter.EMPTY);
            }
            request.getSession().setAttribute(RequestParameter.CREATE_TYPE, createType);
            router.setPagePath(PagePath.CREATE_BOOK);
            router.setRouterType(RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error create ", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
