package com.saurabh.rxjavatutorial.controllers;

import com.saurabh.rxjavatutorial.models.entity.Book;
import com.saurabh.rxjavatutorial.models.request.AddBookRequest;
import com.saurabh.rxjavatutorial.models.request.UpdateBookRequest;
import com.saurabh.rxjavatutorial.models.response.BaseWebResponse;
import com.saurabh.rxjavatutorial.models.response.BookResponse;
import com.saurabh.rxjavatutorial.services.BookService;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BooksControllerImpl implements BooksController {

    private final BookService bookService;

    public BooksControllerImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Single<ResponseEntity<BaseWebResponse<Book>>> addBook(@RequestBody AddBookRequest book) {
        return bookService.addBooks(book)
                .subscribeOn(Schedulers.io())
                .map(s -> ResponseEntity
                        .created(URI.create("/api/v1/books/" + s.getId()))
                        .body(BaseWebResponse.successWithData(s)));
    }


    @PutMapping("/{id}")
    public @NonNull Single<ResponseEntity<BaseWebResponse<?>>> updateBook(@PathVariable String id,
                                                                          @RequestBody UpdateBookRequest book) {
        return bookService.updateBook(id, book)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebResponse.successNoData()));
    }

    @Override
    @GetMapping
    public Single<ResponseEntity<BaseWebResponse<List<BookResponse>>>> getBooks(@RequestParam(defaultValue = "5") int limit,
                                                                                @RequestParam(defaultValue = "0") int page) {
        return bookService.getBooks(limit, page)
                .subscribeOn(Schedulers.io())
                .map(books -> ResponseEntity.ok(BaseWebResponse.successWithData(toBookWebResponseList(books))));
    }

    private List<BookResponse> toBookWebResponseList(List<Book> bookList) {
        return bookList
                .stream()
                .map(BookResponse.BookResponseTransformer::from)
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public Single<ResponseEntity<BaseWebResponse<Book>>> getBookDetail(@PathVariable int id) {
        return bookService.getBookById(id)
                .subscribeOn(Schedulers.io())
                .map(bookResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(bookResponse)));
    }

    @Override
    @DeleteMapping("/{id}")
    public Single<ResponseEntity<Object>> deleteBook(@PathVariable int id) {
        return bookService.deleteBookById(id)
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.noContent().build());
    }
}
