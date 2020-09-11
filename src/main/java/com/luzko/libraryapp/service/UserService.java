package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email) throws ServiceException;

    boolean checkLogin(String login, String password) throws ServiceException;

    boolean updatePassword(String newPassword);

    boolean save(User user) throws ServiceException;

    boolean update(User user) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    User findById(long id) throws ServiceException;

    List<User> findByName(String name) throws ServiceException;

    List<User> findAll() throws ServiceException;
}
