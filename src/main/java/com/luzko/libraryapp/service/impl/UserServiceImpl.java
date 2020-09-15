package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.util.PasswordEncryption;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean registration(String login, String password, String name, String surname, String email) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        PasswordEncryption encryption = PasswordEncryption.getInstance();
        boolean result = false;
        //TODO validation на то какие пришли строки
        String encryptedPassword = encryption.encrypt(password);
        //TODO проверить, что такого юзера с таким логином и такой почтой нет.
        User user = new User(null, login, encryptedPassword, UserRole.READER, name, surname, email);
        try {
            result = userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(); //TODO
        }

        return false;
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
    public boolean checkLogin(String login, String password) throws ServiceException {
        //TODO
        UserDao userDao = UserDaoImpl.getInstance();
        PasswordEncryption encryption = PasswordEncryption.getInstance();
        boolean isLoginCorrect = false;
        //TODO validator на ввод символов...
        String encryptedPassword = encryption.encrypt(password);
        try {
            User user = userDao.findByLogin(login);
            if (user != null) {
                isLoginCorrect = user.getPassword().equals(encryptedPassword);
            }
        } catch (DaoException e) {
            //TODO
        }
        return isLoginCorrect;
    }

    @Override
    public boolean updatePassword(String newPassword) {
        return false;
    }

}
