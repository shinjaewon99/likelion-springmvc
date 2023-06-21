package com.example.likelion.week10day3.dto;

import com.example.likelion.week10day3.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long articleId;
    private String writer;
    private String content;


    public static CommentDto fromEntity(CommentEntity entity){
        CommentDto dto = new CommentDto();

        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setWriter(entity.getWriter());
        dto.setContent(entity.getContent());

        return dto;
    }
}
