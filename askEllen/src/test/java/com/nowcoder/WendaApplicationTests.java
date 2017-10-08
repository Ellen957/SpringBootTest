package com.nowcoder;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import com.nowcoder.service.LikeService;
import com.sun.xml.internal.ws.api.message.saaj.SaajStaxWriter;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
@WebAppConfiguration
public class WendaApplicationTests {
	@Autowired
	UserDAO userDAO;
	@Autowired
	QuestionDAO questionDAO;
	@Autowired
	LikeService likeService;
	@Test
	public void contextLoads() {
		likeService.like(41,0,45);
		likeService.like(16,0,45);
		likeService.like(21,0,45);
		likeService.like(116,0,45);
		likeService.like(3,0,45);
		likeService.like(161,0,45);
		System.out.println(likeService.getLikeCount(0,45));
//		Random random = new Random();
//
//		for(int i=0;i<11;i++){
//			User user = new User();
//			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
//			user.setName(String.format("User%d",i));
//			user.setPassword("");
//			user.setSalt("");
//			userDAO.addUser(user);
//
//			user.setPassword("xxxx");
//			userDAO.updatePassword(user);

		}
//		List<Question> list = new ArrayList<>();
//
//		for(int i=0;i<11;i++){
//			Question question = new Question();
//			question.setComment_count(String.valueOf(i));
//			question.setUser_id(i);
//			Date date = new Date();
//			date.setTime(date.getTime()+1000*3600*i);
//			question.setCreated_date(date);
//			question.setComment_count(String.valueOf(i));
//			question.setTitle(String.format("title{%d}",i));
//			question.setContent(String.format("hahahahah%d",i));
//
//			questionDAO.addQuestion(question);
//		}
		List<Question> questionForIndex = new ArrayList<>();

//		//读取最近的十条消息
//		questionForIndex = questionDAO.selectQuestionLast(0,10);
//		for(Question q : questionForIndex){
//			User user = new User();
//			user  = userDAO.selectById(q.getUserId());
//			System.out.println(user.getName());
//			System.out.println(user.getHeadUrl());
//			System.out.println(user.getId());
//			System.out.println(user.getPassword());
//			System.out.println(user.getSalt());
//			System.out.println("***************");
//		}
//		System.out.println(questionForIndex.get(0).getTitle()+" id:"+questionForIndex.get(0).getId());
////		System.out.println(questionForIndex.get(0).getCreated_date());
//		Question a = new Question();
//		a.setCreated_date(questionDAO.selectAllById(33));
//		System.out.println(a.getCreated_date());
//		User user = new User();
//		user = userDAO.selectById(1);
//		System.out.println(user.getName());




}
