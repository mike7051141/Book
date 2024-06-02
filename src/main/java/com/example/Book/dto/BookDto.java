package com.example.Book.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private long id;
    private String title;
    private String author;
    private String publisher;
    private String genre;
    private String image;
    private String publicationDate;
    private String status;

}