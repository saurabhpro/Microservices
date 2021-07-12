package com.saurabh.rxjavatutorial.controllers;

import com.saurabh.rxjavatutorial.models.entity.Book;
import com.saurabh.rxjavatutorial.models.request.AddBookRequest;
import com.saurabh.rxjavatutorial.models.request.UpdateBookRequest;
import com.saurabh.rxjavatutorial.models.response.BaseWebResponse;
import com.saurabh.rxjavatutorial.models.response.BookResponse;
import io.reactivex.rxjava3.core.Single;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BooksController {

    Single<ResponseEntity<BaseWebResponse<Book>>> addBook(AddBookRequest book);

    Single<ResponseEntity<BaseWebResponse<?>>> updateBook(String bookId, UpdateBookRequest book);

    Single<ResponseEntity<BaseWebResponse<List<BookResponse>>>> getBooks(int limit, int page);

    Single<ResponseEntity<BaseWebResponse<Book>>> getBookDetail(int id);

    Single<ResponseEntity<Object>> deleteBook(int id);
}
