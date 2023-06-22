package com.example.likelion.week10day3.service;

import com.example.likelion.week10day3.dto.ArticleDto;
import com.example.likelion.week10day3.entity.ArticleEntity;
import com.example.likelion.week10day3.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        if (optionalArticle.isPresent()) {
            return ArticleDto.fromEntity(optionalArticle.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if (optionalArticle.isPresent()) {
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

        if (optionalArticle.isPresent()) {
            repository.delete(optionalArticle.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // Spring Data Jpa 쿼리 메소드로 페이지 조회하는것은 좋지않은 방법
//    public List<ArticleDto> readArticlePaged() {
//        List<ArticleDto> articleDtoList = new ArrayList<>();
//        for (ArticleEntity entity : repository.findTop20ByOrderByIdDesc()) {
//            articleDtoList.add(ArticleDto.fromEntity(entity));
//        }
//        return articleDtoList;
//    }

//    public List<ArticleDto> readArticlePaged() {
//        // 조회하고 싶은 페이지의 정보를 담는 객체
//        // 20개씩 데이터를 나눌때 0번 페이지를 달라고 요청하는 Pageable
//        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
//
//        Page<ArticleEntity> articleEntityPage
//                = repository.findAll(pageable);
//
//        List<ArticleDto> articleDtoList = new ArrayList<>();
//        for (ArticleEntity entity : articleEntityPage) {
//            articleDtoList.add(ArticleDto.fromEntity(entity));
//        }
//
//        return articleDtoList;
//    }

    public Page<ArticleDto> readArticlePaged(
            Integer pageNumber, Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Page<ArticleEntity> articleEntityPage
                = repository.findAll(pageable);

        // map : 전달받은 함수를 각 원소에 인자로 전달된 결과를
        // 다시 모와서 Stream 객체로

        Page<ArticleDto> articleDtoPage
                = articleEntityPage.map(ArticleDto::fromEntity);

        return articleDtoPage;
    }

    public Page<ArticleDto> search (
            String query, Integer pageNumber
    ){
        Pageable pageable
                = PageRequest.of(pageNumber, 20, Sort.by("id").descending());

        Page<ArticleEntity> articleEntityPage
                = repository.findAll(pageable);

        return repository.findAllByTitleContent(query, pageable).map(ArticleDto::fromEntity);
    }
}
