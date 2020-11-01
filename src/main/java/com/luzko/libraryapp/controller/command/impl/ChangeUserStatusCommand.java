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
import java.util.List;

public class ChangeUserStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeUserStatusCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = request.getParameter(RequestParameter.LOGIN);
        String userStatus = request.getParameter(RequestParameter.USER_STATUS);
        try {
            boolean isChangeUserStatus = userService.isChangeUserStatus(login, userStatus);
            List<User> userList = defineUserList(userService, request);
            request.getSession().setAttribute(AttributeName.ALL_USERS, userList);
            router.setPagePath(PagePath.ADMIN);
            router.setRedirect();
            if (!isChangeUserStatus) {
                logger.log(Level.WARN, "User status is not change");
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_STATUS_CHANGES,
                        (String) request.getSession().getAttribute(AttributeName.LOCALE));
                request.getSession().setAttribute(AttributeName.ERROR_MESSAGE, attributeValue);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in change status", e);
            router.setPagePath(PagePath.ERROR);
        }
        return router;
    }

    private List<User> defineUserList(UserService userService, HttpServletRequest request) throws ServiceException {
        int countRecords = userService.findCount();
        int shownRecords = shownRecordsPagination(countRecords, request);
        return userService.findPartOfAll(shownRecords, RECORDS_PER_PAGE);
    }
}
