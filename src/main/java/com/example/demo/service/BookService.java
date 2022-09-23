package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Page<BookResponseDto> getBook(String keyword, String type,int page) {
        Pageable pageable = PageRequest.of(page - 1, 30);
        Page<Book> bookList;
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        if (type.equals("title"))
            bookList = bookRepository.findByBookNameContaining(keyword,pageable);

        else if (type.equals("writer"))
            bookList = bookRepository.findByAuthorsContaining(keyword,pageable);
        else
            bookList = bookRepository.findByIsbn13Containing(keyword,pageable);
        Page<BookResponseDto> bookResponseDtoList = new BookResponseDto().toDtoList(bookList);

        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("시간차이(m) : using full text search" + secDiffTime);
        return bookResponseDtoList;
    }



}
