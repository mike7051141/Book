package com.example.Book.serivce;

import com.example.Book.dto.BookDto;
import com.example.Book.entity.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    ArrayList<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    void delete(long id);
    Book save(BookDto bookDto);
}
