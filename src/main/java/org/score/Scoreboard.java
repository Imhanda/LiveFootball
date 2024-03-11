//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.score;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores the list of Live matches.
 */
public class Scoreboard {
    private List<Match> matches = new ArrayList();

    public Scoreboard() {
    }

    /**
     * This method adds a match to the scoreboard.
     *
     * @param match - Match object to be added to the Scoreboard
     */
    public void addMatch(Match match) {
        this.matches.add(match);
    }

    /**
     * This method removes am ended match from the scoreboard.
     *
     * @param match - Match object to be removed to the Scoreboard
     */
    public void endMatch(Match match) {
        this.matches.remove(match);
    }

    /**
     * This method sorts the live matches in order of the total goals scored in the match in descending order.
     * If there is a tie, the match with started recently is show up.
     *
     * @return matches
     */
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
