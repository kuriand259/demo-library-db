
package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BookController {

    private final BookRepository repository;
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Book> read() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Book read(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book updated) {
        return repository.findById(id)
            .map(book -> {
                book.setTitle(updated.getTitle());
                book.setAuthor(updated.getAuthor());
                book.setGenre(updated.getGenre());
                book.setPublishedYear(updated.getPublishedYear());
                return repository.save(book);
            })
            .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
