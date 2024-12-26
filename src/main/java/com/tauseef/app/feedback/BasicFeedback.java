package com.tauseef.app.feedback;

import com.tauseef.app.contracts.FeedbackInterface;

public class BasicFeedback implements FeedbackInterface {

    private final String message;

    public BasicFeedback(String message) {
        this.message = message;
    }

    @Override
    public String getFeedback() {
        return message;
    }
}
