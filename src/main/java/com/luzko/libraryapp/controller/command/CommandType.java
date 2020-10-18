package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.*;
import com.luzko.libraryapp.controller.command.impl.page.*;

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
    LOCALE(new ChangeLocaleCommand()),
    CHANGE_USER_STATUS(new ChangeUserStatusCommand()),
    CONFIRM(new ConfirmCommand()),
    SEND_MESSAGE_ADMIN(new SendMessageAdmin()),
    SETTINGS(new ProfileSettingPageCommand()),
    CHANGE_PROFILE_LOGIN(new ChangeProfileLoginCommand()),
    CHANGE_PROFILE_NAME(new ChangeProfileNameCommand()),
    CHANGE_PROFILE_SURNAME(new ChangeProfileSurnameCommand()),
    CHANGE_PROFILE_IMAGE(new ChangeProfileImageCommand()),
    BOOK_OVERVIEW(new BookOverviewCommand()),
    CREATE_AUTHOR(new CreateAuthorCommand()),
    CREATE_BOOK(new CreateBookCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    RETURN_ORDER(new ReturnOrderCommand()),
    APPROVE_ORDER(new ApproveOrderCommand()),
    DENY_ORDER(new DenyOrderCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
