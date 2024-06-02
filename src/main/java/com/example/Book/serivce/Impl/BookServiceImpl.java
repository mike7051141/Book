package com.example.Book.serivce.Impl;

import com.example.Book.dto.BookDto;
import com.example.Book.entity.Book;
import com.example.Book.repository.BookRepository;
import com.example.Book.serivce.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ArrayList<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        return new ArrayList<>(books);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book save(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setGenre(bookDto.getGenre());
        book.setImage(bookDto.getImage().startsWith("/images/") ? bookDto.getImage() : "/images/" + bookDto.getImage());
        book.setPublicationDate(LocalDate.parse(bookDto.getPublicationDate()));
        return bookRepository.save(book);
    }

    @Override
    public Book update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + bookDto.getId()));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        book.setGenre(bookDto.getGenre());
        book.setImage(bookDto.getImage().startsWith("/images/") ? bookDto.getImage() : "/images/" + bookDto.getImage());
        book.setPublicationDate(LocalDate.parse(bookDto.getPublicationDate()));
        return bookRepository.save(book);
    }
}