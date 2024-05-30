package com.example.Book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long id;

    @Column(name = "REVIEW_USER_NAME")
    private String userName;

    @Column(name = "REVIEW_CONTENT")
    private String content;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
