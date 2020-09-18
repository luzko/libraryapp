package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    boolean verifyUser(String login, String password) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    boolean registration(Map<String, String> registrationParameters) throws ServiceException;

}
