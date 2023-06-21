package com.example.likelion.week10day3.repository;

import com.example.likelion.week10day3.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // select id from ArticleEntity;
    List<CommentEntity> findAllByArticleId(Long id);

}
