package com.example.likelion.week10day3.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contents")
@Data
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private Long articleId;
    private String writer;
    private String content;

    @ManyToOne
    private ArticleEntity articleEntity;
}
