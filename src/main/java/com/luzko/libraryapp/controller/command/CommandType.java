package com.luzko.libraryapp.controller.command;

import com.luzko.libraryapp.controller.command.impl.ApproveOrderCommand;
import com.luzko.libraryapp.controller.command.impl.BookOverviewCommand;
import com.luzko.libraryapp.controller.command.impl.CancelOrderCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeLocaleCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeProfileImageCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeProfileLoginCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeProfileNameCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeProfileSurnameCommand;
import com.luzko.libraryapp.controller.command.impl.ChangeUserStatusCommand;
import com.luzko.libraryapp.controller.command.impl.ConfirmCommand;
import com.luzko.libraryapp.controller.command.impl.CreateAuthorCommand;
import com.luzko.libraryapp.controller.command.impl.CreateBookCommand;
import com.luzko.libraryapp.controller.command.impl.CreateOrderCommand;
import com.luzko.libraryapp.controller.command.impl.DenyOrderCommand;
import com.luzko.libraryapp.controller.command.impl.FindBookCommand;
import com.luzko.libraryapp.controller.command.impl.FindUserCommand;
import com.luzko.libraryapp.controller.command.impl.LoginCommand;
import com.luzko.libraryapp.controller.command.impl.LogoutCommand;
import com.luzko.libraryapp.controller.command.impl.RegistrationCommand;
import com.luzko.libraryapp.controller.command.impl.RemoveBookCommand;
import com.luzko.libraryapp.controller.command.impl.ReturnOrderCommand;
import com.luzko.libraryapp.controller.command.impl.SendMessageAdmin;
import com.luzko.libraryapp.controller.command.impl.page.AdminPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.CreateBookPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.HomePageCommand;
import com.luzko.libraryapp.controller.command.impl.page.LibraryPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.LoginPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.OrderPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.ProfileSettingPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.RegistrationPageCommand;
import com.luzko.libraryapp.controller.command.impl.page.UserPageCommand;

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
