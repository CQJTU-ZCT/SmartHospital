package com.cqjtu.service;

import com.cqjtu.mapperexp.FeedbackMapperExp;
import com.cqjtu.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/28.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapperExp feedbackMapperExp;

    @Override
    public List<Feedback> queryFeedback(Feedback feedback) {
        return feedbackMapperExp.queryFeedback(feedback);
    }

    @Override
    public int addFeedback(Feedback feedback) {
        return feedbackMapperExp.addFeedback(feedback);
    }

    @Override
    public int updateFeedback(Feedback feedback) {
        return feedbackMapperExp.updateFeedback(feedback);
    }
}
