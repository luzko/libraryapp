package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.util.ConfigurationManager;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.CodeGenerator;

import com.luzko.libraryapp.util.mail.EmailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String, String> registrationParameter = fillRegistrationParameter(request);
        Object role = request.getSession().getAttribute(RequestParameter.USER_ROLE);
        boolean isLibrarian = role == UserRole.ADMIN;
        try {
            if (userService.isLoginUnique(registrationParameter.get(ColumnName.LOGIN))) {
                if (userService.registration(registrationParameter, isLibrarian)) {
                    EmailSender.sendMessageConfirm(
                            registrationParameter.get(ColumnName.EMAIL), registrationParameter.get(ColumnName.CONFIRM_CODE)
                    );
                    router.setPagePath(isLibrarian ? PagePath.ADMIN : PagePath.LOGIN);
                    router.setRedirect();
                } else {
                    request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_INCORRECT_DATA));
                    request.setAttribute(RequestParameter.REGISTRATION_PARAMETER, registrationParameter);
                    router.setPagePath(PagePath.REGISTRATION);
                }
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_LOGIN_EXIST));
                request.setAttribute(RequestParameter.REGISTRATION_PARAMETER, registrationParameter);
                router.setPagePath(PagePath.REGISTRATION);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Registration command error", e);
            router.setPagePath(PagePath.ERROR);
            router.setRedirect();
        }
        return router;
    }

    private Map<String, String> fillRegistrationParameter(HttpServletRequest request) {
        Map<String, String> registrationParameter = new HashMap<>();
        registrationParameter.put(ColumnName.LOGIN, request.getParameter(ColumnName.LOGIN).trim());
        registrationParameter.put(ColumnName.PASSWORD, request.getParameter(ColumnName.PASSWORD).trim());
        registrationParameter.put(ColumnName.NAME, request.getParameter(ColumnName.NAME).trim());
        registrationParameter.put(ColumnName.SURNAME, request.getParameter(ColumnName.SURNAME).trim());
        registrationParameter.put(ColumnName.EMAIL, request.getParameter(ColumnName.EMAIL).trim());
        registrationParameter.put(ColumnName.CONFIRM_CODE, CodeGenerator.generate());
        return registrationParameter;
    }
}