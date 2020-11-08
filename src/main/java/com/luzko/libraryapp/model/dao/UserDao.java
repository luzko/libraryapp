package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents User DAO.
 */
public interface UserDao extends BaseDao {
    /**
     * Find count all users.
     *
     * @return the count users
     * @throws DaoException the dao exception
     */
    int findCount() throws DaoException;

    /**
     * Find count users by search value.
     *
     * @param searchName the search value
     * @return the count users
     * @throws DaoException the dao exception
     */
    int findCount(String searchName) throws DaoException;

    /**
     * Find part users.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findPartOfAll(int recordsShown, int recordsPerPage) throws DaoException;

    /**
     * Find users by login.
     *
     * @param searchName     the search value
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return  the list of found users
     * @throws DaoException the dao exception
     */
    List<User> findByLogin(String searchName, int recordsShown, int recordsPerPage) throws DaoException;

    /**
     * Find password by login.
     *
     * @param login the login value
     * @return the password
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Check change password.
     *
     * @param login       the login value
     * @param newPassword the new password
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangePassword(String login, String newPassword) throws DaoException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional of found User
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Add new user.
     *
     * @param user        the user value
     * @param password    the password value
     * @param codeConfirm the code confirm
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password, String codeConfirm) throws DaoException;

    /**
     * Change user status.
     *
     * @param login  the login value
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserStatus(String login, int status) throws DaoException;

    /**
     * Check login unique.
     *
     * @param login the login value
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isLoginUnique(String login) throws DaoException;

    /**
     * Find code confirm by login.
     *
     * @param login the login value
     * @return the code confirm
     * @throws DaoException the dao exception
     */
    String findCodeConfirmByLogin(String login) throws DaoException;

    /**
     * Change user login.
     *
     * @param login    the login value
     * @param newLogin the new login value
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserLogin(String login, String newLogin) throws DaoException;

    /**
     * Change user name.
     *
     * @param login   the login value
     * @param newName the new name value
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserName(String login, String newName) throws DaoException;

    /**
     * Change user surname.
     *
     * @param login      the login value
     * @param newSurname the new surname value
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserSurname(String login, String newSurname) throws DaoException;

    /**
     * Change user avatar.
     *
     * @param login     the login value
     * @param newAvatar the new avatar path
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserAvatar(String login, String newAvatar) throws DaoException;

    /**
     * Give books from reading room.
     *
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void giveBooksFromReadingRoom(long userId) throws DaoException;
}
