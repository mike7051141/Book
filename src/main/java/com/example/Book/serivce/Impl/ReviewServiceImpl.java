package com.example.Book.serivce.Impl;

import com.example.Book.dto.ReviewDto;
import com.example.Book.entity.Book;
import com.example.Book.entity.Review;
import com.example.Book.repository.ReviewRepository;
import com.example.Book.serivce.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    @Override
    public Review saveReview(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUserName(reviewDto.getUserName());
        review.setContent(reviewDto.getContent());
        review.setCreatedAt(LocalDateTime.now());
        review.setBook(reviewDto.getBookID());

        return repository.save(review);
    }

    public List<Review> allReview() {
        List<Review> reviews = repository.findAll();
        return reviews;
    }

    /*List<Review> findAllByBook(Book book);*/

    public List<Review> findAllByBookID(Book book) {
        return repository.findAllByBook(book);

    }

}
