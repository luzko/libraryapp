package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.admin.ChangeUserStatusCommand;
import com.luzko.libraryapp.controller.command.impl.admin.CreateAuthorCommand;
import com.luzko.libraryapp.controller.command.impl.admin.CreateBookCommand;
import com.luzko.libraryapp.controller.command.impl.admin.CreateLibrarianCommand;
import com.luzko.libraryapp.controller.command.impl.page.*;
import com.luzko.libraryapp.controller.command.impl.reader.CreateHomeOrderCommand;
import com.luzko.libraryapp.controller.command.impl.reader.CreateReadingRoomOrderCommand;
import com.luzko.libraryapp.controller.command.impl.user.*;

public enum CommandType {
    LOGIN_PAGE(new LoginPageCommand()),
    REGISTRATION_PAGE(new RegistrationPageCommand()),
    HOME_PAGE(new HomePageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    USER_PAGE(new UserPageCommand()),
    LIBRARY_PAGE(new LibraryPageCommand()),
    CREATE_BOOK_PAGE(new CreateBookPageCommand()),
    ORDERS_PAGE(new OrderPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand()),
    CREATE_LIBRARIAN_COMMAND(new CreateLibrarianCommand()),
    LOCALE(new ChangeLocaleCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CONFIRM(new ConfirmCommand()),
    SEND_MESSAGE_ADMIN(new SendMessageAdmin()),
    SETTINGS(new ProfileSettingPageCommand()),
    CHANGE_PROFILE_LOGIN(new ChangeProfileLoginCommand()),
    CHANGE_PROFILE_NAME(new ChangeProfileNameCommand()),
    CHANGE_PROFILE_SURNAME(new ChangeProfileSurnameCommand()),
    BOOK_OVERVIEW(new BookOverviewCommand()),
    CREATE_AUTHOR(new CreateAuthorCommand()),
    CREATE_BOOK(new CreateBookCommand()),
    HOME_ORDER(new CreateHomeOrderCommand()),
    READING_ROOM_COMMAND(new CreateReadingRoomOrderCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
