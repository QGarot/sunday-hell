package boot;

import matches.Match;
import teams.Team;
import teams.types.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class SundayHell implements IBoot {
    private final List<Team> teams;
    private final List<Match> matches;
    private final HashMap<String, Class<? extends Team>> teamTypes;
    private final Scanner scanner;
    private static final String fileSeparator = ";";

    public SundayHell() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.teamTypes = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.loadTeamTypes();
        this.loadTeams();
    }

    /**
     * Save team in the teams file
     * @param teamType:
     * @param teamName:
     */
    private void saveTeam(String teamType, String teamName) {
        try {
            FileWriter teams = new FileWriter("files/teams", true);
            teams.write(teamType + fileSeparator + teamName + "\n");
            teams.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Load all teams registered in teams file
     */
    private void loadTeams() {
        try {
            File teams = new File("files/teams");
            Scanner reader = new Scanner(teams);
            String line;
            String[] splitLine;
            String teamType;
            String teamName;

            while (reader.hasNextLine()) {
                line = reader.nextLine();
                // Required format: "team type;team name"
                if (line.contains(fileSeparator)) {
                    splitLine = line.split(fileSeparator);
                    // Required format for splitLine : [team type, team name]
                    if (splitLine.length == 2) {
                        teamType = splitLine[0];
                        teamName = splitLine[1];
                        if (this.getTeamTypes().containsKey(teamType)) {
                            Class<? extends Team> teamClass = this.getTeamTypes().get(teamType);
                            this.getTeams().add(teamClass.getDeclaredConstructor(String.class).newInstance(teamName));
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

            System.out.println(this.getTeams().size() + " teams loaded!");
            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Allow the user to add a new team
     */
    private void addNewTeam() {
        try {
            // Get team data
            System.out.println("Type of the new team (foot, futsal, handball, rugby or volley): ");
            String type = this.getScanner().nextLine();
            System.out.println("Name of the new team: ");
            String name = this.getScanner().nextLine();

            // If data are correct, add the team to the collection
            if (this.getTeamTypes().containsKey(type)) {
                if (this.getTeamByName(name) == null) {
                    Class<? extends Team> teamClass = this.getTeamTypes().get(type);
                    this.getTeams().add(teamClass.getDeclaredConstructor(String.class).newInstance(name));
                    this.saveTeam(type, name);
                    System.out.println(name + "'s team added successfully!");
                } else {
                    System.out.println(name + "'s team already exists!");
                }
            } else {
                System.out.println("Unknown team type!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Allow the user to register a new match (teams & scores)
     */
    private void addNewMatch() {
        try {
            // Get team A data
            System.out.println("Name of the team A: ");
            String nameA = this.getScanner().nextLine();
            System.out.println("Score of the team A: ");
            int scoreA = this.getScanner().nextInt();
            this.getScanner().nextLine(); // \n

            // Get team B data
            System.out.println("Name of the team B: ");
            String nameB = this.getScanner().nextLine();
            System.out.println("Score of the team B: ");
            int scoreB = this.getScanner().nextInt();
            this.getScanner().nextLine(); // \n

            // Check validity
            Team teamA = this.getTeamByName(nameA);
            Team teamB = this.getTeamByName(nameB);
            if (teamB != null && teamA != null) {
                if (teamA.getClass() == teamB.getClass()) {
                    // Create match instance and update teams scores
                    Match match = new Match(teamA, teamB, scoreA, scoreB);
                    match.updateChampionshipScores();
                    // Add it to the collection
                    this.getMatches().add(match);
                    System.out.println("New match added! Championship scores are updated!");
                } else {
                    System.out.println("This two teams can not face!");
                }
            } else {
                System.out.println("One of the given team is not registered!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        boolean launched = true;

        while (launched) {
            System.out.println("What do you want to do? Please select the corresponding number : ");
            System.out.println("1. Register a new team");
            System.out.println("2. Register a new match");
            System.out.println("3. Display information");
            System.out.println("4. Exit");
            String choice = this.getScanner().nextLine();

            switch (choice) {
                case "1" -> this.addNewTeam();
                case "2" -> this.addNewMatch();
                case "3" -> {
                    for (Match match: this.getMatches()) {
                        match.displayInformation();
                    }
                }
                case "4" -> launched = false;
                default -> System.out.println("Please choose 1, 2 or 3.");
            }
        }

        this.stop();
    }

    @Override
    public void stop() {
        System.out.println("End of the program! Goodbye!");
        this.getScanner().close();
        this.getTeams().clear();
        this.getMatches().clear();
        this.getTeamTypes().clear();
    }

    private HashMap<String, Class<? extends Team>> getTeamTypes() {
        return this.teamTypes;
    }

    /**
     * Load all team types
     * Note: update it if a new team type is created
     */
    private void loadTeamTypes() {
        this.getTeamTypes().put("foot", FootballTeam.class);
        this.getTeamTypes().put("futsal", FutsalTeam.class);
        this.getTeamTypes().put("handball", HandballTeam.class);
        this.getTeamTypes().put("rugby", RugbyTeam.class);
        this.getTeamTypes().put("volley", VolleyTeam.class);
    }

    private List<Match> getMatches() {
        return this.matches;
    }

    private List<Team> getTeams() {
        return this.teams;
    }

    private Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Get a team by its name
     * @param name: name of the team searched
     * @return team with the given name. null if it was not found.
     */
    private Team getTeamByName(String name) {
        for (Team team: this.getTeams()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }
}
