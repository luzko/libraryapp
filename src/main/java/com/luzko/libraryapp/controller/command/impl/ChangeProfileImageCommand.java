package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.service.UserService;
import com.luzko.libraryapp.util.CodeGenerator;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ChangeProfileImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileImageCommand.class);
    private static final String UPLOAD_DIRECTORY = "C:\\upload";
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png))$)";
    private static final String DOT_PATTERN = ".";
    private static final String DOT_SHIELD_PATTERN = "\\.";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        try {
            String fileName = defineFileName(request);
            if (userService.isChangeProfileImage(login, fileName)) {
                request.getSession().setAttribute(RequestParameter.AVATAR, fileName);
                request.getSession().setAttribute(RequestParameter.AVATAR_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.EMPTY));
            } else {
                request.getSession().setAttribute(RequestParameter.AVATAR_ERROR,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_AVATAR_CHANGES));
            }
            router.setPagePath(PagePath.USER);
            router.setRouterType(RouterType.REDIRECT);
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in change profile image", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private String defineFileName(HttpServletRequest request) {
        Collection<Part> uploadParts = null;
        try {
            uploadParts = request.getParts();
        } catch (IOException | ServletException e) {
            logger.log(Level.ERROR, "Parts doesn't exist", e);
        }
        Path path = Paths.get(UPLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                logger.log(Level.ERROR, "Directory doesn't create", e);
            }
        }
        return uploadFile(uploadParts);
    }

    private String uploadFile(Collection<Part> uploadParts) {
        String photoName = null;
        if (uploadParts != null && !uploadParts.isEmpty()) {
            for (Part part : uploadParts) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null && fileName.matches(IMAGE_PATTERN)) {
                    List<String> partName = Arrays.asList(fileName.split(DOT_SHIELD_PATTERN));
                    String extension = DOT_PATTERN + partName.get(partName.size() - 1);
                    try {
                        photoName = CodeGenerator.generate() + extension;
                        part.write(UPLOAD_DIRECTORY + File.separator + photoName);
                    } catch (IOException e) {
                        logger.log(Level.ERROR, "Error write file", e);
                    }
                }
            }
        }
        return photoName;
    }
}
