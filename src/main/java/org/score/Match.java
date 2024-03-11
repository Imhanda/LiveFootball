package org.score;

import java.sql.Timestamp;

/**
 * This class stores the home & away team objects and their match start time.
 */
public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private Timestamp createdAt;

    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public String displayScore() {
        String var10000 = this.homeTeam.getName();
        return var10000 + ": " + this.homeTeam.getScore() + " - " + this.awayTeam.getName() + ": " + this.awayTeam.getScore();
    }

    public int getTotalGoals() {
        return this.homeTeam.getScore() + this.awayTeam.getScore();
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Team getHomeTeam() {
        return this.homeTeam;
    }

    public Team getAwayTeam() {
        return this.awayTeam;
    }
}
