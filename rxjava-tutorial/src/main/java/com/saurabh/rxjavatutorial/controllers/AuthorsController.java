package com.saurabh.rxjavatutorial.controllers;

import com.saurabh.rxjavatutorial.models.entity.Author;
import com.saurabh.rxjavatutorial.models.request.AddAuthorRequest;
import com.saurabh.rxjavatutorial.models.response.BaseWebResponse;
import io.reactivex.rxjava3.core.Single;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorsController {

    @PostMapping
    Single<ResponseEntity<BaseWebResponse<Author>>> addAuthor(@RequestBody AddAuthorRequest author);
}
