package com.example.likelion.week10day3.controller;

import com.example.likelion.week10day3.dto.ArticleDto;
import com.example.likelion.week10day3.entity.ArticleEntity;
import com.example.likelion.week10day3.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService service;

    @PostMapping
    public ArticleDto create(@RequestBody ArticleDto dto) {
        return service.createArticle(dto);
    }

    @GetMapping
    public List<ArticleDto> readAll() {
        return service.readArticleAll();
    }

    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id) {
        return service.readArticle(id);
    }

    @PutMapping("/{id}")
    public ArticleDto update(@PathVariable("id") Long id,
                             @RequestBody ArticleDto dto) {

        return service.updateArticle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Long id
    ) {
        service.deleteArticle(id);
    }

    // GET /article/page-test
//    @GetMapping("/page-test")
//    public Page<ArticleDto> readPageTest() {
//        return service.readArticlePaged();
//    }

    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ){
        return service.readArticlePaged(page, limit);
    }
}
