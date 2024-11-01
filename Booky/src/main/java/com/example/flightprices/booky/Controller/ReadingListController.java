package com.example.flightprices.booky.Controller;

import com.example.flightprices.booky.Model.ReadingList;
import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Service.ReadingListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reading-list")
@RequiredArgsConstructor
public class ReadingListController {
    private final ReadingListService readingListService;

    @GetMapping("/get/my-list/")
    public ResponseEntity userReadingList(@AuthenticationPrincipal User user, @RequestParam Integer readingListId) {
        return ResponseEntity.status(200).body(readingListService.getReadingListById(user, readingListId));
    }

    @PostMapping("/add-reading-list")
    public ResponseEntity addReadingList(@AuthenticationPrincipal User user, @Valid @RequestBody ReadingList readingList) {
        readingListService.addNewReadingList(readingList,user);
        return ResponseEntity.status(200).body("Added reading list successfully");
    }

    @PostMapping("/add-book-to-list/")
    public ResponseEntity addBookToReadingList(@AuthenticationPrincipal User user, @RequestParam Integer bookId, @RequestParam Integer readingListId) {
        readingListService.addBookToReadingList(user, bookId, readingListId);
        return ResponseEntity.status(200).body("Added book to reading list successfully");
    }

}
