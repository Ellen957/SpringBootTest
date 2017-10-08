package com.nowcoder.controller;

import com.nowcoder.model.User;
import com.nowcoder.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.jaxen.pattern.PatternHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.swing.StringUIClientPropertyKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginController {
    @Autowired
    UserService userService;

    //注册
    @RequestMapping(path = ("/reg"),method = {RequestMethod.POST})
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "next",required = false) String next,
                      HttpServletResponse response){
        try{
            Map<String, String> map = userService.register(username, password);
            if(map.containsKey("msg")){
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }
            if(map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket",map.get("ticket"));
                //增加作用域范围
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            if(!next.isEmpty()){
                System.out.println("***next"+next);
                return "redirect:"+next;
            }
            return "redirect:/askEllen";
        }catch (Exception e){
            e.printStackTrace();
            return "login";
        }

    }

    //退出
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/index";
    }

    //登录/注册页面
    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String login(Model model,@RequestParam(value = "next", required = false) String next) {
        model.addAttribute("next", next);
        System.out.println("this is next: "+next);
        return "login";
    }

    //登录
    @RequestMapping(path = "/reglogin",method = RequestMethod.POST)
    public String reglogin(Model model,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "next",required = false) String next,
                           HttpServletResponse response) {
        try{
            Map<String, String> map = new HashMap<>();
            map = userService.checkUser(username,password);
            System.out.println("reglog this is next: "+next);

            if(map.containsKey("msg")){
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }else {
                if(map.containsKey("ticket")){
                    Cookie cookie = new Cookie("ticket",map.get("ticket"));
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if(!next.isEmpty()){
                    System.out.println("***next"+next);
                    return "redirect:"+next;
                }

                return "redirect:/askEllen";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "login";
    }
}
