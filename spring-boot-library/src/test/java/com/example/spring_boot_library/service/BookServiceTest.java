package com.example.spring_boot_library.service;

import com.example.spring_boot_library.model.Book;
import com.example.spring_boot_library.model.BookCategory;
import com.example.spring_boot_library.model.BookGenreCategory;
import com.example.spring_boot_library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetBookById_BookNotFound() throws Exception{
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> bookService.getBookById(1L));
        assertEquals("Book Not Found", exception.getMessage());
    }

    @Test
    public void testIsBookAvailable_BookNotAvailable() {
        Book book = new Book(1L, "Book Title", BookCategory.BOOK, BookGenreCategory.BUSINESS,0L);

        String result = bookService.isBookAvailable(book);
        assertEquals("Book is not available", result);
    }
}
