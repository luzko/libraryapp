package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    boolean verifyUser(String login, String password) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    boolean registration(Map<String, String> registrationParameter, boolean isLibrarian) throws ServiceException;

    List<User> findAll() throws ServiceException;

    boolean changeUserStatus(String login, String userStatus) throws ServiceException;

    boolean isLoginUnique(String login) throws ServiceException;

    boolean isCodeConfirmCorrect(String login, String codeConfirm) throws ServiceException;

    boolean isUserLoginChange(String login, String newLogin) throws ServiceException;

    boolean isUserNameChange(String login, String newName) throws ServiceException;

    boolean isUserSurnameChange(String login, String newSurname) throws ServiceException;

    boolean isChangeProfileImage(String login, String newAvatar) throws ServiceException;

    void giveBooksFromReadingRoom(Object userId, Object userRole) throws ServiceException;
}
