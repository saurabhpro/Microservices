package com.saurabh.rxjavatutorial.models.request;

import lombok.Data;

@Data
public class AddBookRequest {
    private String title;
    private String authorId;
}
