package com.droidgeniuslabs.tooltip.Model;

public class ScoreEntry {
    private String name;
    private int score;

    public ScoreEntry() {}
    public ScoreEntry(String name, int score) {
	this.name = name;
	this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
}
