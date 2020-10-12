package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.Author;
import com.luzko.libraryapp.model.entity.Category;
import com.luzko.libraryapp.service.AuthorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class CreateBookPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateBookPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String createType = request.getParameter(RequestParameter.CREATE_TYPE);
        try {
            if (createType.equals(RequestParameter.BOOK)) {
                AuthorService authorService = ServiceFactory.getInstance().getAuthorService();
                List<Author> authors = authorService.findAll();
                request.getSession().setAttribute(RequestParameter.ALL_AUTHORS, authors);
                List<Category> categories = Arrays.asList(Category.values());
                request.getSession().setAttribute(RequestParameter.ALL_CATEGORIES, categories);
            }
            request.setAttribute(RequestParameter.CREATE_TYPE, createType);
            router.setPagePath(PagePath.CREATE_BOOK);
            router.setRouterType(RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in create book page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }
}
