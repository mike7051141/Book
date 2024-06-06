package com.example.Book.dto;

import com.example.Book.entity.Book;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private String userName;
    private String content;
    private Book bookID;
    private String rate;
}
