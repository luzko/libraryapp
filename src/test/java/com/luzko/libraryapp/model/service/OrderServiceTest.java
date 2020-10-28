package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import com.luzko.libraryapp.model.dao.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.entity.Order;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

@PrepareForTest(OrderDaoImpl.class)
public class OrderServiceTest {
    private OrderDaoImpl daoMock;
    private OrderService orderService;

    @BeforeClass
    public void setUp() {
        orderService = ServiceFactory.getInstance().getOrderService();
    }

    @BeforeMethod
    public void setUpMock() {
        daoMock = mock(OrderDaoImpl.class);
        Whitebox.setInternalState(OrderDaoImpl.class, "INSTANCE", daoMock);
    }

    /*@Test
    public void findByUserIdPositiveTest() {
        Order order = new Order();
        List<Order> expectedOrderList = List.of(order);
        try {
            when(daoMock.findByUserId(anyLong())).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findByUserId(1);
            assertEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByUserIdNegativeTest() {
        Order order = new Order();
        List<Order> expectedOrderList = Collections.emptyList();
        try {
            when(daoMock.findByUserId(anyLong())).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findByUserId(1);
            assertNotEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findByUserIdExceptionTest() {
        try {
            when(daoMock.findByUserId(anyLong())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> orderService.findByUserId(1));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void findAllPositiveTest() {
        Order order = new Order();
        List<Order> expectedOrderList = List.of(order);
        try {
            when(daoMock.findAll()).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findAll();
            assertEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllNegativeTest() {
        Order order = new Order();
        List<Order> expectedOrderList = Collections.emptyList();
        try {
            when(daoMock.findAll()).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findAll();
            assertNotEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findAllExceptionTest() {
        try {
            when(daoMock.findAll()).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> orderService.findAll());
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isCancelPositiveTest() {
        try {
            when(daoMock.isCancel(anyLong())).thenReturn(true);
            boolean actual = orderService.isCancel("1");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isCancelNegativeTest() {
        try {
            when(daoMock.isCancel(anyLong())).thenReturn(false);
            boolean actual = orderService.isCancel("1");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isCancelExceptionTest() {
        try {
            when(daoMock.isCancel(anyLong())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> orderService.isCancel("1"));
        } catch (DaoException e) {
            fail();
        }
    }

    @Test
    public void isApprovePositiveTest() {
        try {
            when(daoMock.isApprove(anyLong(), anyLong(), anyLong())).thenReturn(true);
            boolean actual = orderService.isApprove("1", "2", "3");
            assertTrue(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isApproveNegativeTest() {
        try {
            when(daoMock.isApprove(anyLong(), anyLong(), anyLong())).thenReturn(false);
            boolean actual = orderService.isApprove("1", "2", "3");
            assertFalse(actual);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void isApproveExceptionTest() {
        try {
            when(daoMock.isApprove(anyLong(), anyLong(), anyLong())).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> orderService.isApprove("1", "2", "3"));
        } catch (DaoException e) {
            fail();
        }
    }*/

    @AfterClass
    public void tierDown() {
        daoMock = null;
        orderService = null;
    }
}
