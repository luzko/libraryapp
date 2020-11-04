package com.luzko.libraryapp.model.service;

import com.luzko.libraryapp.exception.DaoException;
import com.luzko.libraryapp.exception.ServiceException;
import com.luzko.libraryapp.model.dao.impl.OrderDaoImpl;
import com.luzko.libraryapp.model.entity.Order;
import com.luzko.libraryapp.model.factory.ServiceFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

    @Test
    public void findPartPositiveTest() {
        Order order = new Order();
        List<Order> expectedOrderList = List.of(order);
        String typeOrder = "type";
        int shownRecords = 5;
        int recordsPerPage = 5;
        try {
            when(daoMock.findPart(typeOrder, shownRecords, recordsPerPage)).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findPart(typeOrder, shownRecords, recordsPerPage);
            assertEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findPartNegativeTest() {
        Order order = new Order();
        List<Order> expectedOrderList = Collections.emptyList();
        String typeOrder = "type";
        int shownRecords = 5;
        int recordsPerPage = 5;
        try {
            when(daoMock.findPart(typeOrder, shownRecords, recordsPerPage)).thenReturn(List.of(order));
            List<Order> actualOrderList = orderService.findPart(typeOrder, shownRecords, recordsPerPage);
            assertNotEquals(actualOrderList, expectedOrderList);
        } catch (DaoException | ServiceException e) {
            fail();
        }
    }

    @Test
    public void findPartExceptionTest() {
        String typeOrder = "type";
        int shownRecords = 5;
        int recordsPerPage = 5;
        try {
            when(daoMock.findPart(typeOrder, shownRecords, recordsPerPage)).thenThrow(new DaoException());
            assertThrows(ServiceException.class, () -> orderService.findPart(typeOrder, shownRecords, recordsPerPage));
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
    }

    @AfterClass
    public void tierDown() {
        daoMock = null;
        orderService = null;
    }
}
