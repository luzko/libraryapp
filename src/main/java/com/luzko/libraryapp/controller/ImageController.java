package com.luzko.libraryapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet("/images/*")
public class ImageController extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:\\upload";
    private static final int INDEX = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getPathInfo().substring(INDEX);
        File file = new File(UPLOAD_DIRECTORY, filename);
        response.setHeader(RequestParameter.CONTENT_TYPE, getServletContext().getMimeType(filename));
        response.setHeader(RequestParameter.CONTENT_DISPOSITION, String.format(RequestParameter.CONTENT_VALUE, filename));
        Files.copy(file.toPath(), response.getOutputStream());
    }
}
