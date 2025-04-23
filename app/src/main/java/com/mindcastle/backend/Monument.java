package com.mindcastle.backend;

public class Monument {
    private final String id;
    private final String name;
    private final String description;
    private final double latitude;
    private final double longitude;
    private final String imageUrl;
    private boolean visited;

    public Monument(String id, String name, String description, double latitude, double longitude, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.visited = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}