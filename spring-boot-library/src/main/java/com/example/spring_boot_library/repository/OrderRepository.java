package com.example.spring_boot_library.repository;

import com.example.spring_boot_library.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "book.bookId")
    public Optional<Order> findByBook_BookId(Long bookId);

    @EntityGraph(attributePaths = "user.userId")
    public List<Order> findByUser_UserId(Long userId);
}
