package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
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

import static com.luzko.libraryapp.model.dao.ColumnName.*;

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
        if (validator.isValidRegistrationParameters(registrationParameters)) {
            //TODO проверить, что такого юзера с таким логином и такой почтой нет. !!!
            try {
                String login = registrationParameters.get(LOGIN);
                String encryptedPassword = encryption.encrypt(registrationParameters.get(PASSWORD));
                String name = registrationParameters.get(NAME);
                String surname = registrationParameters.get(SURNAME);
                String email = registrationParameters.get(EMAIL);
                String codeConfirm = registrationParameters.get(CONFIRM_CODE);

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
}
