package com.mindcastle.backend;

import java.util.List;

public class Question {
    private final String id;
    private final String question;
    private final List<String> options;
    private final int correctOptionIndex;
    private final String monumentId;
    private final String imageUrl;
    private String explanation;

    public Question(String id, String question, List<String> options, int correctOptionIndex, String monumentId, String imageUrl, String explanation) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.monumentId = monumentId;
        this.imageUrl = imageUrl;
        this.explanation = explanation;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public String getMonumentId() {
        return monumentId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean checkAnswer(int selectedOptionIndex) {
        return selectedOptionIndex == correctOptionIndex;
    }

    public String getExplanation() {
        return explanation;
    }
}