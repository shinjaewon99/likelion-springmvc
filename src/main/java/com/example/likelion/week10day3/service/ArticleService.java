package com.example.likelion.week10day3.service;

import com.example.likelion.week10day3.dto.ArticleDto;
import com.example.likelion.week10day3.entity.ArticleEntity;
import com.example.likelion.week10day3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleDto createArticle(ArticleDto dto) {

        ArticleEntity entity = new ArticleEntity();
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setWriter(dto.getWriter());

        return null;
    }

    public List<ArticleDto> readArticleAll() {

        List<ArticleDto> articleList = new ArrayList<>();
        for (ArticleEntity entity : repository.findAll()) {
            articleList.add(ArticleDto.fromEntity(entity));
        }
        return articleList;
    }

    public ArticleDto readArticle(Long id) {

        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if(optionalArticle.isPresent()){
            return ArticleDto.fromEntity(optionalArticle.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if(optionalArticle.isPresent()){
            ArticleEntity entity = optionalArticle.get();
            entity.setWriter(dto.getWriter());
            entity.setContent(dto.getContent());
            entity.setTitle(dto.getTitle());
            repository.save(entity);
            return ArticleDto.fromEntity(entity);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void deleteArticle(Long id) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if(optionalArticle.isPresent()){
            repository.delete(optionalArticle.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
