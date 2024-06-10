package com.example.spring_boot_library.dto;


import com.example.spring_boot_library.model.Book;
import com.example.spring_boot_library.model.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;


public class OrderDto {

    private Long orderId;

    private User user;

    private Book book;

    private LocalDateTime orderOn;

    private LocalDateTime returnOn;

    private LocalDateTime updatedAt;

    public OrderDto() {
    }

    public OrderDto(Long orderId, User user, Book book, LocalDateTime orderOn, LocalDateTime returnOn, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.user = user;
        this.book = book;
        this.orderOn = orderOn;
        this.returnOn = returnOn;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getOrderOn() {
        return orderOn;
    }

    public void setOrderOn(LocalDateTime orderOn) {
        this.orderOn = orderOn;
    }

    public LocalDateTime getReturnOn() {
        return returnOn;
    }

    public void setReturnOn(LocalDateTime returnOn) {
        this.returnOn = returnOn;
    }

}

