package com.example.spring_boot_library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;

    @Column(name = "book_category")
    @Enumerated(EnumType.STRING)
    private BookCategory type;

    @Enumerated(EnumType.STRING)
    private BookGenreCategory bookGenreCategory;

    private Long bookQuantity;

    public Book() {
    }

    public Book(Long bookId, String bookName, BookCategory type, BookGenreCategory bookGenreCategory, Long bookQuantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.type = type;
        this.bookGenreCategory = bookGenreCategory;
        this.bookQuantity = bookQuantity;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return bookName;
    }

    public void setName(String bookName) {
        this.bookName = bookName;
    }

    public BookCategory getType() {
        return type;
    }

    public void setType(BookCategory type) {
        this.type = type;
    }

    public BookGenreCategory getBookGenreCategory() {
        return bookGenreCategory;
    }

    public void setBookGenreCategory(BookGenreCategory bookGenreCategory) {
        this.bookGenreCategory = bookGenreCategory;
    }

    public Long getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(Long bookQuantity) {
        this.bookQuantity = bookQuantity;
    }


}
