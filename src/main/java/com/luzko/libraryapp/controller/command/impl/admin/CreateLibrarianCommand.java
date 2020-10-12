package com.luzko.libraryapp.controller.command.impl.admin;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.util.ConfigurationManager;
import com.luzko.libraryapp.util.ConfirmCodeGenerator;
import com.luzko.libraryapp.util.mail.EmailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CreateLibrarianCommand implements Command {
    private static final Logger logger = LogManager.getLogger(CreateLibrarianCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String, String> registrationParameter = fillRegistrationParameter(request);

        try {
            if (userService.isLoginUnique(registrationParameter.get(ColumnName.LOGIN))) {
                if (userService.registration(registrationParameter, true)) {
                    EmailSender.sendMessageConfirm(
                            registrationParameter.get(ColumnName.EMAIL), registrationParameter.get(ColumnName.CONFIRM_CODE)
                    );
                    router.setPagePath(PagePath.ADMIN);
                    router.setRouterType(RouterType.REDIRECT);
                } else {
                    request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_INCORRECT_DATA));
                    request.setAttribute(RequestParameter.REGISTRATION_PARAMETER, registrationParameter);
                    router.setPagePath(PagePath.REGISTRATION);
                    router.setRouterType(RouterType.FORWARD);
                }
            } else {
                request.setAttribute(RequestParameter.ERROR_DATA_MESSAGE,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_LOGIN_EXIST));
                request.setAttribute(RequestParameter.REGISTRATION_PARAMETER, registrationParameter);
                router.setPagePath(PagePath.REGISTRATION);
                router.setRouterType(RouterType.FORWARD);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Registration command error", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.REDIRECT);
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
        registrationParameter.put(ColumnName.CONFIRM_CODE, ConfirmCodeGenerator.generate());
        return registrationParameter;
    }
}
