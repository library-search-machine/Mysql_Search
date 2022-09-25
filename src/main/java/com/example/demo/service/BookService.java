package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.dto.response.BookResponseDto;
import com.example.demo.dto.response.BookResponseDto2;
import com.example.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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


    @Transactional
    public BookResponseDto2 getBookByIsbn(String isbn) throws MalformedURLException {
        List<String> LibraryList = bookRepository.findByLibraryName(isbn);//
        String url_address = "http://data4library.kr/api/srchDtlList?authKey=6bd363e870bb744d2e52c35f15cfef0aa929faba70bc2d66961aae91e101901f&isbn13="+isbn+"&loaninfoYN=N&format=json";
        try{
            URL url = new URL(url_address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            JSONParser parser	= new JSONParser();
            JSONObject obj =(JSONObject)parser.parse(sb.toString());
            JSONObject response =(JSONObject)obj.get("response");
            JSONArray detail =(JSONArray) response.get("detail");
            JSONObject temp = (JSONObject)detail.get(0);
            JSONObject book = (JSONObject)temp.get("book");

            String description = (String)book.get("description");
            description=description.replaceAll("&gt;","");
            description=description.replaceAll("&lt;","");


            BookResponseDto2 bookResponseDto = BookResponseDto2.builder()
                    .bookName((String)book.get("bookname"))
                    .authors((String)book.get("authors"))
                    .publisher((String)book.get("publisher"))
                    .class_nm((String)book.get("class_nm"))
                    .publicationYear((String)book.get("publication_year"))
                    .bookImageURL((String)book.get("bookImageURL"))
                    .description(description)
                    .LibraryList(LibraryList)
                    .build();
            return  bookResponseDto;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



}
