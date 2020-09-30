package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.admin.ChangeUserStatusCommand;
import com.luzko.libraryapp.controller.command.impl.page.*;
import com.luzko.libraryapp.controller.command.impl.user.ChangeLocaleCommand;
import com.luzko.libraryapp.controller.command.impl.user.RegistrationCommand;
import com.luzko.libraryapp.controller.command.impl.user.LoginCommand;
import com.luzko.libraryapp.controller.command.impl.user.LogoutCommand;

public enum CommandType {
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    HOME_PAGE(new HomePageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    LIBRARIAN_PAGE(new LibrarianPageCommand()),
    READER_PAGE(new ReaderPageCommand()),
    LIBRARY_PAGE(new LibraryPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOCALE(new ChangeLocaleCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
