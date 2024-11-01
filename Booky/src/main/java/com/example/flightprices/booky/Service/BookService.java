package com.example.flightprices.booky.Service;

import com.example.flightprices.booky.Api.ApiException;
import com.example.flightprices.booky.Model.Book;
import com.example.flightprices.booky.Model.User;
import com.example.flightprices.booky.Repository.BookRepository;
import com.example.flightprices.booky.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookService {
   private final BookRepository bookRepository;
    private final UserRepository userRepository;
    //this for integrate with open book Api
    private final RestTemplate restTemplate;

    public Book fetchBookByIsbn(String isbn, User user) {
        User check=userRepository.findUserByEmail(user.getEmail());
        Book checkContainTheSameUser=bookRepository.findBookByIsbnAndUser(isbn,check);
        if (checkContainTheSameUser!=null){
            throw new ApiException("You add this book before try another book");
        }
        String url = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";
        ResponseEntity<Map> response;

        try {
            response = restTemplate.getForEntity(url, Map.class);
        } catch (Exception e) {
            throw new ApiException("Error fetching data from OpenLibrary: " + e.getMessage());
        }

        // Parse and save the book
        Map<String, Object> bookData = (Map<String, Object>) response.getBody().get("ISBN:" + isbn);

        if (bookData == null) {
            throw new ApiException("Book not found. Try another ISBN.");
        }

        User checkUser = userRepository.findUserByEmail(user.getEmail());
        if (checkUser == null) {
            throw new ApiException("User not found. Please register or log in.");
        }

        Book book = new Book();
        book.setIsbn(isbn);
        book.setUser(checkUser);
        book.setTitle((String) bookData.get("title"));

        // Extract authors
        if (bookData.containsKey("authors")) {
            List<Map<String, String>> authors = (List<Map<String, String>>) bookData.get("authors");
            if (!authors.isEmpty()) {
                book.setAuthor(authors.get(0).get("name")); // Taking the first author for simplicity
            }
        } else {
            throw new ApiException("No authors found for this book.");
        }

        // Extract number of pages
        if (bookData.containsKey("number_of_pages")) {
            book.setPageNumbers((Integer) bookData.get("number_of_pages"));
        } else {
            throw new ApiException("Number of pages not found.");
        }

        // Extract cover image URL
        if (bookData.containsKey("cover")) {
            Map<String, String> cover = (Map<String, String>) bookData.get("cover");
            if (cover.containsKey("large")) {
                book.setImageUrl(cover.get("large")); // or cover.get("medium") for a smaller image
            } else {
                throw new ApiException("Cover image not found.");
            }
        } else {
            throw new ApiException("Cover information not available.");
        }

        return bookRepository.save(book); // Save the book to the repository
    }
    //As a user, I want to add a book to the system by its ISBN
    //Add a Book by ISBN integrate with another APIs
    //As a user, I want to be able to view all my added books
    public List<Book> getAllUserBooks(User user) {
        User checkUser=userRepository.findUserByEmail(user.getEmail());
        if (checkUser==null){
             throw new ApiException("try register or login ");
        }
        List<Book> books=bookRepository.findBookByUser(checkUser);
        if (books.isEmpty()) {
            throw new ApiException("sorry there is not any books try add one");
        }

        return books;
    }
}
