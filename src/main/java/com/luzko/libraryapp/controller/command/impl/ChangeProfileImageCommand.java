package com.luzko.libraryapp.controller.command.impl;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.RequestParameter;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;
import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.util.CodeGenerator;
import com.luzko.libraryapp.util.ConfigurationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ChangeProfileImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeProfileImageCommand.class);
    private static final String UPLOAD_DIRECTORY = "C:\\upload";
    private static final String DOT_PATTERN = ".";
    private static final String DOT_SHIELD_PATTERN = "\\.";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        UserService userService = ServiceFactory.getInstance().getUserService();
        String login = (String) request.getSession().getAttribute(RequestParameter.LOGIN);
        try {
            String fileName = uploadFile(request);
            if (fileName != null && !fileName.isBlank()) {
                //if (userService.changeProfileImage(login, fileName)) {
                //TODO
                    /*request.getSession().setAttribute(RequestParameter.CHANGE_SAVED,
                        ConfigurationManager.getMessageProperty(RequestParameter.PATH_SAVE_CHANGES));
                request.getSession().setAttribute(RequestParameter.NAME_ERROR, RequestParameter.EMPTY);
                request.getSession().setAttribute(RequestParameter.USER_NAME, newName);*/
                //TODO запись нового пути аватарки в сессию.
                //} else {
                //TODO
                    /*request.getSession().setAttribute(RequestParameter.CHANGE_SAVED, RequestParameter.EMPTY);
                    request.getSession().setAttribute(RequestParameter.LOGIN_ERROR,
                            ConfigurationManager.getMessageProperty(RequestParameter.PATH_LOGIN_CHANGES));*/
                //}
                router.setPagePath(PagePath.USER);
                router.setRouterType(RouterType.REDIRECT);
            }
        } catch (Exception e) {
            logger.log(Level.ERROR, "Error in change profile image", e);
            router.setPagePath(PagePath.ERROR);
            router.setRouterType(RouterType.FORWARD);
        }
        return router;
    }

    private String uploadFile(HttpServletRequest request) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        File uploadDir = new File(UPLOAD_DIRECTORY);
        String fileName = null;

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        List<FileItem> formItems = upload.parseRequest(request);
        if (formItems != null && !formItems.isEmpty()) {
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    List<String> partName = Arrays.asList(item.getName().split(DOT_SHIELD_PATTERN));
                    String extension = DOT_PATTERN + partName.get(partName.size() - 1);
                    fileName = CodeGenerator.generate();
                    String filePath = UPLOAD_DIRECTORY + File.separator + fileName + extension;
                    File storeFile = new File(filePath);
                    item.write(storeFile);
                }
            }
        }
        return fileName;
    }
}
