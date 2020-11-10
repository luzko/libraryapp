package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.AttributeName;
import com.luzko.libraryapp.controller.AttributeValue;
import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.Command;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The type represents the command to change the user's profile image.
 */
public class ChangeProfileImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileImageCommand.class);
    private static final String UPLOAD_DIRECTORY = "C:\\upload";
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png))$)";
    private static final String DOT_PATTERN = ".";
    private static final String DOT_SHIELD_PATTERN = "\\.";

    @Override
    public Router execute(HttpServletRequest request) {
        removeTempAttribute(request);
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(AttributeName.LOGIN);
        try {
            String fileName = defineFileName(request);
            if (userService.isChangeProfileImage(login, fileName)) {
                request.getSession().setAttribute(AttributeName.AVATAR, fileName);
            } else {
                String attributeValue = ConfigurationManager.getMessageProperty(AttributeValue.PATH_AVATAR_CHANGES,
                        (String) request.getSession().getAttribute(AttributeName.LOCALE));
                request.getSession().setAttribute(AttributeName.AVATAR_ERROR, attributeValue);
            }
            router.setPagePath(PagePath.USER);
            router.setRedirect();
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in change profile image", e);
            router.setPagePath(PagePath.ERROR);
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
