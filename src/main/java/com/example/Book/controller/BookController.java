package com.example.Book.controller;

import com.example.Book.dto.BookDto;
import com.example.Book.entity.Book;
import com.example.Book.serivce.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "addBook";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.save(bookDto);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookList";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam("searchType") String searchType,
                              @RequestParam("keyword") String keyword, Model model) {
        List<Book> books;
        if (searchType.equals("title")) {
            books = bookService.findByTitle(keyword);
        } else if (searchType.equals("author")) {
            books = bookService.findByAuthor(keyword);
        } else {
            books = bookService.findAll();
        }
        model.addAttribute("books", books);
        return "searchResults";
    }
}