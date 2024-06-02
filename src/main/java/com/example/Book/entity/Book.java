package com.example.Book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

<<<<<<< HEAD
import java.util.Date;
=======
import java.time.LocalDate;
>>>>>>> mingi_branch
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String publisher;

    @Column
    private String genre;

    @Column
    private String image;

    @Column
<<<<<<< HEAD
    private Status status;

    @OneToMany(mappedBy = "Book", fetch = FetchType.LAZY)
    private List<Loan> loans;

    @OneToMany(mappedBy = "Book", fetch = FetchType.LAZY)
    private List<Review> reviews;
=======
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status = Status.AVAILABLE;

//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//    private List<Loan> loans;
//
//    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
//    private List<Review> reviews;
>>>>>>> mingi_branch
}
