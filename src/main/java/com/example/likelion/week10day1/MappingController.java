package com.example.likelion.week10day1;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class MappingController {
    // @Slf4j를 사용하면 아래 코드를 해당 클래스에 자동으로 만들어 준다.
//    private static final Logger logger = LoggerFactory.getLogger(MappingController.class);



    // '/path'로 오는 GET 요청에 대해서 메소드를 실행하고 싶을경우
    @RequestMapping(
            value = "/path",
            method = RequestMethod.GET
    )
    public String getPath(){
        log.info("GET /path");
        return "index";
    }

    // '/path'로 오는 POST 요청에 대해서 메소드를 실행하고 싶을경우
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST
    )
    public String postPath(){
        log.info("POST /path");
        return "index";
    }

}
