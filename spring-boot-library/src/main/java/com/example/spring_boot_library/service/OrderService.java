package com.example.spring_boot_library.service;

import com.example.spring_boot_library.model.Book;
import com.example.spring_boot_library.model.Order;
import com.example.spring_boot_library.model.User;
import com.example.spring_boot_library.repository.BookRepository;
import com.example.spring_boot_library.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OrderService {

    private final UserService userService;
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final BookService bookService;

    public OrderService(UserService userService, BookRepository bookRepository, OrderRepository orderRepository, BookService bookService) {
        this.userService = userService;
        this.bookRepository = bookRepository;
        this.orderRepository = orderRepository;
        this.bookService = bookService;
    }

    public Order orderBook(User user, Book book) throws Exception {
        Order order = new Order();
        order.setBook(book);
        order.setUser(user);
        order.setOrderOn(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        book.setBookQuantity(book.getBookQuantity()-1);
        orderRepository.save(order);
        return order;
    }


    public Order returnBook(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new Exception("User Not Found"));
        Book book = order.getBook();
        order.setReturnOn(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        book.setBookQuantity(book.getBookQuantity() + 1);
        orderRepository.save(order);
        return order;
    }
}
