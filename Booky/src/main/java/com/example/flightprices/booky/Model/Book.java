package com.example.flightprices.booky.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title can not be null")
    @Column(columnDefinition = "Varchar(255) not null")
    private String title;

    @NotEmpty(message = "ISBN can not be null")
    @Column(columnDefinition = "Varchar(20) not null")
    private String isbn;

    @NotEmpty(message = "Author can not be null")
    @Column(columnDefinition = "Varchar(100) not null")
    private String author;

    @Positive(message = "Enter valid numbers of pages")
    @Column(columnDefinition = "int not null")
    private Integer pageNumbers;


    @Column(columnDefinition = "Varchar(255) not null")
    private String imageUrl;


    //A book can belong to the library and can be viewed by all users,
    // but info is user-generated on addition.
    @ManyToOne
    @JsonIgnore
    private User user;

    //A reading list can contain multiple books and a book can be in multiple reading lists
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "books")
    @JsonIgnore
    private Set<ReadingList> readingList;
}
