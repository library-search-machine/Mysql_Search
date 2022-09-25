package com.example.demo.repository;


import com.example.demo.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {



    @Query(value = "SELECT * FROM book WHERE MATCH(book_name) "
            + "AGAINST (?1)", nativeQuery = true)
    Page<Book> findByBookNameContaining(String bookname, Pageable pageable);
    @Query(value = "SELECT * FROM book WHERE MATCH(authors) "
            + "AGAINST (?1)", nativeQuery = true)
    Page<Book> findByAuthorsContaining(String authors, Pageable pageable);
    @Query(value = "SELECT * FROM book WHERE MATCH(isbn13) "
            + "AGAINST (?1)", nativeQuery = true)
    Page<Book> findByIsbn13Containing(String isbn, Pageable pageable);


    @Query(value = "SELECT library_name FROM book WHERE MATCH(isbn13) "
            + "AGAINST (?1)", nativeQuery = true)
    List<String> findByLibraryName(String isbn);



}
