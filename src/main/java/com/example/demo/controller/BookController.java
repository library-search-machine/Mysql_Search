package com.example.demo.controller;



import com.example.demo.domain.Book;
import com.example.demo.dto.response.BookResponseDto;

import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookService bookService;
    private  final BookRepository bookRepository;

    @GetMapping("/")
    public String main(Model model){

        return "main";
    }



    @GetMapping("search")
    public String getBook(@RequestParam() String name,@RequestParam() String type,Model model){
        List<BookResponseDto> bookList =  bookService.getBook(name,type);
        System.out.println(bookList.size());
        model.addAttribute("bookList", bookList);
        return "main";
    }


}
