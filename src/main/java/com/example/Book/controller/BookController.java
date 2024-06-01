package com.example.Book.controller;

import com.example.Book.dto.BookDto;
import com.example.Book.entity.Book;
import com.example.Book.serivce.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.save(bookDto);
        return "redirect:/books";
    }

    @GetMapping
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

    @GetMapping("/delete/{idx}")
    public  String delete(@PathVariable int idx) {
        bookService.delete(idx);
        return "redirect:/books";
    }
}