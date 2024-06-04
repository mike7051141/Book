package com.example.Book.controller;

import com.example.Book.dto.LoanDto;
import com.example.Book.serivce.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/list")
    public String getBooks(Model model) {
        model.addAttribute("books", loanService.getAvailableBooks());
        return "availableBookList";
    }

    @GetMapping("/add/{book_id}")
    public String addLoan(@PathVariable(name = "book_id") Long bookId,
                          Model model) {
        model.addAttribute("bookId", bookId);
        return "addLoan";
    }

    @PostMapping("/add")
    public String addLoan(@ModelAttribute LoanDto loanDto) {
        loanService.addLoan(loanDto);
        return "redirect:/loans/history";
    }

    @GetMapping("/history")
    public String getLoans(Model model) {
        model.addAttribute("loans", loanService.getLoans());
        return "loanList";
    }

    @GetMapping("/history/{book_id}")
    public String getLoans(@PathVariable(name = "book_id") Long bookId,
                           Model model) {
        model.addAttribute("loans", loanService.getLoansByBookId(bookId));
        return "loanList";
    }

    @GetMapping("/update/{loan_id}")
    public String updateLoan(@PathVariable(name = "loan_id") Long loanId,
                             Model model) {
        model.addAttribute("loan", loanService.getLoanById(loanId));
        return "updateLoan";
    }

    @PostMapping("/update")
    public String updateLoan(@ModelAttribute LoanDto loanDto) {
        loanService.updateLoan(loanDto);
        return "redirect:/loans/history";
    }
}
