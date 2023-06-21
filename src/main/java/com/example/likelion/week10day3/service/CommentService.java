package com.example.likelion.week10day3.service;

import com.example.likelion.week10day3.dto.CommentDto;
import com.example.likelion.week10day3.entity.CommentEntity;
import com.example.likelion.week10day3.repository.ArticleRepository;
import com.example.likelion.week10day3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentDto createComment(Long articleId, CommentDto dto){

        // article를 ID로 가진 ArticleEntity 가 존재 하는지?
        if(!articleRepository.existsById(articleId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CommentEntity entity = new CommentEntity();
        entity.setWriter(dto.getWriter());
        entity.setContent(dto.getContent());
        entity.setArticleId(articleId);
        entity = commentRepository.save(entity);

        return CommentDto.fromEntity(entity);
    }
}
