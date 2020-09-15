package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        //TODO передавать

        String login = request.getParameter(ColumnName.LOGIN).trim();
        String password = request.getParameter(ColumnName.PASSWORD);
        String name = request.getParameter(ColumnName.NAME);
        String surname = request.getParameter(ColumnName.SURNAME);
        String email = request.getParameter(ColumnName.EMAIL);

        try {
            if (service.registration(login, password, name, surname, email)) {
                router.setPagePath(PagePath.HOME);
                router.setRouterType(RouterType.REDIRECT);
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE, "Incorrect data");
                router.setPagePath(PagePath.REGISTRATION);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            //TODO error
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
        }
        return router;
    }
}
