package com.luzko.libraryapp.service.impl;

import com.luzko.libraryapp.model.builder.UserBuilder;
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
import com.luzko.libraryapp.validator.ValueValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public boolean verifyUser(String login, String password) throws ServiceException {
        logger.log(Level.INFO, "Verify user execute: {}, {}", login, password);
        boolean isCredentialCorrect = false;
        if (UserValidator.isLoginValid(login) && UserValidator.isPasswordValid(password)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                String userPassword = userDao.findPasswordByLogin(login);
                if (userPassword != null && !userPassword.isEmpty()) {
                    String encryptedPassword = PasswordEncryption.encrypt(password);
                    isCredentialCorrect = userPassword.equals(encryptedPassword);
                }
            } catch (DaoException e) {
                throw new ServiceException("Verify user error", e);
            }
        }
        return isCredentialCorrect;
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        logger.log(Level.INFO, "Find by login execute: {}", login);
        Optional<User> userOptional = Optional.empty();
        if (UserValidator.isLoginValid(login)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                userOptional = userDao.findByLogin(login);
            } catch (DaoException e) {
                throw new ServiceException("Find by login error", e);
            }
        }
        return userOptional;
    }

    @Override
    public boolean registration(Map<String, String> registrationParameter, boolean isLibrarian) throws ServiceException {
        logger.log(Level.INFO, "Registration execute");
        boolean isRegistered = false;
        UserDao userDao = UserDaoImpl.getInstance();
        if (UserValidator.isValidRegistrationParameter(registrationParameter)) {
            try {
                String login = registrationParameter.get(ColumnName.LOGIN);
                String encryptedPassword = PasswordEncryption.encrypt(registrationParameter.get(ColumnName.PASSWORD));
                String name = registrationParameter.get(ColumnName.NAME);
                String surname = registrationParameter.get(ColumnName.SURNAME);
                String email = registrationParameter.get(ColumnName.EMAIL);
                String codeConfirm = registrationParameter.get(ColumnName.CONFIRM_CODE);
                UserRole userRole = isLibrarian ? UserRole.LIBRARIAN : UserRole.READER;
                UserBuilder userBuilder = new UserBuilder()
                        .setLogin(login)
                        .setName(name)
                        .setSurname(surname)
                        .setEmail(email)
                        .setUserRole(userRole);
                User user = new User(userBuilder);
                isRegistered = userDao.add(user, encryptedPassword, codeConfirm);
            } catch (DaoException e) {
                throw new ServiceException("Registration error", e);
            }
        }
        return isRegistered;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        logger.log(Level.INFO, "Find all execute");
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public boolean isChangeUserStatus(String login, String userStatus) throws ServiceException {
        logger.log(Level.INFO, "Change user status execute: {}, {}", login, userStatus);
        boolean isChangeStatus = false;
        if (ValueValidator.isValidValue(userStatus)) {
            UserDao userDao = UserDaoImpl.getInstance();
            UserStatus status = UserStatus.valueOf(userStatus);
            try {
                int statusCode = status == UserStatus.ACTIVE ? UserStatus.BLOCKED.defineId() : UserStatus.ACTIVE.defineId();
                isChangeStatus = userDao.isChangeUserStatus(login, statusCode);
            } catch (DaoException e) {
                throw new ServiceException("Change user status error", e);
            }
        }
        return isChangeStatus;
    }

    @Override
    public boolean isLoginUnique(String login) throws ServiceException {
        logger.log(Level.INFO, "Check unique login execute: {}", login);
        boolean isLoginUnique = false;
        if (UserValidator.isLoginValid(login)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                isLoginUnique = userDao.isLoginUnique(login);
            } catch (DaoException e) {
                throw new ServiceException("Login unique error", e);
            }
        }
        return isLoginUnique;
    }

    @Override
    public boolean isCodeConfirmCorrect(String login, String codeConfirm) throws ServiceException {
        logger.log(Level.INFO, "Check code confirmation execute: {}, {}", login, codeConfirm);
        boolean isCodeConfirmCorrect = false;
        if (ValueValidator.isValidValue(codeConfirm)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                String code = userDao.findCodeConfirmByLogin(login);
                if (codeConfirm.equals(code)) {
                    isCodeConfirmCorrect = userDao.isChangeUserStatus(login, UserStatus.ACTIVE.defineId());
                }
            } catch (DaoException e) {
                throw new ServiceException("Code confirmation error", e);
            }
        }
        return isCodeConfirmCorrect;
    }

    @Override
    public boolean isUserLoginChange(String login, String newLogin) throws ServiceException {
        logger.log(Level.INFO, "Change user login execute: {}, {}", login, newLogin);
        boolean isUserLoginChange = false;
        if (UserValidator.isLoginValid(newLogin)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                if (userDao.findByLogin(newLogin).isEmpty()) {
                    isUserLoginChange = userDao.isChangeUserLogin(login, newLogin);
                }
            } catch (DaoException e) {
                throw new ServiceException("User login change error", e);
            }
        }
        return isUserLoginChange;
    }

    @Override
    public boolean isUserNameChange(String login, String newName) throws ServiceException {
        logger.log(Level.INFO, "Change name execute: {}, {}", login, newName);
        boolean isUserNameChange = false;
        if (UserValidator.isNameValid(newName)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                isUserNameChange = userDao.isChangeUserName(login, newName);
            } catch (DaoException e) {
                throw new ServiceException("User name change error", e);
            }
        }
        return isUserNameChange;
    }

    @Override
    public boolean isUserSurnameChange(String login, String newSurname) throws ServiceException {
        logger.log(Level.INFO, "Change surname execute: {}, {}", login, newSurname);
        boolean isUserSurnameChange = false;
        if (UserValidator.isNameValid(newSurname)) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                isUserSurnameChange = userDao.isChangeUserSurname(login, newSurname);
            } catch (DaoException e) {
                throw new ServiceException("Surname change error", e);
            }
        }
        return isUserSurnameChange;
    }

    @Override
    public boolean isChangeProfileImage(String login, String newAvatar) throws ServiceException {
        logger.log(Level.INFO, "Change avatar execute: {}, {}", login, newAvatar);
        boolean isAvatarChange = false;
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            if (newAvatar != null && !newAvatar.isBlank()) {
                isAvatarChange = userDao.isChangeUserAvatar(login, newAvatar);
            }
        } catch (DaoException e) {
            throw new ServiceException("Avatar change error", e);
        }
        return isAvatarChange;
    }

    @Override
    public void giveBooksFromReadingRoom(Object userIdObject, Object userRoleObject) throws ServiceException {
        logger.log(Level.INFO, "Give book from reading room execute: {}", userIdObject);
        UserDao userDao = UserDaoImpl.getInstance();
        long userId = (long) userIdObject;
        UserRole userRole = (UserRole) userRoleObject;
        try {
            if (userRole.equals(UserRole.READER)) {
                userDao.giveBooksFromReadingRoom(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException("Give all book error", e);
        }
    }
}
