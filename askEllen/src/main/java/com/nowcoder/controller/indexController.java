package com.nowcoder.controller;

import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页显示
 * 2017-9-25
 */

@Controller
public class indexController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CommentService commentService;

    //首页显示
    @RequestMapping(path = ("/askEllen"))
    public String index(Model model){
        List<ViewObject> vios = new ArrayList<>();
        List<Question> questionForIndex = new ArrayList<>();

        //读取最近的十条消息
        questionForIndex = questionService.getQuestionLast(0,10);

        for(Question question : questionForIndex){
            int id = question.getUserId();
            //更新评论数量
            int count = commentService.selectCommNumByEntityId(question.getId());
            System.out.println("askEllen count:"+count);
            questionService.updatCommentCount(count,question.getId());

            ViewObject vo = new ViewObject();
            vo.set("question",question);
            vo.set("user",userService.getUserById(id));

            vios.add(vo);
        }

//        取user测试
        User user = hostHolder.getUser();

        model.addAttribute("vios",vios);
        model.addAttribute("temp","123");

        model.addAttribute("user",user);
        return "index";
    }



    //测试freemarker
    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("name", "[Angel -- 守护天使]");
        map.put("gender",1);//gender:性别，1：男；0：女；

        List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();
        Map<String,Object> friend = new HashMap<String,Object>();
        friend.put("name", "张三");
        friend.put("age", 20);
        friends.add(friend);
        friend = new HashMap<String,Object>();
        friend.put("name", "李四");
        friend.put("age", 22);
        friends.add(friend);
        map.put("friends", friends);
        return "exemple/hello";
    }
}
