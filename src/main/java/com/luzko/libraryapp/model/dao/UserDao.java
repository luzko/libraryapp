package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    String findPasswordByLogin(String login) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    boolean add(String login, String password, UserRole role,
                String name, String surname, String email) throws DaoException;

}
