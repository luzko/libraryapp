package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.page.LoginPage;
import com.luzko.libraryapp.controller.command.impl.user.RegistrationCommand;
import com.luzko.libraryapp.controller.command.impl.user.LoginCommand;
import com.luzko.libraryapp.controller.command.impl.user.LogoutCommand;

public enum CommandType {
    LOGIN_PAGE(new LoginPage()),
    REGISTRATION_PAGE(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
