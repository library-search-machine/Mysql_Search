package com.example.demo.controller;



import com.example.demo.domain.Book;
import com.example.demo.dto.response.BookResponseDto;

import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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


    @GetMapping("/")
    public String main(Model model){

        return "main";
    }



    @GetMapping("/search")
    public String getBook(@RequestParam() String keyword,@RequestParam() String type,@RequestParam() int page,Model model){
        Page<BookResponseDto> bookList =  bookService.getBook(keyword,type,page);
        model.addAttribute("totalPages", bookList.getTotalPages());
        model.addAttribute("totalItems", bookList.getTotalElements());

        long startCount = (page - 1) * 30 + 1;
        model.addAttribute("startCount", startCount);

        long endCount = startCount + 30 - 1;
        if (endCount > bookList.getTotalElements()) {
            endCount = bookList.getTotalElements();
        }

        model.addAttribute("endCount", endCount);
        model.addAttribute("bookList", bookList);
        model.addAttribute("keyword", keyword);

        return "main";
    }




}
