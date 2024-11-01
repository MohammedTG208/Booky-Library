package com.example.flightprices.booky.Repository;

import com.example.flightprices.booky.Model.Book;
import com.example.flightprices.booky.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookByIsbnAndUser(String isbn, User user);
    List<Book> findBookByUser(User user);
    Book findBookById(Integer id);
}
