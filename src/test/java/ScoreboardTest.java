import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.score.Match;
import org.score.Scoreboard;
import org.score.Team;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardTest {

    @Test
    public void testDisplayScore() {
        Team homeTeam = new Team("Home");
        Team awayTeam = new Team("Away");
        Match match = new Match(homeTeam, awayTeam);
        assertEquals("Home: 0 - Away: 0", match.displayScore());
    }

    @Test
    public void testHomeTeamGoalWin() {
        Team homeTeam = new Team("Home");
        Team awayTeam = new Team("Away");
        Match match = new Match(homeTeam, awayTeam);
        homeTeam.scoreGoal();
        homeTeam.scoreGoal();
        awayTeam.scoreGoal();
        assertEquals("Home: 2 - Away: 1", match.displayScore());
    }

    @Test
    public void testTotalGoalsForMultipleMatches() {
        Team homeTeam1 = new Team("Home1");
        Team awayTeam1 = new Team("Away1");
        Team homeTeam2 = new Team("Home2");
        Team awayTeam2 = new Team("Away2");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch(new Match(homeTeam1, awayTeam1));
        scoreboard.addMatch(new Match(homeTeam2, awayTeam2));
        // 1st Match
        homeTeam1.scoreGoal();
        homeTeam1.scoreGoal();
        awayTeam1.scoreGoal();
        // 2nd Match
        homeTeam2.scoreGoal();
        awayTeam2.scoreGoal();
        assertEquals(3, scoreboard.getScoreboard().get(0).getTotalGoals());
        assertEquals(2, scoreboard.getScoreboard().get(1).getTotalGoals());
    }

    @Test
    public void testScoreboardSummary() throws IOException, JSONException, InterruptedException {
        Team homeTeam1 = new Team("Home1");
        Team awayTeam1 = new Team("Away1");
        Team homeTeam2 = new Team("Home2");
        Team awayTeam2 = new Team("Away2");
        Team homeTeam3 = new Team("Home3");
        Team awayTeam3 = new Team("Away3");
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addMatch(new Match(homeTeam1, awayTeam1));
        Thread.sleep(100);
        scoreboard.addMatch(new Match(homeTeam2, awayTeam2));
        Thread.sleep(100);
        scoreboard.addMatch(new Match(homeTeam3, awayTeam3));
        // 1st Match
        homeTeam1.scoreGoal();
        homeTeam1.scoreGoal();
        awayTeam1.scoreGoal();
        // 2nd Match
        homeTeam2.scoreGoal();
        awayTeam2.scoreGoal();
        // 3rd Match
        homeTeam3.scoreGoal();
        awayTeam3.scoreGoal();
        System.out.println(new Timestamp(System.currentTimeMillis()));
        List<Match> actualScoreboard = scoreboard.getScoreboard();
        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONAssert.assertEquals(
                getExpectedScoreboard("scoreboard.json"),
                mapper.writeValueAsString(actualScoreboard),
                JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void testScoreboardSummaryWithEndedMatch() throws IOException, JSONException {
        Team homeTeam1 = new Team("Home1");
        Team awayTeam1 = new Team("Away1");
        Team homeTeam2 = new Team("Home2");
        Team awayTeam2 = new Team("Away2");
        Team homeTeam3 = new Team("Home3");
        Team awayTeam3 = new Team("Away3");
        Scoreboard scoreboard = new Scoreboard();
        Match match1 = new Match(homeTeam1, awayTeam1);
        Match match2 = new Match(homeTeam2, awayTeam2);
        Match match3 = new Match(homeTeam3, awayTeam3);
        scoreboard.addMatch(match1);
        scoreboard.addMatch(match2);
        scoreboard.addMatch(match3);
        // 1st Match
        homeTeam1.scoreGoal();
        homeTeam1.scoreGoal();
        awayTeam1.scoreGoal();
        // 2nd Match
        homeTeam2.scoreGoal();
        awayTeam2.scoreGoal();
        // 3rd Match
        homeTeam3.scoreGoal();
        awayTeam3.scoreGoal();
        scoreboard.endMatch(match2);
        List<Match> actualScoreboard = scoreboard.getScoreboard();
        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        JSONAssert.assertEquals(
                getExpectedScoreboard("scoreboard_ended_match.json"),
                mapper.writeValueAsString(actualScoreboard),
                JSONCompareMode.STRICT_ORDER);
    }

    /**
     * Fetch JSON String from resources file
     *
     * @param fileName
     * @return JSON String
     */
    public String getExpectedScoreboard(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}