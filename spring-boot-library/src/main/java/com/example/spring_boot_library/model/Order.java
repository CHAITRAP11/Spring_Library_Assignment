package com.example.spring_boot_library.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERLIST")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private LocalDateTime orderOn;

    private LocalDateTime returnOn;

    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long orderId, User user, Book book, LocalDateTime orderOn, LocalDateTime returnOn, LocalDateTime updatedAt) {
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
