package com.nowcoder.service;

import com.nowcoder.dao.LoginDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginDAO loginDAO;

    //通过ID查找用户信息
    public User getUserById(int id){
        return (User)userDAO.selectById(id);
    }

    //通过ID删除用户信息
    public void deleteUserById(int id){
        userDAO.deleteById(id);
    }

    //添加用户信息
    public void addUserInfor(User user){
        userDAO.addUser(user);
    }

    //注册用户
    public Map<String,String> register(String username, String password){
        Map<String, String> map = new HashMap<>();
        if(StringUtils.isBlank(username)){
            map.put("msg","用户不能为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }

        String name = userDAO.selectByName(username);
        if(name!=null){
            map.put("msg","用户名已存在");
            return map ;
        }

        User user = new User();
        user.setName(username);
        user.setHeadUrl(String.format("#"));
        user.setPassword(password);
        user.setSalt("#");
        userDAO.addUser(user);

        String ticket = addTicket(user.getId());
        map.put("ticket",ticket);

        return map;
    }
    //注销用户
    public void logout(String ticket){
        loginDAO.updateStatus(1,ticket);
    }

    //验证用户是否已注册
    public Map<String,String> checkUser(String username,String password){
        Map<String, String> map = new HashMap<>();
        boolean result = false;
        User user = new User();
        user = userDAO.selectUserByName(username);

        //查找是否存在此用户
        String name = userDAO.selectByName(username);
        if(name == null){
            map.put("msg","用户名不存在");
            return map ;
        }

        //核对用户名及密码
        if(user.getPassword().equals(password)){
            String ticket = addTicket(user.getId());
            map.put("ticket",ticket);

            return map;
        }else{
            map.put("msg","密码错误，请重新输入");
            return map;
        }
    }

    public String addTicket(int userId){
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date now = new Date();
        now.setTime(3600*24*100+now.getTime());
        loginTicket.setExpired(now);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-",""));
        loginDAO.addTicket(loginTicket);
        return loginTicket.getTicket();
    }
}
