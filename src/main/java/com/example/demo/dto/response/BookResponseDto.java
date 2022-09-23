package com.example.demo.dto.response;


import com.example.demo.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private Long id;
    private String title;

    private String writer;

    private String publisher;

    private String isbn;
    private String library;

    public  Page<BookResponseDto> toDtoList(Page<Book> postList){
        Page<BookResponseDto> ResponsePostList = postList.map(m -> BookResponseDto.builder()
                .id(m.getId())
                .title(m.getBookName())
                .writer(m.getAuthors())
                .publisher(m.getPublisher())
                .isbn(m.getIsbn13())
                .library(m.getLibraryName())
                .build()
        );
        return ResponsePostList;
    }
}