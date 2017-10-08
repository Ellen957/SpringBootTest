package com.nowcoder.controller;

import com.nowcoder.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class searchReasultController {
    @Autowired
    QuestionService questionService;

    @RequestMapping(path = "/search")
    public String searchResult(@RequestParam("type") String type,
                               @RequestParam("q") String title){
        System.out.println(type);
        System.out.println(title);
        return "result";
    }
}
