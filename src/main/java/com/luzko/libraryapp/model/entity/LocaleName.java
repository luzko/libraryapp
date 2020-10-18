package com.luzko.libraryapp.model.entity;

public class LocaleName {
    private LocaleName() {

    }

    //category
    public static final String CATEGORY_FANTASY = "category.fantasy";
    public static final String CATEGORY_HISTORY = "category.history";
    public static final String CATEGORY_AUTOBIOGRAPHY = "category.autobiography";
    public static final String CATEGORY_PHILOSOPHY = "category.philosophy";
    public static final String CATEGORY_NOVEL = "category.novel";
    public static final String CATEGORY_PSYCHOLOGY = "category.psychology";

    //user status
    public static final String USER_STATUS_ACTIVE = "user.status.active";
    public static final String USER_STATUS_BLOCKED = "user.status.blocked";
    public static final String USER_STATUS_UNCONFIRMED = "user.status.unconfirmed";

    //user role
    public static final String ROLE_ADMIN = "user.role.admin";
    public static final String ROLE_LIBRARIAN = "user.role.librarian";
    public static final String ROLE_READER = "user.role.reader";

    //order type
    public static final String TYPE_READING_ROOM = "type.reading.room";
    public static final String TYPE_HOME = "type.home";

    //order status
    public static final String ORDER_STATUS_NEW = "order.status.new";
    public static final String ORDER_STATUS_APPROVED = "order.status.approved";
    public static final String ORDER_STATUS_DENIED = "order.status.denied";
    public static final String ORDER_STATUS_CANCELED = "order.status.canceled";
    public static final String ORDER_STATUS_RETURNED = "order.status.returned";
}
