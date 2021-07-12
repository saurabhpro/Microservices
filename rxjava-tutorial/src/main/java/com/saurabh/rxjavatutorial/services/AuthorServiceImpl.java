package com.saurabh.rxjavatutorial.services;

import com.saurabh.rxjavatutorial.models.entity.Author;
import com.saurabh.rxjavatutorial.models.request.AddAuthorRequest;
import io.reactivex.rxjava3.core.Single;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public Single<Author> addAuthor(AddAuthorRequest author) {
        return null;
    }
}
