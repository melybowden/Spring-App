package com.cygnus.books;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class BooksController {
    @Autowired
    BooksRepository repository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/")
    public String getRoot() {
        return "We are at the root";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/books") // CREATE FROM LIST OF JSON OBJECTS
    public List<String> receiveBookList(@RequestBody List<BookClass> bookList) {
        List<String> booksAdded = new ArrayList<String>();
        bookList.forEach((book) -> {
            this.receiveBook(book);
            booksAdded.add("Added: " + book.getTitle());
        });
        return booksAdded;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/book") // CREATE
    public String receiveBook(@RequestBody BookClass book) {
        repository.save(book);
        return "Book title: " + book.getTitle();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/book") // LIST
    public List<BookClass> getAllBooks() {
        return repository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/book/{id}") // READ ONE
    public BookClass getBookByID(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/book/{id}") // UPDATE
    public BookClass updateBook(@PathVariable Long id, @RequestBody BookClass book) {
        // BookClass bookToUpdate = repository.findById(id).orElse(null);
        // if (bookToUpdate == null) {
        //     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        // }
        // bookToUpdate = new BookClass(book, id);
        // return repository.save(bookToUpdate);
        if (repository.existsById(id)) {
            return repository.save(new BookClass(book, id));
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/book/{id}") // DELETE
    public void deleteBook(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
