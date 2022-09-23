package com.example.demo.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookResponseDto {
    private Long id;
    private String title;

    private String writer;

    private String publisher;



    private String isbn;
    private String library;
}