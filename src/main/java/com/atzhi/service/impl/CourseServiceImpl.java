package com.atzhi.service.impl;

import com.atzhi.dao.CourseDao;
import com.atzhi.pojo.Course;
import com.atzhi.pojo.User;
import com.atzhi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> selectAll() {
        List<Course> courses=courseDao.selectAll();
        courses.sort(Comparator.comparing(Course::getId));
        return courses;
    }

    @Override
    public Course selectById(Integer id) {
        return courseDao.selectById(id);
    }

    @Override
    public Boolean update(Course course) {
        courseDao.update(course);
        return true;
    }

    @Override
    public List<Course> selectByscName(String name) {
        return courseDao.selectByscName(name);
    }

    @Override
    public Boolean insertAutoId(Course course) {
        courseDao.insertAutoId(course);
        return true;
    }

    @Override
    public Boolean deleteById(Integer id) {
        courseDao.deleteById(id);
        return true;
    }

    @Override
    public String selectImage(Integer id) {
        return courseDao.selectImage(id);
    }

    @Override
    public List<Course> selectByCondition(Course course){
        System.out.println("-----------------------selectByCondition---------------------------");

        return courseDao.selectByCondition(course);
    }
}
