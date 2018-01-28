package com.cqjtu.service;

import com.cqjtu.model.Feedback;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/28.
 */
public interface FeedbackService {
    /**
     * 查询反馈
     * @param feedback
     * @return
     */
    List<Feedback> queryFeedback(Feedback feedback);

    /**
     * 添加反馈
     * @param feedback
     * @return
     */
    int addFeedback(Feedback feedback);

    /**
     * 修改反馈
     * @param feedback
     * @return
     */
    int updateFeedback(Feedback feedback);
}
