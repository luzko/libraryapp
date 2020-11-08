package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.AuthorService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The type represents the command for creating the author.
 */
public class CreateAuthorCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateAuthorCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        String authorName = request.getParameter(RequestParameter.AUTHOR_NAME);
        String locale = (String) request.getSession().getAttribute(AttributeName.LOCALE);
        AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
        try {
            if (authorService.add(authorName)) {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_AUTHOR_CORRECT, locale);
                request.getSession().setAttribute(AttributeName.CORRECT_DATA_MESSAGE, attributeValue);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_AUTHOR_DATA, locale);
                request.getSession().setAttribute(AttributeName.ERROR_DATA_MESSAGE, attributeValue);
            }
            request.getSession().setAttribute(AttributeName.CREATE_TYPE, createType);
            router.setPagePath(PagePath.CREATE_BOOK);
            router.setRedirect();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error create ", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
