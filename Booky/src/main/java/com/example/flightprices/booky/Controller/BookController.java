package com.example.flightprices.booky.Controller;

import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/get-all-user-books")
    public ResponseEntity allUserBooks(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(bookService.getAllUserBooks(user));
    }

    @PostMapping("/add-book/{snbi}")
    public ResponseEntity addBook(@AuthenticationPrincipal User user,@PathVariable String snbi){
        return ResponseEntity.status(200).body(bookService.fetchBookByIsbn(snbi,user));
    }
}
