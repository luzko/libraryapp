package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Author;
import com.luzko.libraryapp.model.entity.Category;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.AuthorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * The type represents the command for the book creation page.
 */
public class CreateBookPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateBookPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        removeTempAttribute(request);
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        try {
            if (createType.equals(AttributeName.BOOK)) {
                AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
                List<Author> authorList = authorService.findAll();
                request.getSession().setAttribute(AttributeName.ALL_AUTHORS, authorList);
                List<Category> categoryList = Arrays.asList(Category.values());
                request.getSession().setAttribute(AttributeName.ALL_CATEGORIES, categoryList);
            }
            request.setAttribute(RequestParameter.CREATE_TYPE, createType);
            router.setPagePath(PagePath.CREATE_BOOK);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in create book page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }
}
