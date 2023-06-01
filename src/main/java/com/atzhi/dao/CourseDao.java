package com.atzhi.dao;

import com.atzhi.pojo.Course;

import java.util.List;

public interface CourseDao {
    void insertAutoId(Course course);
    void deleteById(Integer id);
    void update(Course course);
    List<Course> selectAll();
    Course selectById(Integer id);
    List<Course> selectBycName(String name);
    List<Course> selectByscName(String name);
    List<Course> selectByCondition(Course course);
    List<Course> selectByConditionSingle(Course course);
    String selectImage(Integer id);
}
