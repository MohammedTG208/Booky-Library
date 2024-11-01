# Booky API

Booky is a simple API that allows users to manage their book collections and reading lists. This API enables user registration, adding books via ISBN, and organizing books into reading lists.

## Features

- User registration
- Add books using ISBN
- View all books added to the user's library
- Create and manage reading lists
- Add specific books to reading lists

## API Documentation

The detailed API documentation is provided below:

### Base URL
http://localhost:8080/api/v1


### User Endpoints
- **POST** `/user/register`: Register a new user.

### Book Endpoints
- **POST** `/book/add-book/{isbn}`: Add a new book to the user's library.
- **GET** `/book/get-all-user-books`: Retrieve a list of all books added by the user.

### Reading List Endpoints
- **POST** `/reading-list/add-reading-list`: Create a new reading list.
- **POST** `/reading-list/add-book-to-list`: Add a specific book to a reading list.

## User Stories

1. Users can add books by ISBN and automatically fetch book details.
2. Users can view all added books with pagination.
3. Users can create and manage reading lists.
4. Users can add books to reading lists and view them.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/Mohammedtg208/booky-Library.git
    cd booky-api
    ```

2. Ensure you have Docker installed and running.
   You can easily pull the Docker image using the following command:
   ```bash
    docker pull mohammedtg208/booky:latest
    ```

4. Build and run the application.

5. Use a tool like Postman or curl to test the API endpoints.
