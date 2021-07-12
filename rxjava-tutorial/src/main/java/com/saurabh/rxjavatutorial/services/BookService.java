package com.saurabh.rxjavatutorial.services;

import com.saurabh.rxjavatutorial.models.entity.Book;
import com.saurabh.rxjavatutorial.models.request.AddBookRequest;
import com.saurabh.rxjavatutorial.models.request.UpdateBookRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface BookService {

    Single<Book> addBooks(AddBookRequest book);

    Completable updateBook(String id, UpdateBookRequest book);

    Single< List<Book>> getBooks(int limit, int page);

    Single<Book> getBookById(int id);

    Completable deleteBookById(int id);
}
