package com.example.spring_boot_library.service;

import com.example.spring_boot_library.model.*;
import com.example.spring_boot_library.repository.OrderRepository;
import com.example.spring_boot_library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public User getUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User Not Found"));
    }

    public String canOrderBook(User user, Book book) {
        if(isMembershipLimitExceeded(user,book))
            return "User cannot borrow book";
        if(!isValidAgeForBook(user, book))
            return "User is below 18";
        if(isMonthlyLimitExceeded(user, book))
            return "Transaction limit is ended!";
        return "ok";
    }


    public boolean isMembershipLimitExceeded(User user, Book book){
        MemberShipCategory membership = user.getMemberShip();

        List<Order> orderList = orderRepository.findByUser_UserId(user.getUserId());

        long currentBooksCount = orderList.stream()
                .filter(b -> b.getBook().getType() == BookCategory.BOOK)
                .count();
        long currentMagazinesCount = orderList.stream()
                .filter(b -> b.getBook().getType() == BookCategory.MAGAZINE)
                .count();

        if (book.getType() == BookCategory.BOOK) {
            return !(currentBooksCount < membership.getMaxBooks());
        } else if (book.getType() == BookCategory.MAGAZINE) {
            return !(currentMagazinesCount < membership.getMaxMagazines());
        }
        return true;
    }

    public boolean isValidAgeForBook(User user, Book book){
        Period period = Period.between(user.getDateOfBirth(), LocalDate.now());
        if(period.getYears()<18){
            if(book.getBookGenreCategory() == BookGenreCategory.CRIMEGENRE)
                return false;
        }
        return true;
    }

    public boolean isMonthlyLimitExceeded(User user, Book book){
        List<Order> orderList = orderRepository.findByUser_UserId(user.getUserId());
        long monthlyTransactionCount = orderList.stream()
                .filter(order -> (order.getReturnOn() != null && order.getReturnOn().getMonth() == LocalDateTime.now().getMonth())
                        || (order.getReturnOn() != null && order.getOrderOn().getMonth() == LocalDateTime.now().getMonth()))
                .count();
        if(monthlyTransactionCount < 10)
            return false;
        return true;
    }
}
