package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AdminPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        Object searchName = request.getSession().getAttribute(RequestParameter.SEARCH_USER);
        try {
            if (searchName == null) {
                List<User> userList = defineUserList(request);
                request.setAttribute(AttributeName.ALL_USERS, userList);
            } else {
                findUser(request, searchName);
            }
            router.setPagePath(PagePath.ADMIN);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in admin page", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private List<User> defineUserList(HttpServletRequest request) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        int countRecords = userService.findCount();
        int shownRecords = shownRecordsPagination(countRecords, request);
        return userService.findPartOfAll(shownRecords, RECORDS_PER_PAGE);
    }

    private void findUser(HttpServletRequest request, Object searchName) throws ServiceException {
        List<User> userList = defineSearchUserList((String) searchName, request);
        request.setAttribute(AttributeName.ALL_USERS, userList);
    }

    private List<User> defineSearchUserList(String searchName, HttpServletRequest request) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        int countRecords = userService.findCount(searchName);
        int shownRecords = shownRecordsPagination(countRecords, request);
        return userService.findByLogin(searchName, shownRecords, RECORDS_PER_PAGE);
    }
}