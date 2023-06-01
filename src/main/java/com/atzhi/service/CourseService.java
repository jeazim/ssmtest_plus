package com.atzhi.service;

import com.atzhi.pojo.Course;
import com.atzhi.pojo.User;

import java.util.List;



public interface CourseService {

    Boolean insertAutoId(Course course);
    Boolean deleteById(Integer id);
    List<Course> selectAll();
    Course selectById(Integer id);
    Boolean update(Course course);
    List<Course> selectByscName(String name);
    String selectImage(Integer id);
    List<Course> selectByCondition(Course course);
}
