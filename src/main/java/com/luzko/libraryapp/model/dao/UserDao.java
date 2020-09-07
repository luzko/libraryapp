package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;

public interface UserDao extends BaseDao<User> {

    User findByEmail(String email) throws DaoException;
}
