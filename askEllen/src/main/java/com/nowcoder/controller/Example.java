package com.nowcoder.controller;

import com.nowcoder.aspect.exemple;
import com.nowcoder.service.exempleService;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Example {
    @Autowired
    exempleService serv;
    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    @RequestMapping(path = {"/","index"},method = {RequestMethod.GET})
    @ResponseBody
    public String index(HttpSession httpSession){
        logger.info("visite index");
        String a = (String)httpSession.getAttribute("msg");
        return "this is session " + a;
    }

    @RequestMapping(path = "/test/{param1}")
    @ResponseBody
    public String test(@PathVariable("param1") String param2,
                       @RequestParam(value = "param3",defaultValue = "defaultValue")String param4){
        return "this is param1 "+ param2 + " and this is param3 =" + param4;

    }
    @RequestMapping("/freemarker")
    public String hello(Model model, HttpSession httpSession) {
        httpSession.setAttribute("msg","i love u");
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("this world");
        model.addAttribute("list",list);
        model.addAttribute("value1","hello");
        return "redirect:/";
    }

    @RequestMapping("/testService")
    @ResponseBody
    public String testService() {
        return String.format("%s",serv.serviceTest("dsxa"));
    }

}
