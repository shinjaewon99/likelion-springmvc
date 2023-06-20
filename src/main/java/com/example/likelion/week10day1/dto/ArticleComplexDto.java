package com.example.likelion.week10day1.dto;

import lombok.Data;

import java.util.List;

@Data
public class ArticleComplexDto {
    private String title;
    private String content;
    private WriterDto writer;
    private List<String> comments;

}
