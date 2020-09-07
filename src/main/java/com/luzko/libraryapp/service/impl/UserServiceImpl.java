package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean save(User user) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
            throw new ServiceException("service"); //TODO
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        return false;
    }

    @Override
    public User findById(long id) throws ServiceException {
        return null;
    }

    @Override
    public List<User> findByName(String name) throws ServiceException {
        return null;
    }

    @Override
    public User findByEmail(String email) throws ServiceException {
        return null;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        return null;
    }

    @Override
    public boolean checkLogin(String login, String password) throws ServiceException{
        return true;
    }

    @Override
    public boolean updatePassword(String newPassword) {
        return false;
    }
}
