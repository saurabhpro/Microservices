package com.saurabh.rxjavatutorial.services;

import com.saurabh.rxjavatutorial.models.entity.Author;
import com.saurabh.rxjavatutorial.models.entity.Book;
import com.saurabh.rxjavatutorial.models.request.AddBookRequest;
import com.saurabh.rxjavatutorial.models.request.UpdateBookRequest;
import com.saurabh.rxjavatutorial.repositories.AuthorRepository;
import com.saurabh.rxjavatutorial.repositories.BookRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Single<Book> addBooks(AddBookRequest book) {
        return null;
    }

    @Override
    public Completable updateBook(String id, UpdateBookRequest book) {
        return null;
    }

    private Single<String> saveBookToRepository(AddBookRequest addBookRequest) {
        return Single.create(singleSubscriber -> {
            Optional<Author> optionalAuthor = authorRepository.findById(addBookRequest.getAuthorId());
            if (optionalAuthor.isEmpty())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                String addedBookId = bookRepository.save(toBook(addBookRequest)).getId();
                singleSubscriber.onSuccess(addedBookId);
            }
        });
    }

    private Book toBook(AddBookRequest addBookRequest) {
        final Book book = new Book();
        BeanUtils.copyProperties(addBookRequest, book);

        book.setId(UUID.randomUUID().toString());
        book.setAuthor(Author.builder()
            .id(addBookRequest.getAuthorId())
            .build());
        return book;
    }

    @Override
    public Single<List<Book>> getBooks(int limit, int page) {
        return null;
    }

    @Override
    public Single<Book> getBookById(int id) {
        return null;
    }

    @Override
    public Completable deleteBookById(int id) {

        return null;
    }
}
