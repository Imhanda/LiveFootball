//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.score;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    private List<Match> matches = new ArrayList();

    public Scoreboard() {
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public void endMatch(Match match) {
        this.matches.remove(match);
    }

    public List<Match> getScoreboard() {
        this.matches.sort((m1, m2) -> {
            return m1.getTotalGoals() == m2.getTotalGoals() ? m2.getCreatedAt().compareTo(m1.getCreatedAt()) : Integer.compare(m2.getTotalGoals(), m1.getTotalGoals());
        });
        return this.matches;
    }

    public List<Match> getMatches() {
        return this.matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
