package com.atzhi.service;

import com.atzhi.pojo.School;

import java.util.List;


public interface SchoolService {
    School selectSchoolNameById(int id);
    List<School> selectAll();
}
