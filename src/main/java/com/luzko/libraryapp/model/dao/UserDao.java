package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;

public interface UserDao extends BaseDao<User> {

    User findByEmail(String email) throws DaoException;

    User findByLogin(String login) throws DaoException;

    void registerUser(User user) throws DaoException;

}
