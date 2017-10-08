package com.nowcoder.controller;

import com.nowcoder.model.Comment;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.LikeService;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import com.nowcoder.util.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class questionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CommentService commentService;
    @Autowired
    LikeService likeService;

    int annoymousID = 000;

    @RequestMapping(path = "/detail/{questionId}")
    public String detail(Model model,
            @PathVariable("questionId") int questionId){
        Question question = questionService.seletcById(questionId);
        List<Comment> commentList = new ArrayList<>();
        List<ViewObject> vio = new ArrayList<>();

        //读取所有评论entityType=0问题评论
        commentList = commentService.selectCommById(questionId,0);
        for(Comment comment:commentList){
            ViewObject vo = new ViewObject();
            vo.set("comment",comment);
            //判断是否喜欢
            if(hostHolder.getUser() == null){
                vo.set("liked",0);
            }else{
                vo.set("liked",likeService.getLikeStatus(hostHolder.getUser().getId(),0,comment.getId()));
            }

            vo.set("likeCount",likeService.getLikeCount(0,comment.getId()));
            vo.set("user",userService.getUserById(comment.getUserId()));
            vio.add(vo);
        }

        model.addAttribute("quesComment",vio);
        model.addAttribute("question",question);
        model.addAttribute("user",userService.getUserById(question.getUserId()));
        return "detail";
    }

    @RequestMapping(path = "/popup/{userId}")
    public String addQuestion(Model model,
                       @PathVariable("userId") int userId){
        return "popup";
    }

    @RequestMapping(path = "/question/add",method = RequestMethod.POST)
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam(value = "Content",defaultValue = "no value") String content){
//        System.out.println("***********"+title);
//        System.out.println("***********"+content);
        try{
            util u = new util();
            Question question = new Question();
            question.setTitle(title);
            question.setContent(content);
            question.setCreatedDate(new Date());

            //判断当前用户是否为空
            if(hostHolder.getUser() == null){
                question.setUserId(annoymousID);
            }else{
                question.setUserId(hostHolder.getUser().getId());
            }

            if(questionService.addQuestion(question) > 0){
                return "redirect:/askEllen";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return util.getJsonString(1,"失败");
    }
}
