package com.example.likelion.week10day1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

    // '/path'로 오는 POST 요청에 대해서 메소드를 실행하고 싶을경우
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST
    )
    public String postPath() {
        log.info("POST /path");
        return "index";
    }


    // '/path'로 들어오는 PUT 또는 DELETE 요청에 대해서
    @RequestMapping(
            value = "/path",
            method = {RequestMethod.PUT, RequestMethod.DELETE}
    )
    public String putOrDeletePath() {
        log.info("PUT or DELETE /path");
        return "index";
    }


    // '/path'로 들어오는 POST 요청이면서 JSON 데이터를 포함하는 요청에 대해서
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String consume() {
        log.info("POST /path Content-Type : application/json");
        return "index";
    }

    // '/path' 로 들어오는 POST 요청이면서
    // 헤더에 'x-likelion 이라는 헤더가 값이 hello로 지정되어 있을때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            headers = "x-likelion=hello"
    )
    public String headerWith() {
        log.info("POST /x-likelion header");
        return "index";
    }


}
