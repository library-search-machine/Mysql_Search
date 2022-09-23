package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public List<BookResponseDto> getBook(String keyword, String type) {
        List<Book> bookList;
        long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        if (type.equals("title"))
            bookList = bookRepository.findByBookNameContaining(keyword);

        else if (type.equals("writer"))
            bookList = bookRepository.findByAuthorsContaining(keyword);
        else
            bookList = bookRepository.findByIsbn13Containing(keyword);
        System.out.println(bookList.size());
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        for (Book book : bookList) {
            bookResponseDtoList.add(
                    BookResponseDto.builder()
                            .id(book.getId())
                            .title(book.getBookName())
                            .writer(book.getAuthors())
                            .isbn(book.getIsbn13())
                            .library(book.getLibraryName())
                            .build()
            );
        }
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTime = (afterTime - beforeTime);
        System.out.println("시간차이(m) : " + secDiffTime);
        return bookResponseDtoList;
    }


}
