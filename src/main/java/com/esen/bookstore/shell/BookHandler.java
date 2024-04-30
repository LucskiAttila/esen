package com.esen.bookstore.shell;

import com.esen.bookstore.model.Book;
import com.esen.bookstore.service.BookService;
import com.esen.bookstore.service.BookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.stream.Collectors;

@ShellComponent
@ShellCommandGroup("Book related commands")
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;
    private final BookstoreService bookstoreService;


    @ShellMethod(value = "Create a book", key = "create book")
    public void createBook(String title, String author, String publisher, Double price) {
        bookService.save(Book.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .price(price)
                .build());
    }

    @ShellMethod(value = "List books", key = "list books")
    public String listBooks() {
        return bookService.findAll()
                .stream()
                .map(book -> "ID: %d, Publisher: %s, Author: %s, Title: %s, Price %f Ft".formatted(
                        book.getId(),
                        book.getPublisher(),
                        book.getAuthor(),
                        book.getTitle(),
                        book.getPrice()
                )).collect(Collectors.joining(System.lineSeparator()));
    }

    @ShellMethod(value = "Delete books", key = "delete book")
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Update a book", key = "update book")
    public void updateBook(Long id,
                           @ShellOption(defaultValue = ShellOption.NULL) String title,
                           @ShellOption(defaultValue = ShellOption.NULL) String author,
                           @ShellOption(defaultValue = ShellOption.NULL) String publisher,
                           @ShellOption(defaultValue = ShellOption.NULL) Double price) {
        bookService.updateBook(id, title, author, publisher, price);
    }

    @ShellMethod(value = "Find prices", key = "Find prices")
    public String findPrices(Long id) {
        return bookstoreService.findPrice(id)
                .entrySet()
                .stream()
                .map(entry -> "Id: %d, Location: %s, Price: %s Ft".formatted(
                        entry.getKey().getId(),
                        entry.getKey().getLocation(),
                        entry.getValue()
                )).collect(Collectors.joining(System.lineSeparator()));
    }

}
