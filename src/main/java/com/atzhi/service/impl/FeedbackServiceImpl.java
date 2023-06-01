package com.atzhi.service.impl;

import com.atzhi.dao.CourseDao;
import com.atzhi.dao.FeedbackDao;
import com.atzhi.pojo.Feedback;
import com.atzhi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public List<Feedback> selectchat(){
        List<Feedback> feedbacks=feedbackDao.selectchat();
        return feedbacks;
    }
}
