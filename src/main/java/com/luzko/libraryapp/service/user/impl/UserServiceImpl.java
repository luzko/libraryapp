package com.luzko.libraryapp.service.user.impl;

import com.luzko.libraryapp.model.builder.UserBuilder;
import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.factory.DaoFactory;
import com.luzko.libraryapp.factory.ValidatorFactory;
import com.luzko.libraryapp.model.dao.ColumnName;
import com.luzko.libraryapp.model.dao.user.UserDao;
import com.luzko.libraryapp.model.entity.user.User;
import com.luzko.libraryapp.model.entity.user.UserRole;
import com.luzko.libraryapp.model.entity.user.UserStatus;
import com.luzko.libraryapp.service.user.UserService;
import com.luzko.libraryapp.util.PasswordEncryption;
import com.luzko.libraryapp.validator.UserValidator;
import com.luzko.libraryapp.validator.ValueValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public boolean verifyUser(String login, String password) throws ServiceException {
        logger.log(Level.INFO, "Verify user execute: {}, {}", login, password);
        boolean isCredentialCorrect = false;
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isLoginValid(login) && userValidator.isPasswordValid(password)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
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
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isLoginValid(login)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            try {
                userOptional = userDao.findByLogin(login);
            } catch (DaoException e) {
                throw new ServiceException("Find by login error", e);
            }
        }
        return userOptional;
    }

    @Override
    public boolean registration(Map<String, String> registrationParameters, boolean isLibrarian) throws ServiceException {
        logger.log(Level.INFO, "Registration execute");
        boolean isRegistered = false;
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        UserDao userDao = DaoFactory.getInstance().getUserDAO();
        if (userValidator.isValidRegistrationParameters(registrationParameters)) {
            try {
                String login = registrationParameters.get(ColumnName.LOGIN);
                String encryptedPassword = PasswordEncryption.encrypt(registrationParameters.get(ColumnName.PASSWORD));
                String name = registrationParameters.get(ColumnName.NAME);
                String surname = registrationParameters.get(ColumnName.SURNAME);
                String email = registrationParameters.get(ColumnName.EMAIL);
                String codeConfirm = registrationParameters.get(ColumnName.CONFIRM_CODE);
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
        UserDao userDao = DaoFactory.getInstance().getUserDAO();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Find all error", e);
        }
    }

    @Override
    public boolean changeUserStatus(String login, String userStatus) throws ServiceException {
        logger.log(Level.INFO, "Change user status execute: {}, {}", login, userStatus);
        boolean isChangeStatus = false;
        ValueValidator valueValidator = ValidatorFactory.getInstance().getValueValidator();
        if (valueValidator.isValidValue(userStatus)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            UserStatus status = UserStatus.valueOf(userStatus);
            try {
                int statusCode = status == UserStatus.ACTIVE ? UserStatus.BLOCKED.defineId() : UserStatus.ACTIVE.defineId();
                isChangeStatus = userDao.changeUserStatus(login, statusCode);
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
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isLoginValid(login)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
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
        ValueValidator valueValidator = ValidatorFactory.getInstance().getValueValidator();
        if (valueValidator.isValidValue(codeConfirm)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            try {
                String code = userDao.findCodeConfirmByLogin(login);
                if (codeConfirm.equals(code)) {
                    isCodeConfirmCorrect = userDao.changeUserStatus(login, UserStatus.ACTIVE.defineId());
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
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isLoginValid(newLogin)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            try {
                if (userDao.findByLogin(newLogin).isEmpty()) {
                    isUserLoginChange = userDao.changeUserLogin(login, newLogin);
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
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isNameValid(newName)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            try {
                isUserNameChange = userDao.changeUserName(login, newName);
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
        UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
        if (userValidator.isNameValid(newSurname)) {
            UserDao userDao = DaoFactory.getInstance().getUserDAO();
            try {
                isUserSurnameChange = userDao.changeUserSurname(login, newSurname);
            } catch (DaoException e) {
                throw new ServiceException("Surname change error", e);
            }
        }
        return isUserSurnameChange;
    }
}
