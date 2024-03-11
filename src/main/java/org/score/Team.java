package org.score;

/**
 * This class stores the team name and the goals scored by them.
 */
public class Team {
    private String name;
    private int score;

    public Team(String name) {
        this.name = name;
        this.score = 0;
    }

    public void scoreGoal() {
        ++this.score;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }
}
