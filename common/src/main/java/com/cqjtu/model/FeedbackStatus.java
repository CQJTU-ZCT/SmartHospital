package com.cqjtu.model;

public class FeedbackStatus {
    private Integer feedbackStatusId;

    private String description;

    public Integer getFeedbackStatusId() {
        return feedbackStatusId;
    }

    public void setFeedbackStatusId(Integer feedbackStatusId) {
        this.feedbackStatusId = feedbackStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}