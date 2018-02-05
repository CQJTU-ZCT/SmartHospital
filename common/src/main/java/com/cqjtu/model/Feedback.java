package com.cqjtu.model;

public class Feedback {
    private String feedbackId;

    private Integer feedbackStatusId;

    private String title;

    private String description;

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId == null ? null : feedbackId.trim();
    }

    public Integer getFeedbackStatusId() {
        return feedbackStatusId;
    }

    public void setFeedbackStatusId(Integer feedbackStatusId) {
        this.feedbackStatusId = feedbackStatusId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}