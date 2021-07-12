package com.saurabh.rxjavatutorial.services;

import com.saurabh.rxjavatutorial.models.entity.Author;
import com.saurabh.rxjavatutorial.models.request.AddAuthorRequest;
import io.reactivex.rxjava3.core.Single;

public interface AuthorService {
    Single<Author> addAuthor(AddAuthorRequest author);
}
