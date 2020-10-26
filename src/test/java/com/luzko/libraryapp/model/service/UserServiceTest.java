package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.UserDaoImpl;
import com.luzko.libraryapp.model.entity.User;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@PrepareForTest(UserDaoImpl.class)
public class UserServiceTest {
    private UserDaoImpl daoMock;
    private UserService userService;

    @BeforeClass
    public void setUp() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Test
    public void verifyUserPositiveTest() {
        String login = "user12345";
        String password = "qwerQWER1234";
        try {
            when(daoMock.findPasswordByLogin(anyString())).thenReturn("ccc0efccbeafae925ae3f2987bb170b644b4083d");
            boolean actual = userService.verifyUser(login, password);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void verifyUserNegativeTest() {
        String login = "user12345";
        String password = "qwerQWER1234";
        try {
            when(daoMock.findPasswordByLogin(anyString())).thenReturn("qwerQWER1234");
            boolean actual = userService.verifyUser(login, password);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void verifyUserExceptionTest() {
        String login = "user12345";
        String password = "qwerQWER1234";
        try {
            when(daoMock.findPasswordByLogin(anyString())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.verifyUser(login, password));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void findByLoginPositiveTest() {
        String login = "user1234";
        User user = new User();
        Optional<User> expectedUserOptional = Optional.of(user);
        try {
            when(daoMock.findByLogin(login)).thenReturn(Optional.of(user));
            Optional<User> actualUserOptional = userService.findByLogin(login);
            assertEquals(actualUserOptional, expectedUserOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByLoginNegativeTest() {
        String login = "user1234";
        User user = new User();
        Optional<User> expectedUserOptional = Optional.empty();
        try {
            when(daoMock.findByLogin(login)).thenReturn(Optional.of(user));
            Optional<User> actualUserOptional = userService.findByLogin(login);
            assertNotEquals(actualUserOptional, expectedUserOptional);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByLoginExceptionTest() {
        String login = "user1234";
        User user = new User();
        try {
            when(daoMock.findByLogin(login)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.findByLogin(login));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void findAllPositiveTest() {
        User user = new User();
        List<User> expectedUserList = List.of(user);
        try {
            when(daoMock.findAll()).thenReturn(List.of(user));
            List<User> actualUserList = userService.findAll();
            assertEquals(actualUserList, expectedUserList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllNegativeTest() {
        User user = new User();
        List<User> expectedUserList = Collections.emptyList();
        try {
            when(daoMock.findAll()).thenReturn(List.of(user));
            List<User> actualUserList = userService.findAll();
            assertNotEquals(actualUserList, expectedUserList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllExceptionTest() {
        try {
            when(daoMock.findAll()).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.findAll());
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isLoginUniquePositiveTest() {
        String login = "user1234";
        try {
            when(daoMock.isLoginUnique(login)).thenReturn(true);
            boolean actual = userService.isLoginUnique(login);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isLoginUniqueNegativeTest() {
        String login = "user";
        try {
            when(daoMock.isLoginUnique(login)).thenReturn(true);
            boolean actual = userService.isLoginUnique(login);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isLoginUniqueExceptionTest() {
        String login = "user1234";
        try {
            when(daoMock.isLoginUnique(anyString())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.isLoginUnique(login));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isUserLoginChangePositiveTest() {
        String login = "user1234";
        String newLogin = "user12344";
        try {
            when(daoMock.isChangeUserLogin(login, newLogin)).thenReturn(true);
            boolean actual = userService.isUserLoginChange(login, newLogin);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isUserLoginChangeNegativeTest() {
        String login = "user1234";
        String newLogin = "user12344";
        try {
            when(daoMock.isChangeUserLogin(login, newLogin)).thenReturn(false);
            boolean actual = userService.isUserLoginChange(login, newLogin);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isUserLoginChangeExceptionTest() {
        String login = "user1234";
        String newLogin = "user12344";
        try {
            when(daoMock.isChangeUserLogin(login, newLogin)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.isUserLoginChange(login, newLogin));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isChangeProfileImagePositiveTest() {
        String login = "user1234";
        String newAvatar = "asdfasdfasf.jpg";
        try {
            when(daoMock.isChangeUserAvatar(login, newAvatar)).thenReturn(true);
            boolean actual = userService.isChangeProfileImage(login, newAvatar);
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isChangeProfileImageNegativeTest() {
        String login = "user1234";
        String newAvatar = "asdfasdfasf.jpg";
        try {
            when(daoMock.isChangeUserAvatar(login, newAvatar)).thenReturn(false);
            boolean actual = userService.isChangeProfileImage(login, newAvatar);
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isChangeProfileImageExceptionTest() {
        String login = "user1234";
        String newAvatar = "user12344";
        try {
            when(daoMock.isChangeUserAvatar(login, newAvatar)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> userService.isChangeProfileImage(login, newAvatar));
        } catch (DaoException e) {
            fail();
        }
    }

    @BeforeMethod
    public void setUpMock() {
        daoMock = mock(UserDaoImpl.class);
        Whitebox.setInternalState(UserDaoImpl.class, "INSTANCE", daoMock);
    }

    @AfterClass
    public void tierDown() {
        daoMock = null;
        userService = null;
    }
}
