package com.luzko.libraryapp.model.dao;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao extends BaseDao {
    /**
     * Find part.
     *
     * @param recordsShown   the records shown
     * @param recordsPerPage the records per page
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findPart(int recordsShown, int recordsPerPage) throws DaoException;

    /**
     * Find password by login.
     *
     * @param login the login
     * @return the string
     * @throws DaoException the dao exception
     */
    String findPasswordByLogin(String login) throws DaoException;

    /**
     * Find by login.
     *
     * @param login the login
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Add.
     *
     * @param user        the user
     * @param password    the password
     * @param codeConfirm the code confirm
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean add(User user, String password, String codeConfirm) throws DaoException;

    /**
     * Change user status.
     *
     * @param login  the login
     * @param status the status
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserStatus(String login, int status) throws DaoException;

    /**
     * Check login unique.
     *
     * @param login the login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isLoginUnique(String login) throws DaoException;

    /**
     * Find code confirm by login.
     *
     * @param login the login
     * @return the string
     * @throws DaoException the dao exception
     */
    String findCodeConfirmByLogin(String login) throws DaoException;

    /**
     * Change user login.
     *
     * @param login    the login
     * @param newLogin the new login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserLogin(String login, String newLogin) throws DaoException;

    /**
     * Change user name.
     *
     * @param login   the login
     * @param newName the new name
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserName(String login, String newName) throws DaoException;

    /**
     * Change user surname.
     *
     * @param login      the login
     * @param newSurname the new surname
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isChangeUserSurname(String login, String newSurname) throws DaoException;

    /**
     * Change user avatar.
     *
     * @param login     the login
     * @param newAvatar the new avatar
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

    /**
     * Find count all records.
     *
     * @return the int
     * @throws DaoException the dao exception
     */
    int findCountRecords() throws DaoException;
}
