package com.example.likelion.week10day1;

import com.example.likelion.week10day1.dto.ArticleDto;
import com.example.likelion.week10day1.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
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

        ResponseDto response = new ResponseDto();
        response.setStatus(200);
        response.setMessage("success");
        return response;
    }
}
