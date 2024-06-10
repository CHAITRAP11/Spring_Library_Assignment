package com.example.spring_boot_library.controller;


import com.example.spring_boot_library.api.HomeController;
import com.example.spring_boot_library.dto.OrderDto;
import com.example.spring_boot_library.model.*;
import com.example.spring_boot_library.service.BookService;
import com.example.spring_boot_library.service.OrderService;
import com.example.spring_boot_library.service.UserService;
//import org.aspectj.lang.annotation.Before;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class HomeControllerTest {
    @InjectMocks
    private HomeController homeController;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @Mock
    private OrderService orderService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testOrderBookByUser() throws Exception {

        User user = new User(1L, "John Doe",null,MemberShipCategory.GOLD);
        Book book = new Book(1L, "Book Title", BookCategory.BOOK, BookGenreCategory.BUSINESS,1L);

        when(userService.getUserById(1L)).thenReturn(user);
        when(bookService.getBookById(1L)).thenReturn(book);

        when(userService.canOrderBook(user, book)).thenReturn("ok");
        when(bookService.isBookAvailable(book)).thenReturn("ok");


        Order order = new Order(1L, user, book, LocalDateTime.now(), null, null);
        when(orderService.orderBook(user, book)).thenReturn(order);


        ResponseEntity<String> response = homeController.orderBookByUser(1L, 1L);

        assertEquals( HttpStatus.CREATED, response.getStatusCode());
        assertEquals("success", response.getBody());
    }

    @Test
    public void testOrderBookByUser_InvalidAgeRequest() throws Exception {

        User user = new User(1L, "John Doe", LocalDate.of(2019,6,9),MemberShipCategory.GOLD);
        Book book = new Book(1L, "Book Title", BookCategory.BOOK, BookGenreCategory.CRIMEGENRE,1L);

        when(userService.getUserById(1L)).thenReturn(user);
        when(bookService.getBookById(1L)).thenReturn(book);

        when(userService.canOrderBook(user, book)).thenReturn("User is below 18");
        when(bookService.isBookAvailable(book)).thenReturn("ok");

        Order order = new Order(1L, user, book, LocalDateTime.now(), null, null);
        when(orderService.orderBook(user, book)).thenReturn(order);

        ResponseEntity<String> response = homeController.orderBookByUser(1L, 1L);

        assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Invalid Request: User is below 18",response.getBody());
    }

    @Test
    public void testOrderBookByUser_InvalidBookRequest() throws Exception {

        User user = new User(1L, "John Doe", null,MemberShipCategory.SILVER);
        Book book = new Book(1L, "Book Title", BookCategory.MAGAZINE, BookGenreCategory.CRIMEGENRE,0L);

        when(userService.getUserById(1L)).thenReturn(user);
        when(bookService.getBookById(1L)).thenReturn(book);

        when(userService.canOrderBook(user, book)).thenReturn("ok");
        when(bookService.isBookAvailable(book)).thenReturn("Book is not available");

        Order order = new Order(1L, user, book, LocalDateTime.now(), null, null);
        when(orderService.orderBook(user, book)).thenReturn(order);

        ResponseEntity<String> response = homeController.orderBookByUser(1L, 1L);


        assertEquals( HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Invalid Request: Book is not available",response.getBody());
    }

    @Test
    public void testReturnBookByUser() throws Exception {
        // Mock order service
        when(orderService.returnBook(1L)).thenReturn(null);

        // Call the method under test
        ResponseEntity<String> response = homeController.returnBookByUser(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book Returned Successfully", response.getBody());
    }

    @Test
    public void testReturnBookByUser_Failure() throws Exception {
        // Arrange
        Long orderId = 1L;
        Exception exception = new Exception("Invalid order ID");
        doThrow(exception).when(orderService).returnBook(orderId);

        // Act
        ResponseEntity<String> response = homeController.returnBookByUser(orderId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid Request: Invalid order ID", response.getBody());
        verify(orderService).returnBook(orderId);
    }


}