package com.example.Book.serivce;

import com.example.Book.dto.ReviewDto;
import com.example.Book.entity.Book;
import com.example.Book.entity.Review;
import org.apache.catalina.User;

import java.util.List;

public interface ReviewService {

    Review saveReview(ReviewDto reviewdto);

    List<Review> allReview();

    List<Review> findAllByBookID(Book book);
}
