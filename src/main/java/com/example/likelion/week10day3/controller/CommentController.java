package com.example.likelion.week10day3.controller;

import com.example.likelion.week10day3.dto.CommentDto;
import com.example.likelion.week10day3.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article/{articleId}/comments")
public class CommentController {

    private final CommentService service;


    @PostMapping
    public CommentDto create(
            @PathVariable("articleId") Long id,
            @RequestBody CommentDto dto
    ) {
        return service.createComment(id, dto);
    }

    // TODO 게시글 댓글 전체 조회
    // GET /articles/{articleId}/comments

    @GetMapping
    public List<CommentDto> readAll (
            @PathVariable("articleId") Long articleId
    ){
        return service.readCommentAll(articleId);
    }

    // TODO 게시글 댓글 수정
    // PUT /articles/{articleId}/comments/{commentId}

    @PutMapping("/{commentId}")
    public CommentDto update(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId,
            CommentDto dto
    ){

        return service.updateComment(articleId, commentId, dto);
    }

    // TODO 게시글 댓글 삭제
    // DELETE /articles/{articleId}/comments/{commentId}
    @DeleteMapping("/{commentId}")
    public CommentDto delete(
            @PathVariable("articleId") Long articleId,
            @PathVariable("commentId") Long commentId
    ){
        return service.deleteComment(articleId,commentId);
    }

}
