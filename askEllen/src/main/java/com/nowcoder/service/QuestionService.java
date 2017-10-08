package com.nowcoder.service;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    SensitiveService sensitiveService;


    //查找最近的问题
    public List<Question> getQuestionLast(int OFFSET,int LIMIT){
        return questionDAO.selectQuestionLast(OFFSET,LIMIT);
    }

    //查找某个问题
    public Question seletcById(int id){
        return questionDAO.selectQuesById(id);
    }

    //根据题目查找问题
    public List<Question> selectByTitle(String title){
        return questionDAO.selectQuestionByTitle(title);
    }

    //查找所有问题
    public List<Question> getAllQuestion(){
        return questionDAO.selectQuestion();
    }

    //添加问题
    public int addQuestion(Question question) throws Exception {
        //过滤HTML格式输入
        question.setContent(HtmlUtils.htmlEscape(question.getContent()));
        question.setTitle(HtmlUtils.htmlEscape(question.getTitle()));

        sensitiveService.afterPropertiesSet();
        //敏感词过滤
        question.setContent(sensitiveService.filter(question.getContent()));
        question.setTitle(sensitiveService.filter(question.getTitle()));

        return questionDAO.addQuestion(question) > 0 ? question.getId():0;

    }

    //通过ID删除问题
    public void deleteQuestion(int id){
        questionDAO.deleteQuestion(id);
    }

    //通过ID查询时间
    public Date selectDateById(int id){
        return questionDAO.selectDateById(id);
    }

    //更新commentCount
    public void updatCommentCount(int commentCount,int questionId){
        questionDAO.updateCommentCount(commentCount,questionId);
    }
}
