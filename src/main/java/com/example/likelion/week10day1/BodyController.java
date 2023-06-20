package com.example.likelion.week10day1;

import com.example.likelion.week10day1.dto.ArticleComplexDto;
import com.example.likelion.week10day1.dto.ArticleDto;
import com.example.likelion.week10day1.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class BodyController {

    // '/body'로 요청이 들어왔을때
    // ResponseDto 데이터를 표현한 JSON 응답을 반환하는 메소드
    @PostMapping("/body")
    public @ResponseBody ResponseDto body(
            @RequestBody ArticleDto requestDto
    ) {
        log.info(requestDto.toString());
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }

    @PostMapping("/body-1")
    public @ResponseBody ResponseDto body1(
            @RequestBody ArticleComplexDto requestDto
    ) {
        log.info(requestDto.toString());
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }

    // @ResponseBody는 요청의 HTTP Body만 설정
    // 헤더를 추가하거나
    // Status code를 고르고 싶을떄
    // ResponseEntity<T>
    @PostMapping("/entity")
    public ResponseEntity<ResponseDto> entity(
            @RequestBody ArticleDto requestDto
    ) {
        log.info(requestDto.toString());
        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        ResponseEntity<ResponseDto> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);


        HttpHeaders headers = new HttpHeaders();
        headers.add("x-likelion-custom", "hello");
                
//        return new ResponseEntity<>(
//                response, headers, HttpStatus.ACCEPTED
//        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("x-likelion-one", "1")
                .headers(headers)
                .body(response);
    }
}
