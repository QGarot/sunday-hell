package files;

import matches.Match;

import java.io.File;
import java.io.FileWriter;

public class MatchesFile extends File {

    private static final String fileSeparator = ";";

    public MatchesFile() {
        super("files/matches");
    }

    /**
     * Register a new match in the matches file
     * @param match:
     */
    public void registerMatch(Match match) {
        try {
            FileWriter writer = new FileWriter(this, true);
            writer.write(match.getTeamA().getName() + fileSeparator + match.getTeamB().getName() + fileSeparator + match.getScoreA() + fileSeparator + match.getScoreB() + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
