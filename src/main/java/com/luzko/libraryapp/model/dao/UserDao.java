package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {
    String findPasswordByLogin(String login) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    boolean add(User user, String password, String codeConfirm) throws DaoException;

    boolean changeUserStatus(String login, int status) throws DaoException;

    boolean isLoginUnique(String login) throws DaoException;

    String findCodeConfirmByLogin(String login) throws DaoException;

    boolean isChangeUserLogin(String login, String newLogin) throws DaoException;

    boolean isChangeUserName(String login, String newName) throws DaoException;

    boolean isChangeUserSurname(String login, String newSurname) throws DaoException;

    boolean isChangeUserAvatar(String login, String newAvatar) throws DaoException;

    void giveBooksFromReadingRoom(long userId) throws DaoException;
}
