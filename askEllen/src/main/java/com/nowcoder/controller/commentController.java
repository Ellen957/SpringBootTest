package com.nowcoder.controller;

import com.nowcoder.model.Comment;
import com.nowcoder.service.CommentService;
import com.nowcoder.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class commentController {
     @Autowired
    CommentService commentService;
     @Autowired
    SensitiveService sensitiveService;

     @RequestMapping(path = "/addQuesComment/{questionId}",method = RequestMethod.POST)
     public String addQuesComment(@PathVariable("questionId") int questionId,
                                @RequestParam("content") String content,
                                  @RequestParam("userId") int userId) throws Exception {
         Comment comment = new Comment();
         sensitiveService.afterPropertiesSet();

         comment.setContent(sensitiveService.filter(content));
         comment.setEntityType(0);
         comment.setEntityId(questionId);
         comment.setUserId(userId);
         comment.setCreatedDate(new Date());

         commentService.addComment(comment);
         return "redirect:/detail/"+questionId;
     }
}
