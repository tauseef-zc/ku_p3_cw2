package com.tauseef.app.services;

import com.tauseef.app.contracts.FeedbackInterface;

public class RatingService implements FeedbackInterface {

    private final FeedbackInterface feedback;
    private final int rating;

    public RatingService(FeedbackInterface feedback, int rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String getFeedback() {
        return feedback.getFeedback();
    }
}
