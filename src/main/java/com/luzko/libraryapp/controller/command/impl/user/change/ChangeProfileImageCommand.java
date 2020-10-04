package com.luzko.libraryapp.controller.command.impl.user.change;

import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class ChangeProfileImageCommand implements Command {
    public static final String UPLOAD_DIR = "profile_image";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        String applicationDir = request.getServletContext().getContextPath();
        String applicationDir1 = request.getServletContext().getRealPath("/");

        System.out.println(applicationDir);
        System.out.println(applicationDir1);

        String uploadFileDir = applicationDir + UPLOAD_DIR + File.separator;

        System.out.println(uploadFileDir);

        return null;
    }
}
