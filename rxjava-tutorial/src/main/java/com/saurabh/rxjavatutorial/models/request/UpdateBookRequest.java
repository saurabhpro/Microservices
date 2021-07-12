package com.saurabh.rxjavatutorial.models.request;

import lombok.Data;

@Data
public class UpdateBookRequest {
    private String id;
    private String title;
}