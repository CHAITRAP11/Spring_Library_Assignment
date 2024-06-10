package com.example.spring_boot_library.service;

import com.example.spring_boot_library.model.Book;
import com.example.spring_boot_library.model.User;
import com.example.spring_boot_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(Long bookId) throws Exception {
        return bookRepository.findById(bookId).orElseThrow(()-> new Exception("Book Not Found"));
    }

    public String isBookAvailable(Book book) {
        if(book.getBookQuantity() == 0)
            return "Book is not available";
        return "ok";
    }
}
