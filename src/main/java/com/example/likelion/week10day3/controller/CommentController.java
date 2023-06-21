package com.example.likelion.week10day3.controller;

import com.example.likelion.week10day3.dto.CommentDto;
import com.example.likelion.week10day3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article/{articleId}/comments")
public class CommentController {

    private final CommentService service;


    @PostMapping
    public CommentDto create(
            @PathVariable("articleId") Long id,
            @RequestBody CommentDto dto
    ){
        return service.createComment(id, dto);
    }
}
