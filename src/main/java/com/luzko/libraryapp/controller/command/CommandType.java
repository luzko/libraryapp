package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.*;

public enum CommandType {
    BROWSE_LOGIN(new BrowseLoginCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    BROWSE_REGISTRATION(new BrowseRegistration());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
