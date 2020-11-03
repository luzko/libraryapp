package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class FindUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger(FindUserCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String searchName = request.getParameter(RequestParameter.SEARCH_USER);
        try {
            if(searchName.isBlank()) {
                request.getSession().removeAttribute(AttributeName.SEARCH_USER);
                List<User> userList = defineUserList(request);
                request.setAttribute(AttributeName.ALL_USERS, userList);
            } else {
                findUser(request, searchName);
            }
            router.setPagePath(PagePath.ADMIN);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in find user", e);
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

    private void findUser(HttpServletRequest request, String  searchName) throws ServiceException {
        request.getSession().setAttribute(AttributeName.SEARCH_USER, searchName);
        List<User> userList = defineSearchUserList(searchName, request);
        if(userList.isEmpty()) {
            String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_USER_NOT_FOUND,
                    (String) request.getSession().getAttribute(AttributeName.LOCALE));
            request.setAttribute(AttributeName.NOT_FOUND_USERS, attributeValue);
        } else {
            request.setAttribute(AttributeName.ALL_USERS, userList);
        }
    }

    private List<User> defineSearchUserList(String searchName, HttpServletRequest request) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> userList = Collections.emptyList();
        int countRecords = userService.findCount(searchName);
        if (countRecords > 0) {
            int shownRecords = shownRecordsPagination(countRecords, request);
            userList = userService.findByLogin(searchName, shownRecords, RECORDS_PER_PAGE);
        }
        return userList;
    }
}
