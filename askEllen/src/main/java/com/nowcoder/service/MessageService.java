package com.nowcoder.service;

import com.nowcoder.dao.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowcoder.model.Message;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageDAO messageDAO;

    public void addMessage(Message message){
        messageDAO.addMessage(message);
    }

    public List<Message> selectMessage(){
        return messageDAO.selectMessage();
    }

    public List<Message> selectMessageByCId(String conversationId){
        return messageDAO.selectMessageByCId(conversationId);
    }

    public void deleteLetter(int id){
        messageDAO.deleteLetter(id);
    }
}
