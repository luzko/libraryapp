package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Verify user.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean verifyUser(String login, String password) throws ServiceException;

    /**
     * Find by login.
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findByLogin(String login) throws ServiceException;

    /**
     * Registration.
     *
     * @param registrationParameter the registration parameter
     * @param isLibrarian           the is librarian
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registration(Map<String, String> registrationParameter, boolean isLibrarian) throws ServiceException;

    /**
     * Find all.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;

    /**
     * Find part.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findPart(int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Change user status.
     *
     * @param login      the login
     * @param userStatus the user status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isChangeUserStatus(String login, String userStatus) throws ServiceException;

    /**
     * Check login unique.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginUnique(String login) throws ServiceException;

    /**
     * Check code confirm correct.
     *
     * @param login       the login
     * @param codeConfirm the code confirm
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isCodeConfirmCorrect(String login, String codeConfirm) throws ServiceException;

    /**
     * Change user login.
     *
     * @param login    the login
     * @param newLogin the new login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserLoginChange(String login, String newLogin) throws ServiceException;

    /**
     * Change user name.
     *
     * @param login   the login
     * @param newName the new name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserNameChange(String login, String newName) throws ServiceException;

    /**
     * Change user surname.
     *
     * @param login      the login
     * @param newSurname the new surname
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserSurnameChange(String login, String newSurname) throws ServiceException;

    /**
     * Change profile image.
     *
     * @param login     the login
     * @param newAvatar the new avatar
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isChangeProfileImage(String login, String newAvatar) throws ServiceException;

    /**
     * Give books from reading room.
     *
     * @param userId   the user id
     * @param userRole the user role
     * @throws ServiceException the service exception
     */
    void giveBooksFromReadingRoom(Object userId, Object userRole) throws ServiceException;

    /**
     * Find count records.
     *
     * @return the int
     * @throws ServiceException the service exception
     */
    int findCountRecords() throws ServiceException;
}
