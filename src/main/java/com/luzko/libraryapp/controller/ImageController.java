package com.luzko.libraryapp.controller;

import com.luzko.libraryapp.controller.command.ActionProvider;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.model.validator.ValueValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 * The type represents the images controller.
 */
@WebServlet(urlPatterns = {"/images/*"}, loadOnStartup = 2)
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImageController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:\\upload";
    private static final int INDEX = 1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = ActionProvider.defineCommand(commandName);
        Router router = new Router();
        if (commandOptional.isPresent()) {
            router = commandOptional.get().execute(request);
        } else {
            String filename = request.getPathInfo();
            if (ValueValidator.isValidValue(filename.substring(INDEX))) {
                defineImagePath(filename, response);
            } else {
                router = new Router(PagePath.ERROR);
            }
        }
        if (router.getRouteType().equals(Router.RouteType.FORWARD)) {
            request.getRequestDispatcher(router.getPagePath()).forward(request, response);
        } else if (router.getRouteType().equals(Router.RouteType.REDIRECT)) {
            response.sendRedirect(router.getPagePath());
        }
    }

    private void defineImagePath(String filename, HttpServletResponse response) throws IOException {
        File file = new File(UPLOAD_DIRECTORY, filename);
        response.setHeader(AttributeName.CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(AttributeName.CONTENT_DISPOSITION, String.format(AttributeName.CONTENT_VALUE, filename));
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
