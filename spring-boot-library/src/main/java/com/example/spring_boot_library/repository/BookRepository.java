package com.example.spring_boot_library.repository;

import com.example.spring_boot_library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
