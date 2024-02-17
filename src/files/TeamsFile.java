package files;

import teams.Team;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TeamsFile extends File {

    private static final String fileSeparator = ";";

    public TeamsFile() {
        super("files/teams");
    }

    /**
     * Save the given team data into the teams file (edit corresponding line)
     * @param team: the team to save
     */
    public void saveTeam(Team team) {
        try {
            Scanner reader = new Scanner(this);
            StringBuilder newContent = new StringBuilder();
            boolean edited = false;

            String line;
            String[] splitLine;
            String teamType;
            String teamName;
            int championShipScore;

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                // Required format: "team type;team name;championship score"
                if (line.contains(fileSeparator)) {
                    splitLine = line.split(fileSeparator);
                    // Required format for splitLine : [team type, team name, championship score]
                    if (splitLine.length == 3) {
                        teamType = splitLine[0];
                        teamName = splitLine[1];
                        championShipScore = Integer.parseInt(splitLine[2]);
                        // Update the score
                        if (teamName.equals(team.getName())) {
                            championShipScore = team.getChampionshipScore();
                            edited = true;
                        }
                        newContent.append(teamType).
                                append(fileSeparator).
                                append(teamName).
                                append(fileSeparator).
                                append(championShipScore).
                                append("\n");
                    } else {
                        //System.out.println("Invalid format!");
                    }
                } else {
                    //System.out.println("Invalid format!");
                }
            }

            FileWriter writer = new FileWriter(this);
            writer.write(newContent.toString());

            if (!edited) {
                System.out.println("TeamsFile: no changes");
            }

            writer.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Register a new team in the teams file
     * @param teamType:
     * @param teamName:
     */
    public void registerTeam(String teamType, String teamName) {
        try {
            FileWriter writer = new FileWriter(this, true);
            writer.write(teamType + fileSeparator + teamName + fileSeparator + 0 + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get all registered teams in the data file
     * @param types: available types
     * @return a list with all saved teams
     */
    public List<Team> getRegisteredTeams(HashMap<String, Class<? extends Team>> types) {

        List<Team> teams = new ArrayList<>();

        try {
            Scanner reader = new Scanner(this);

            String line;
            String[] splitLine;
            String teamType;
            String teamName;
            int championShipScore;

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                // Required format: "team type;team name;championship score"
                if (line.contains(fileSeparator)) {
                    splitLine = line.split(fileSeparator);
                    // Required format for splitLine : [team type, team name, championship score]
                    if (splitLine.length == 3) {
                        teamType = splitLine[0];
                        teamName = splitLine[1];
                        championShipScore = Integer.parseInt(splitLine[2]);
                        if (types.containsKey(teamType)) {
                            Class<? extends Team> teamClass = types.get(teamType);
                            teams.add(teamClass.getDeclaredConstructor(String.class, int.class).newInstance(teamName, championShipScore));
                            //System.out.println(teamType + ": " + teamName + " is loaded!");
                        } else {
                            //System.out.println(teamType + ": " + teamName + " can not be loaded!");
                        }
                    } else {
                        //System.out.println("Invalid format!");
                    }
                } else {
                    //System.out.println("Invalid format!");
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(teams.size() + " teams loaded!");
        return teams;
    }
}
