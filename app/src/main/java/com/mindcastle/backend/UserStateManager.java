package com.mindcastle.backend;

import java.util.ArrayList;
import java.util.List;

public class UserStateManager {
    private static UserStateManager instance;
    private String username;
    private int totalScore;
    private int currentLevel;
    private List<String> visitedMonumentIds;
    private String currentMonumentId;

    private UserStateManager() {
        username = "";
        totalScore = 0;
        currentLevel = 1;
        visitedMonumentIds = new ArrayList<>();
        currentMonumentId = null;
    }

    public static UserStateManager getInstance() {
        if (instance == null) {
            instance = new UserStateManager();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addPoints(int points) {
        this.totalScore += points;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void incrementLevel() {
        this.currentLevel++;
    }

    public List<String> getVisitedMonumentIds() {
        return visitedMonumentIds;
    }

    public void addVisitedMonument(String monumentId) {
        if (!visitedMonumentIds.contains(monumentId)) {
            visitedMonumentIds.add(monumentId);

            Monument monument = QuestionDataProvider.getInstance().getMonumentById(monumentId);
            if (monument != null) {
                monument.setVisited(true);
            }
        }
    }

    public String getCurrentMonumentId() {
        return currentMonumentId;
    }

    public void setCurrentMonumentId(String currentMonumentId) {
        this.currentMonumentId = currentMonumentId;
    }

    public boolean hasVisitedMonument(String monumentId) {
        return visitedMonumentIds.contains(monumentId);
    }

    public void reset() {
        username = "";
        totalScore = 0;
        currentLevel = 1;
        visitedMonumentIds = new ArrayList<>();
        currentMonumentId = null;

        for (Monument monument : QuestionDataProvider.getInstance().getMonuments()) {
            monument.setVisited(false);
        }
    }
}