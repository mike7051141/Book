package com.example.Book.repository;

import com.example.Book.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findFirstByBookIdOrderByIdDesc(Long bookId);
}
