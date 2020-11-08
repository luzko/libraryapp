package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface represents User Service.
 */
public interface UserService {
    /**
     * Find count all users.
     *
     * @return the count users
     * @throws ServiceException the service exception
     */
    int findCount() throws ServiceException;

    /**
     * Find count users by search value.
     *
     * @param searchName the search value
     * @return the count users
     * @throws ServiceException the service exception
     */
    int findCount(String searchName) throws ServiceException;

    /**
     * Find part users.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found users
     * @throws ServiceException the service exception
     */
    List<User> findPartOfAll(int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Find users by login.
     *
     * @param searchName     the search value
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found users
     * @throws ServiceException the service exception
     */
    List<User> findByLogin(String searchName, int recordsShown, int recordsPerPage) throws ServiceException;

    /**
     * Verify user.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isVerifyUser(String login, String password) throws ServiceException;

    /**
     * Check change password.
     *
     * @param login       the login value
     * @param newPassword the new password value
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isChangePassword(String login, String newPassword) throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login value
     * @return the optional of found User
     * @throws ServiceException the service exception
     */
    Optional<User> findByLogin(String login) throws ServiceException;

    /**
     * Add new user.
     *
     * @param registrationParameter the registration parameter
     * @param isLibrarian           the is librarian
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isRegistration(Map<String, String> registrationParameter, boolean isLibrarian) throws ServiceException;

    /**
     * Change user status.
     *
     * @param login      the login value
     * @param userStatus the user status
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isChangeUserStatus(String login, String userStatus) throws ServiceException;

    /**
     * Check login unique.
     *
     * @param login the login value
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginUnique(String login) throws ServiceException;

    /**
     * Find code confirm by login.
     *
     * @param login the login value
     * @return the code confirm
     * @throws ServiceException the service exception
     */
    String findCodeConfirm(String login) throws ServiceException;

    /**
     * Check code confirm correct.
     *
     * @param login       the login value
     * @param codeConfirm the code confirm
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isCodeConfirmCorrect(String login, String codeConfirm) throws ServiceException;

    /**
     * Change user login.
     *
     * @param login    the login value
     * @param newLogin the new login value
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserLoginChange(String login, String newLogin) throws ServiceException;

    /**
     * Change user name.
     *
     * @param login   the login value
     * @param newName the new name value
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserNameChange(String login, String newName) throws ServiceException;

    /**
     * Change user surname.
     *
     * @param login      the login value
     * @param newSurname the new surname value
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isUserSurnameChange(String login, String newSurname) throws ServiceException;

    /**
     * Change profile image.
     *
     * @param login     the login value
     * @param newAvatar the new avatar path
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
}
