package com.example.Book.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoanDto {
    private Long id;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private String status;
    private String borrowerName;
    private String borrowerPhoneNumber;
    private Long bookId;
}
