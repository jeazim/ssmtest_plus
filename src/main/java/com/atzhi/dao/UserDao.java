package com.atzhi.dao;

import com.atzhi.pojo.Course;
import com.atzhi.pojo.User;

import java.util.List;

public interface UserDao {
    User selectByUsername(String username);

    List<User> selectAllUsers();

    int addUser(User user);


}
