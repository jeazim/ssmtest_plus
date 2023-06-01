package com.atzhi.service.impl;

import com.atzhi.dao.SchoolDao;
import com.atzhi.pojo.School;
import com.atzhi.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolDao schoolDao;

    @Override
    public School selectSchoolNameById(int id) {
        return schoolDao.selectSchoolNameById(id);
    }

    @Override
    public List<School> selectAll() {
        return schoolDao.selectAll();
    }
}
