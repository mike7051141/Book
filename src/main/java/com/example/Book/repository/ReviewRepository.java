package com.example.Book.repository;

import com.example.Book.entity.Book;
import com.example.Book.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Override
    List<Review> findAllById(Iterable<Long> longs);

    List<Review> findAllByBook(Book book);
}
