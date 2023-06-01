package com.atzhi.service.impl;

import com.atzhi.dao.UserDao;
import com.atzhi.pojo.Course;
import com.atzhi.pojo.User;
import com.atzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User selectByUsername(String username) {
        System.out.println("---------------------------------selectByUsername");
        User user=userDao.selectByUsername(username);
//        System.out.println(userDao);
        return user;
    }
    @Override
    public List<User> selectAllUsers(){
        System.out.println("---------------------------------------selectAllUsers");
        List<User> users=userDao.selectAllUsers();

        return users;
    }

    @Override
    public int addUser(User user){
        System.out.println("---------------------------------------addUser");
        return userDao.addUser(user);
    }

}
