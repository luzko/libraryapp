package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.admin.ChangeUserStatus;
import com.luzko.libraryapp.controller.command.impl.page.HomePage;
import com.luzko.libraryapp.controller.command.impl.page.LoginPage;
import com.luzko.libraryapp.controller.command.impl.page.RegistrationPage;
import com.luzko.libraryapp.controller.command.impl.user.LocaleCommand;
import com.luzko.libraryapp.controller.command.impl.user.RegistrationCommand;
import com.luzko.libraryapp.controller.command.impl.user.LoginCommand;
import com.luzko.libraryapp.controller.command.impl.user.LogoutCommand;

public enum CommandType {
    LOGIN_PAGE(new LoginPage()),
    REGISTRATION_PAGE(new RegistrationPage()),
    HOME_PAGE(new HomePage()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOCALE(new LocaleCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatus());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
