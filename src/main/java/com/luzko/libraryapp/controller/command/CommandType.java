package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.LoginCommand;
import com.luzko.libraryapp.controller.command.impl.LogoutCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

}
