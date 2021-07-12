package com.saurabh.rxjavatutorial.models.response;

import com.saurabh.rxjavatutorial.models.entity.Book;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BookResponse {
    private String id;
    private String title;
    private String authorName;

    public static class BookResponseTransformer {
        public static BookResponse from(Book book) {
            final BookResponse bookWebResponse = new BookResponse();
            BeanUtils.copyProperties(book, bookWebResponse);
            return bookWebResponse;
        }
    }
}