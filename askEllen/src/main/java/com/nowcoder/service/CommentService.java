package com.nowcoder.service;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    public Comment selectComment(){
        return commentDAO.selectComment();
    }

    public List<Comment> selectCommById(int entityId, int entityType){
        return commentDAO.selectCommByID(entityId, entityType);
    }

    public void deleteById(int id){
        commentDAO.deleteById(id);
    }

    public void addComment(Comment comment){
        commentDAO.addComment(comment);
    }

    public int selectCommNumByEntityId(int id){
        return commentDAO.selectCommNumByEntityId(id);
    }
}
