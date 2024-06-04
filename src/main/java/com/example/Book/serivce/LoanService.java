package com.example.Book.serivce;

import com.example.Book.dto.BookDto;
import com.example.Book.dto.LoanDto;

import java.util.List;

public interface LoanService {
    void addLoan(LoanDto loanDto);

    List<BookDto> getAvailableBooks();

    List<LoanDto> getLoans();

    List<LoanDto> getLoansByBookId(Long bookId);

    LoanDto getLoanById(Long id);

    void updateLoan(LoanDto loanDto);
}
