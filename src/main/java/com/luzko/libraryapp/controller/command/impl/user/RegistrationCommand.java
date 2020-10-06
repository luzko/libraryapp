package com.luzko.libraryapp.controller.command.impl.user;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.entity.user.UserRole;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.service.user.impl.UserServiceImpl;
import com.luzko.libraryapp.util.ConfirmCodeGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class RegistrationCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegistrationCommand.class);
    private static final UserService service = UserServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        Map<String, String> registrationParameter = fillRegistrationParameter(request);
        Object role = request.getSession().getAttribute(RequestParameter.USER_ROLE);
        boolean isLibrarian = role == UserRole.ADMIN;
        try {
            if (service.isLoginUnique(registrationParameter.get(ColumnName.LOGIN))) {
                if (service.registration(registrationParameter, isLibrarian)) {
                    //TODO логика по отсылки письма с подтверждем.. Редирект на страницу подтверждения..

                    //TODO isLibrarian на админа
                    router.setPagePath(PagePath.HOME);
                    router.setRouterType(RouterType.REDIRECT);
                } else {
                    request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE, "Incorrect data");
                    request.setAttribute("registrationParameters", registrationParameter);
                    router.setPagePath(PagePath.REGISTRATION);
                    router.setRouterType(RouterType.FORWARD);
                }
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE, "This login is already in use in the system");
                request.setAttribute("registrationParameters", registrationParameter);
                router.setPagePath(PagePath.REGISTRATION);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
            //TODO error
        }
        return router;
    }

    private Map<String, String> fillRegistrationParameter(HttpServletRequest request) {
        Map<String, String> registrationParameters = new HashMap<>();
        registrationParameters.put(ColumnName.LOGIN, request.getParameter(ColumnName.LOGIN).trim());
        registrationParameters.put(ColumnName.PASSWORD, request.getParameter(ColumnName.PASSWORD).trim());
        registrationParameters.put(ColumnName.NAME, request.getParameter(ColumnName.NAME).trim());
        registrationParameters.put(ColumnName.SURNAME, request.getParameter(ColumnName.SURNAME).trim());
        registrationParameters.put(ColumnName.EMAIL, request.getParameter(ColumnName.EMAIL).trim());
        registrationParameters.put(ColumnName.CONFIRM_CODE, ConfirmCodeGenerator.getInstance().generate());
        return registrationParameters;
    }
}

//TODO logger..
//TODO add filter..