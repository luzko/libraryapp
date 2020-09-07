package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

public interface UserService extends BaseService<User> {

    User findByEmail(String email) throws ServiceException;

    boolean checkLogin(String login, String password) throws ServiceException;

    boolean updatePassword(String newPassword);
}
