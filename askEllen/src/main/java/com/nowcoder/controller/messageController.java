package com.nowcoder.controller;

import com.nowcoder.model.Message;
import com.nowcoder.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class messageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(path = "/letter/{userId}")
    public String letter(Model model,
                         @PathVariable("userId") int userId){
        Message message = new Message();
        List<Message> messages = new ArrayList<>();

        messages = messageService.selectMessage();

        model.addAttribute("messages",messages);

        return "letter";
    }

    @RequestMapping(path = "/letterDetail/{conversationId}")
    public String letterDetail(Model model,
                               @PathVariable("conversationId") String conversationId){
        List<Message> messages = new ArrayList<>();

        messages = messageService.selectMessageByCId(conversationId);

        model.addAttribute("messages", messages);

        return "letterDetail";
    }

    @RequestMapping(path = "/deleteLetter/{id}/{conversationId}")
    public String deleteLetter(@PathVariable("id") int id,
                               @PathVariable("conversationId") String conversationId){
        messageService.deleteLetter(id);
        return "redirect:/letterDetail/"+conversationId;
    }


}
