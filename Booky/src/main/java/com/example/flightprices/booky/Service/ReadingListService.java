package com.example.flightprices.booky.Service;

import com.example.flightprices.booky.Api.ApiException;
import com.example.flightprices.booky.Model.Book;
import com.example.flightprices.booky.Model.ReadingList;
import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Repository.BookRepository;
import com.example.flightprices.booky.Repository.ReadingListRepository;
import com.example.flightprices.booky.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingListService {
private final ReadingListRepository readingListRepository;
private final BookRepository bookRepository;
    private final UserRepository userRepository;

    // As a user, I can create a reading list
    public void addNewReadingList(ReadingList readingList, User user) {
        readingList.setUser(user);
        readingListRepository.save(readingList);
    }

    //As a user, I can add a book to a reading list
    public void addBookToReadingList(User user, Integer bookId, Integer readingListId) {
        ReadingList readingList = readingListRepository.findReadingListById(readingListId);
        Book getBook=bookRepository.findBookById(bookId);
        User checkUser=userRepository.findUserByEmail(user.getEmail());

       if (readingList==null){
           throw new ApiException("this list not found");
       }

        if (getBook == null || !getBook.getUser().getId().equals(checkUser.getId())) {
            throw new ApiException("This book not found or does not belong to the user");
        }

        if (!readingList.getBooks().contains(getBook)) {
            readingList.getBooks().add(getBook);
            getBook.getReadingList().add(readingList);
            readingListRepository.save(readingList);
            bookRepository.save(getBook);
        }
    }

    // As a user, I can view the books under a specific reading list
    public ReadingList getReadingListById(User user, Integer readingListId) {
        ReadingList readingList = readingListRepository.findReadingListById(readingListId);
        User checkUser=userRepository.findUserByEmail(user.getEmail());

        if (readingList==null||!readingList.getUser().getId().equals(checkUser.getId())){
            throw new ApiException("this list not found or does not belong to you");
        }

        return readingList;
    }
}
