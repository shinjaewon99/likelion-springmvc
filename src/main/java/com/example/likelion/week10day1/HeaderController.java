package com.example.likelion.week10day1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Controller
public class HeaderController {

    @PostMapping("/header-one")
    public String getHeader(@RequestHeader("x-likelion") String header) {
        log.info("POST /header-one header :" + header);
        return "index";
    }


    // 헤더중 x-likelion 이있으면 할당, 없으면 null로 받고 싶을때
    @PostMapping("/header-optional")
    public String getHeaderOptional(@RequestHeader(value = "x-likelion", required = false) String header) {

        log.info("POST /header-optional header :" + header);
        return "index";
    }

    // 헤더중 x-likelion-int 가 있으면 Integer 할당
    @PostMapping("/header-type")
    public String getHeaderInteger(@RequestHeader(value = "x-likelion") Integer header) {

        log.info("POST /header-type header :" + header);
        return "index";
    }

}
