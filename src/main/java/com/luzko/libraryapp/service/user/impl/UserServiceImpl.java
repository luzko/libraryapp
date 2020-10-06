package com.luzko.libraryapp.service.user.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.user.UserDao;
import com.luzko.libraryapp.model.dao.user.impl.UserDaoImpl;
import com.luzko.libraryapp.model.entity.user.User;
import com.luzko.libraryapp.model.entity.user.UserRole;
import com.luzko.libraryapp.model.entity.user.UserStatus;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.util.PasswordEncryption;
import com.luzko.libraryapp.validator.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();
    //TODO add logger..

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean verifyUser(String login, String password) throws ServiceException {
        //TODO валидация на входящие поля логина и пароля, если апраори неверные, то зачем выполнять работу..
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isCredentialCorrect = false;
        try {
            String userPassword = userDao.findPasswordByLogin(login);
            if (userPassword != null && !userPassword.isEmpty()) {
                String encryptedPassword = PasswordEncryption.encrypt(password);
                isCredentialCorrect = userPassword.equals(encryptedPassword);
            }
        } catch (DaoException e) {
            throw new ServiceException("", e); //TODO
        }
        return isCredentialCorrect;
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        //TODO проверка входных значений.. чтоб типо не null, ибо смысл дальше что-то делать, если null..
        //TODO вызов валидатора..
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
    }

    @Override
    public boolean registration(Map<String, String> registrationParameters, boolean isLibrarian) throws ServiceException {
        //TODO проверка входных значений.. чтоб типо не null, ибо смысл дальше что-то делать, если null..
        //TODO вызов валидатора..
        UserValidator validator = new UserValidator();
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isRegistered = false;
        if (validator.isValidRegistrationParameters(registrationParameters)) {
            try {
                String login = registrationParameters.get(ColumnName.LOGIN);
                String encryptedPassword = PasswordEncryption.encrypt(registrationParameters.get(ColumnName.PASSWORD));
                String name = registrationParameters.get(ColumnName.NAME);
                String surname = registrationParameters.get(ColumnName.SURNAME);
                String email = registrationParameters.get(ColumnName.EMAIL);
                String codeConfirm = registrationParameters.get(ColumnName.CONFIRM_CODE);
                UserRole userRole = isLibrarian ? UserRole.LIBRARIAN : UserRole.READER;
                isRegistered = userDao.add(login, encryptedPassword, userRole, name, surname, email, codeConfirm);
            } catch (DaoException e) {
                throw new ServiceException("service", e); // TODO
            }
        }
        return isRegistered;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
    }

    @Override
    public boolean changeUserStatus(String login, String userStatus) throws ServiceException {
        //TODO проверка входных значений.. чтоб типо не null, ибо смысл дальше что-то делать, если null..
        //TODO вызов валидатора..
        UserDao userDao = UserDaoImpl.getInstance();
        UserStatus status = UserStatus.valueOf(userStatus);
        boolean isChangeStatus;
        try {
            int statusCode = status == UserStatus.ACTIVE ? UserStatus.BLOCKED.defineId() : UserStatus.ACTIVE.defineId();
            isChangeStatus = userDao.changeUserStatus(login, statusCode);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
        return isChangeStatus;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        //TODO проверка входных значений.. чтоб типо не null, ибо смысл дальше что-то делать, если null..
        //TODO вызов валидатора..
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isLoginUnique;
        try {
            isLoginUnique = userDao.isLoginUnique(login);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
        return isLoginUnique;
    }

    @Override
    public boolean isCodeConfirmCorrect(String login, String codeConfirm) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isCodeConfirmCorrect = false;
        try {
            String code = userDao.findCodeConfirmByLogin(login);
            if (codeConfirm.equals(code)) {
                isCodeConfirmCorrect = userDao.changeUserStatus(login, UserStatus.ACTIVE.defineId());
            }
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
        return isCodeConfirmCorrect;
    }

    @Override
    public boolean isUserLoginChange(String login, String newLogin) throws ServiceException {
        //TODO если новый логин нулевой или пустой, но сразу возвращать false
        //TODO так же валидация!!!
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isUserLoginChange = false;
        try {
            if (userDao.findByLogin(newLogin).isEmpty()) {
                isUserLoginChange = userDao.changeUserLogin(login, newLogin);
            }
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
        return isUserLoginChange;
    }

    @Override
    public boolean isUserNameChange(String login, String newName) throws ServiceException {
        //TODO если новое имя нулевое или пустое, то сразу возвращать false
        //TODO так же валидация!!!
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isUserNameChange;
        try {
            isUserNameChange = userDao.changeUserName(login, newName);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }
        return isUserNameChange;
    }

    @Override
    public boolean isUserSurnameChange(String login, String newSurname) throws ServiceException {
        //TODO если новое имя нулевое или пустое, то сразу возвращать false
        //TODO так же валидация!!!
        UserDao userDao = UserDaoImpl.getInstance();
        boolean isUserSurnameChange;
        try {
            isUserSurnameChange = userDao.changeUserSurname(login, newSurname);
        } catch (DaoException e) {
            throw new ServiceException("service", e);
        }

        return isUserSurnameChange;
    }
}
