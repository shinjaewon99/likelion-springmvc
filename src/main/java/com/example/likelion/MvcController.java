package com.example.likelion;

import com.example.likelion.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class MvcController {
    private final LottoService lottoService;

    // 의존성 주입
    public MvcController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @RequestMapping("/lotto")
    public String lotto(Model model){

        model.addAttribute("lotto", lottoService.lotto());
        return "lotto";
    }
    @RequestMapping("/hits")
    public String hits(Model model) {
        model.addAttribute("hits", lottoService.addHit());
        return "hits";
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello. ThymeLeaf");
        return "home";
    }

    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute(
                "object",
                new Student("JaeWon Shin", "JaeWonShin@naver.com")
        );
        return "student";
    }

    @RequestMapping("/is-logged-in")
    public String isLoggedIn(Model model){

        model.addAttribute(
                "isLoggedIn",
//                true
                false
        );
        return "if-unless";
    }

    @RequestMapping("/each")
    public String items(Model model){
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("foo");
        listOfStrings.add("bar");
        listOfStrings.add("baz");

        model.addAttribute("listOfStrings", listOfStrings);

        List<Student> studentList = Arrays.asList(
                new Student("Alex", "Alex123@naver.com"),
                new Student("Tomas", "Tomas123@naver.com"),
                new Student("Bob", "Bob45@naver.com")
        );
        return "each";
    }


}
