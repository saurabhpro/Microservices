package com.saurabh.rxjavatutorial.controllers;

import com.saurabh.rxjavatutorial.models.entity.Author;
import com.saurabh.rxjavatutorial.models.request.AddAuthorRequest;
import com.saurabh.rxjavatutorial.models.response.BaseWebResponse;
import com.saurabh.rxjavatutorial.services.AuthorService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.saurabh.rxjavatutorial.models.response.BaseWebResponse.successWithData;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorsControllerImpl implements AuthorsController {

    private final AuthorService authorService;

    public AuthorsControllerImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @PostMapping
    public Single<ResponseEntity<BaseWebResponse<Author>>> addAuthor(@RequestBody AddAuthorRequest author) {
        return authorService.addAuthor(author)
            .subscribeOn(Schedulers.io())
            .map(s -> ResponseEntity
                .created(URI.create("/api/authors/" + s.getId()))
                .body(successWithData(s)));
    }
}
