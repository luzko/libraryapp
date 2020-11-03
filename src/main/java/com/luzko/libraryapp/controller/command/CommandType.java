package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.PagePath;
import com.luzko.libraryapp.controller.Router;
import com.luzko.libraryapp.controller.command.impl.*;
import com.luzko.libraryapp.controller.command.impl.page.*;


public enum CommandType {
    LOGIN_PAGE(request -> new Router(PagePath.LOGIN)),
    REGISTRATION_PAGE(request -> new Router(PagePath.REGISTRATION)),
    HOME_PAGE(new HomePageCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    USER_PAGE(new UserPageCommand()),
    LIBRARY_PAGE(new LibraryPageCommand()),
    CREATE_BOOK_PAGE(new CreateBookPageCommand()),
    ORDERS_PAGE(new OrderPageCommand()),
    LOGIN(request -> new Router(PagePath.LOGIN)),
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
    DENY_ORDER(new DenyOrderCommand()),
    REMOVE_BOOK(new RemoveBookCommand()),
    FIND_BOOK(new FindBookCommand()),
    FIND_USER(new FindUserCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
