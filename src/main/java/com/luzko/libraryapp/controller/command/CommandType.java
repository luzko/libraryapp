package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.admin.ChangeUserStatusCommand;
import com.luzko.libraryapp.controller.command.impl.page.*;
import com.luzko.libraryapp.controller.command.impl.user.*;

public enum CommandType {
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    HOME_PAGE(new HomePageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    USER_PAGE(new UserPageCommand()),
    LIBRARY_PAGE(new LibraryPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOCALE(new ChangeLocaleCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CONFIRM(new ConfirmCommand()),
    SEND_MESSAGE_ADMIN(new SendMessageAdmin());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
