package com.example.flightprices.booky.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "must add name to the list")
    @Column(columnDefinition = "Varchar(255) not null")
    private String name;

    //Each reading list belongs to one user
    @ManyToOne
    @JsonIgnore
    private User user;

    //A reading list can contain multiple books and a book can be in multiple reading lists
    @ManyToMany
    private Set<Book> books;
}
