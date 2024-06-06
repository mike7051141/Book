package com.example.Book.serivce.Impl;

import com.example.Book.dto.BookDto;
import com.example.Book.dto.LoanDto;
import com.example.Book.entity.Book;
import com.example.Book.entity.Loan;
import com.example.Book.entity.Status;
import com.example.Book.repository.BookRepository;
import com.example.Book.repository.LoanRepository;
import com.example.Book.serivce.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    @Override
    public void addLoan(LoanDto loanDto) {
        if(!(checkBookRented(loanDto.getBookId()))) {
            System.out.println("not rented");
            Book book = bookRepository.findById(loanDto.getBookId()).orElse(null);
            if(book != null) {
                System.out.println("book exists");
                Loan loan = Loan.builder()
                        .borrowerName(loanDto.getBorrowerName())
                        .borrowerPhoneNumber(loanDto.getBorrowerPhoneNumber())
                        .status(Status.RENTED)
                        .loanDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                        .book(book)
                        .build();
                loanRepository.save(loan);
            } else {
                System.out.println("book not exists");
            }
        } else {
            System.out.println("rented");
        }
    }

    @Override
    public List<BookDto> getAvailableBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            return Collections.emptyList();
        }

        List<BookDto> loanBooks = books.stream().map(book -> {
            BookDto dto = BookDto.builder()
                    .id(book.getId())
                    .author(book.getAuthor())
                    .title(book.getTitle())
                    .image(book.getImage())
                    .status("")
                    .build();
            Loan loan = loanRepository.findFirstByBookIdOrderByIdDesc(book.getId()).orElse(null);
            if(loan == null) {
                dto.setStatus("AVAILABLE");
            } else {
                dto.setStatus(String.valueOf(loan.getStatus()));
            }
            return dto;
        }).filter(bookDto -> bookDto.getStatus().equals("AVAILABLE")).collect(Collectors.toList());
        return loanBooks;
    }

    @Override
    public List<LoanDto> getLoans() {
        List<Loan> loans = loanRepository.findAll();
        if(loans.isEmpty()) {
            return Collections.emptyList();
        }
        List<LoanDto> list = loans.stream().map(loan -> {
            LoanDto loanDto = LoanDto.builder()
                    .id(loan.getId())
                    .loanDate(loan.getLoanDate())
                    .returnDate(loan.getReturnDate())
                    .borrowerPhoneNumber(loan.getBorrowerPhoneNumber())
                    .borrowerName(loan.getBorrowerName())
                    .status(String.valueOf(loan.getStatus()))
                    .bookId(loan.getBook().getId())
                    .build();
            return loanDto;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<LoanDto> getLoansByBookId(Long bookId) {
        List<Loan> loans = loanRepository.findAll();
        if(loans.isEmpty()) {
            return Collections.emptyList();
        }
        List<LoanDto> list = loans.stream().map(loan -> {
            LoanDto loanDto = LoanDto.builder()
                    .id(loan.getId())
                    .loanDate(loan.getLoanDate())
                    .returnDate(loan.getReturnDate())
                    .borrowerPhoneNumber(loan.getBorrowerPhoneNumber())
                    .borrowerName(loan.getBorrowerName())
                    .status(String.valueOf(loan.getStatus()))
                    .bookId(loan.getBook().getId())
                    .build();
            return loanDto;
        }).filter(loanDto -> loanDto.getBookId() == bookId).collect(Collectors.toList());
        return list;
    }

    @Override
    public LoanDto getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElse(new Loan());
        LoanDto loanDto = LoanDto.builder()
                .id(loan.getId())
                .loanDate(loan.getLoanDate())
                .returnDate(loan.getReturnDate())
                .borrowerPhoneNumber(loan.getBorrowerPhoneNumber())
                .borrowerName(loan.getBorrowerName())
                .status(String.valueOf(loan.getStatus()))
                .bookId(loan.getBook().getId())
                .build();
        return loanDto;
    }

    @Override
    public void updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findById(loanDto.getId()).orElse(null);
        loan.setBorrowerName(loanDto.getBorrowerName());
        loan.setBorrowerPhoneNumber(loanDto.getBorrowerPhoneNumber());
        loan.setReturnDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        loan.setLoanDate(loanDto.getLoanDate());
        loan.setStatus(Status.valueOf(loanDto.getStatus()));
        loanRepository.save(loan);
    }

    private boolean checkBookRented(Long bookId) {
        Loan loan = loanRepository.findFirstByBookIdOrderByIdDesc(bookId).orElse(null);
        if(loan == null) {
            return false;
        }
        if(loan.getStatus() == Status.AVAILABLE) {
            return false;
        } else {
            return true;
        }
    }
}
