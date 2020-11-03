package com.luzko.libraryapp.controller;

import com.luzko.libraryapp.controller.command.ActionProvider;
import com.luzko.libraryapp.controller.command.Command;
import com.luzko.libraryapp.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * The type Front controller.
 */
@WebServlet(urlPatterns = {"/controller/*"}, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ConnectionPool.getInstance().init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND_NAME);
        Optional<Command> commandOptional = ActionProvider.defineCommand(commandName);
        Router router;
        if (commandOptional.isPresent()) {
            router = commandOptional.get().execute(request);
        } else {
            router = new Router(PagePath.ERROR);
        }
        if (router.getRouteType().equals(Router.RouteType.FORWARD)) {
            request.getRequestDispatcher(router.getPagePath()).forward(request, response);
        } else {
            response.sendRedirect(router.getPagePath());
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
        super.destroy();
    }
}