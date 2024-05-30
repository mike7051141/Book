package com.example.Book.entity;

import com.example.Book.entity.Book;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Book bookId;

    @Column
    private LocalDateTime loanDate;

    @Column
    private LocalDateTime returnDate;

    @Column
    private Status status;

    @Column
    private String borrowerName;

    @Column
    private String borrowerPhoneNumber;

}
