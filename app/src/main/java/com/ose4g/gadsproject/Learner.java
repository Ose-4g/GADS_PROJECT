package com.ose4g.gadsproject;

public class Learner {
    public String name;
    public int score;
    public int hours;
    public String countries;
    public String badgeURL;

    public Learner(String name, int score, int hours, String countries, String badgeURL) {
        this.name = name;
        this.score = score;
        this.hours = hours;
        this.countries = countries;
        this.badgeURL = badgeURL;
    }
}
