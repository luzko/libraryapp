package com.luzko.libraryapp.controller.command.impl.page;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.entity.User;
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
        try {
            List<User> userList = defineUserList(request);
            request.setAttribute(RequestParameter.ALL_USERS, userList);
            router.setPagePath(PagePath.ADMIN);
            router.setRouterType(RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Error in admin page", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private List<User> defineUserList(HttpServletRequest request) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        String currentPageString = request.getParameter(RequestParameter.CURRENT_PAGE);
        int currentPage = currentPageString != null ? Integer.parseInt(currentPageString) : 1;
        int recordsPerPage = Integer.parseInt(RequestParameter.RECORD_PAGE);
        List<User> userList = userService.findAll();
        definePagination(request, userList.size(), currentPage, recordsPerPage);
        int recordsView = (currentPage - 1) * recordsPerPage;
        return userList.subList(recordsView, Math.min(recordsView + recordsPerPage, userList.size()));
    }
}