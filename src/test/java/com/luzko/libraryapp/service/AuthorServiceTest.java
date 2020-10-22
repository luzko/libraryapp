package com.luzko.libraryapp.service;

import com.luzko.libraryapp.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.AuthorDao;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.mock;

@PrepareForTest(AuthorDao.class)
public class AuthorServiceTest {
    private AuthorDao daoMock;
    private AuthorService authorService;

    @BeforeClass
    public void setUp() {
        daoMock = mock(AuthorDao.class);
        authorService = ServiceFactory.getInstance().getAuthorService();
        Whitebox.setInternalState(AuthorDao.class, "instance", daoMock);
    }



    @AfterClass
    public void tierDown() {
        daoMock = null;
        authorService = null;
    }
}
