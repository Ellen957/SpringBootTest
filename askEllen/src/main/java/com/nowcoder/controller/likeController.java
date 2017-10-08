package com.nowcoder.controller;

import com.nowcoder.model.HostHolder;
import com.nowcoder.service.LikeService;
import com.nowcoder.util.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class likeController {
    @Autowired
    LikeService likeService;
    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/like/{commentId}"},method ={RequestMethod.POST})
    @ResponseBody
    public String like(@PathVariable("commentId") int commentId){
        if(hostHolder.getUser() == null){
            return "redirect:/index";
        }
        System.out.println("this is commentId"+commentId);
        long likeCount = likeService.like(hostHolder.getUser().getId(),0,commentId);
        return util.getJsonString(0, String.valueOf(likeCount));
    }

    @RequestMapping(path = {"/dislike/{commentId}"},method ={RequestMethod.POST})
    @ResponseBody
    public String dislike(@PathVariable("commentId") int commentId){
        if(hostHolder.getUser() == null){
            return "redirect:/index";
        }

        long dislikeCount = likeService.dislike(hostHolder.getUser().getId(),0,commentId);
        return util.getJsonString(0, String.valueOf(dislikeCount));
    }
}
