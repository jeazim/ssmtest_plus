package com.atzhi.dao;

import com.atzhi.pojo.School;

import java.util.List;


public interface SchoolDao {
    School selectSchoolNameById(int id);

    List<School> selectAll();
}
