package com.example.likelion.week11day2.controller;

import com.example.likelion.week11day2.aspect.LogArguments;
import com.example.likelion.week11day2.dto.ResponseDto;
import com.example.likelion.week11day2.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AopController {


    @PostMapping("/users")
    // 컨트롤러의 코드를 크게 바꾸지 않으면서
    // 부수적인 기능을 추가
    @LogArguments
    public ResponseDto addUser(@RequestBody UserDto userDto){
        log.info(userDto.toString());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("addUser");
        return responseDto;
    }
}
