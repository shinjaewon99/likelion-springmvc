package com.example.likelion;

import com.example.likelion.model.StudentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MvcController {
    private final LottoService lottoService;
    private int hits = 0;
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
        hits++;
        model.addAttribute("hits", hits);
        return "hits";
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello. ThymeLeaf");
        return "home";
    }

    @RequestMapping("/student")
    public String student(Model model) {

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


        return "each";
    }


}
