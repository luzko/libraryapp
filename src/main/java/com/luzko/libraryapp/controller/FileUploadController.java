package com.luzko.libraryapp.controller;

import com.luzko.libraryapp.connection.ConnectionPool;
import com.luzko.libraryapp.controller.command.ActionProvider;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.controller.router.Router;
import com.luzko.libraryapp.controller.router.RouterType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet("/upload")
public class FileUploadController extends HttpServlet {
    //TODO ???
    @Override
    public void init() throws ServletException {
        super.init();
        //ConnectionPool.getInstance().init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    //TODO добавить объект, что не гонять request вне контроллера..

    //TODO ???
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = ActionProvider.defineCommand(commandName);
        if(commandOptional.isPresent()) {
            Router router = commandOptional.get().execute(request, response);
            if (router.getRouterType().equals(RouterType.FORWARD)) {
                request.getRequestDispatcher(router.getPagePath()).forward(request, response);
            } else {
                response.sendRedirect(router.getPagePath());
            }
        } else {
            //TODO данной команды не существет..
            request.getRequestDispatcher(PagePath.ERROR).forward(request, response);
        }
    }

    //TODO ???
    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }
}
