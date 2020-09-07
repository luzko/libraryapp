package com.luzko.libraryapp.service;

import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.entity.Role;
import com.luzko.libraryapp.model.entity.User;
import com.luzko.libraryapp.service.impl.UserServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class UserServiceImplTest {
    private UserService service;
    private User user;

    @BeforeClass
    public void setUp() {
        service = UserServiceImpl.getInstance();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        user = new User("test1", "test1@gmail.com", "pass1test", roleSet);
    }

    @Test
    public void savePositiveTest() {
        try {
            boolean actual = service.save(user);
            assertTrue(actual);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @AfterClass
    public void tierDown() {
        service = null;
        user = null;
    }
}
