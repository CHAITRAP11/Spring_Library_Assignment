package com.example.spring_boot_library.api;

import com.example.spring_boot_library.dto.OrderDto;
import com.example.spring_boot_library.model.Book;
import com.example.spring_boot_library.model.Order;
import com.example.spring_boot_library.model.User;
import com.example.spring_boot_library.service.BookService;
import com.example.spring_boot_library.service.OrderService;
import com.example.spring_boot_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    private static final Logger logger = Logger.getLogger(HomeController.class.getName());

    @PostMapping("orders/{user_id}/{book_id}")
    public ResponseEntity<String> orderBookByUser(@PathVariable("user_id") Long userId, @PathVariable("book_id") Long bookId) throws Exception {
        Order order;
        try {
            User user = userService.getUserById(userId);
            Book book = bookService.getBookById(bookId);

            String canUserOrderBook = userService.canOrderBook(user, book);
            if (canUserOrderBook != "ok") {
                throw new RuntimeException(canUserOrderBook);
            }

            String isBookAvailable = bookService.isBookAvailable(book);
            if (isBookAvailable != "ok") {
                throw new RuntimeException(isBookAvailable);
            }
            order = orderService.orderBook(user, book);
        }catch (Exception ex){
            logger.log(Level.SEVERE ,"Invalid Request: "+ex.getMessage());
            return new ResponseEntity<>("Invalid Request: "+ex.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    @PutMapping("return/{orderId}")
    public ResponseEntity<String> returnBookByUser(@PathVariable Long orderId) throws Exception {
        try {
            orderService.returnBook(orderId);
        }catch (Exception ex){
            logger.log(Level.SEVERE ,"Invalid Request: "+ex.getMessage());
            return new ResponseEntity<>("Invalid Request: "+ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Book Returned Successfully",HttpStatus.OK);
    }

    @PutMapping("return")
    public ResponseEntity<String> returnBookByUser(@RequestBody List<Long> orderIds) throws Exception {
        try {
            for (Long orderId : orderIds) {
                orderService.returnBook(orderId);
            }
        }catch (Exception ex){
            logger.log(Level.SEVERE ,"Invalid Request: "+ex.getMessage());
            return new ResponseEntity<>("Invalid Request: "+ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Book Returned Successfully",HttpStatus.OK);
    }
}
