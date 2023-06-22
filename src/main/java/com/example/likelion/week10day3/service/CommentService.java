package com.example.likelion.week10day3.service;

import com.example.likelion.week10day3.dto.CommentDto;
import com.example.likelion.week10day3.entity.CommentEntity;
import com.example.likelion.week10day3.repository.ArticleRepository;
import com.example.likelion.week10day3.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {

        // article를 ID로 가진 ArticleEntity 가 존재 하는지?
        if (!articleRepository.existsById(articleId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CommentEntity entity = new CommentEntity();
        entity.setWriter(dto.getWriter());
        entity.setContent(dto.getContent());
        entity.setArticleId(articleId);
        entity = commentRepository.save(entity);

        return CommentDto.fromEntity(entity);
    }

    // TODO 게시글 댓글 전체 조회
    public List<CommentDto> readCommentAll(Long articleId) {
        List<CommentEntity> commentEntities
                = commentRepository.findAllByArticleId(articleId);

        List<CommentDto> commentList = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntities) {
            commentList.add(CommentDto.fromEntity(commentEntity));
        }

        return commentList;
    }

    // TODO 게시글 댓글 수정
    // 수정하고자 하는 댓글이 지정한 게시글이 맞는지 확인할 목적으로
    public CommentDto updateComment(
            Long articleId,
            Long commentId,
            CommentDto dto
    ){

        Optional<CommentEntity> optionalCommentEntity
                = commentRepository.findById(articleId);

        if(optionalCommentEntity.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
        // 아니면 로직 실행
        CommentEntity comment = optionalCommentEntity.get();

        // 댓글이 해당 게시글의 댓글이 맞는지 확인
        if(!articleId.equals(comment.getArticleId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        comment.setContent(dto.getContent());
        comment.setWriter(dto.getWriter());

        return CommentDto.fromEntity(commentRepository.save(comment));
    }


    // TODO 게시글 댓글 삭제
    public void deleteComment(Long articleId, Long commentId) {

        Optional<CommentEntity> optionalComment
                = commentRepository.findById(commentId); // 댓글의 Id를 찾는다.

        if(optionalComment.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        CommentEntity comment = optionalComment.get();

        if(!articleId.equals(comment.getArticleId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        commentRepository.deleteById(commentId);
    }



}
