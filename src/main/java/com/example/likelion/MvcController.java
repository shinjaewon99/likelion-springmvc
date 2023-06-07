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
    private int toDayHit = 0;

    @RequestMapping("/lotto")
    public String lotto(Model model){
        List<Integer> lottoList = new ArrayList<>();
        Random random = new Random();
        while(lottoList.size() < 6){
            int randomNumber = random.nextInt(45) + 1; // bound 값은 포함하지 않음으로 0 ~ 44가 된다. 그래서 + 1
            if(!lottoList.contains(randomNumber)){
                lottoList.add(randomNumber);
            }
        }
        model.addAttribute("lotto", lottoList);
        return "lotto";
    }
    @RequestMapping("/hits")
    public String hits(Model model) {
        model.addAttribute("hits", ++toDayHit);
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
