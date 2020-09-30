package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.UserDao;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.model.entity.UserRole;
import com.luzko.libraryapp.model.entity.UserStatus;
import com.luzko.libraryapp.service.UserService;
import com.luzko.libraryapp.util.PasswordEncryption;
import com.luzko.libraryapp.validator.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean verifyUser(String login, String password) throws ServiceException {
        //UserDao userDao = UserDaoImpl.getInstance();
        UserDao userDao = new UserDaoImpl();
        PasswordEncryption encryption = PasswordEncryption.getInstance();
        boolean isCredentialCorrect = false;
        //TODO валидация на входящие поля логина и пароля, если апраори неверные, то зачем выполнять работу..

        try {
            String userPassword = userDao.findPasswordByLogin(login);
            if (userPassword != null && !userPassword.isEmpty()) {
                String encryptedPassword = encryption.encrypt(password);
                isCredentialCorrect = userPassword.equals(encryptedPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException(""); //TODO
        }
        return isCredentialCorrect;
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        //UserDao userDao = UserDaoImpl.getInstance();
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("service");
        }
    }

    @Override
    public boolean registration(Map<String, String> registrationParameters, boolean isLibrarian) throws ServiceException {
        UserValidator validator = new UserValidator();
        //UserDao userDao = UserDaoImpl.getInstance();
        UserDao userDao = new UserDaoImpl();
        PasswordEncryption encryption = PasswordEncryption.getInstance();
        boolean isRegistered = false;
        //System.out.println(registrationParameters);
        if (validator.isValidRegistrationParameters(registrationParameters)) {
            //if (true) {
            try {
                String login = registrationParameters.get(ColumnName.LOGIN);
                String encryptedPassword = encryption.encrypt(registrationParameters.get(ColumnName.PASSWORD));
                String name = registrationParameters.get(ColumnName.NAME);
                String surname = registrationParameters.get(ColumnName.SURNAME);
                String email = registrationParameters.get(ColumnName.EMAIL);
                String codeConfirm = registrationParameters.get(ColumnName.CONFIRM_CODE);
                UserRole userRole = isLibrarian ? UserRole.LIBRARIAN : UserRole.READER;
                isRegistered = userDao.add(login, encryptedPassword, userRole, name, surname, email, codeConfirm);
            } catch (DaoException e) {
                throw new ServiceException("service"); // TODO
            }
        }
        return isRegistered;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("service");
        }
    }

    @Override
    public boolean changeUserStatus(String login, String userStatus) throws ServiceException {
        //TODO проверка входных значений.. чтоб типо не null, ибо смысл дальше что-то делать, если null..
        UserDao userDao = new UserDaoImpl();
        boolean isChangeStatus = false;
        UserStatus status = UserStatus.valueOf(userStatus);
        try {
            if (status == UserStatus.ACTIVE) {
                isChangeStatus = userDao.changeUserStatus(login, UserStatus.BLOCKED.getId());
            } else if (status == UserStatus.BLOCKED) {
                isChangeStatus = userDao.changeUserStatus(login, UserStatus.ACTIVE.getId());
            }
        } catch (DaoException e) {
            throw new ServiceException("service");
        }

        return isChangeStatus;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        System.out.println(login + " service");
        UserDao userDao = new UserDaoImpl();
        boolean isLoginUnique = false;
        try {
            isLoginUnique = userDao.isLoginUnique(login);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }

        return isLoginUnique;
    }
}
