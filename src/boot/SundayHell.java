package boot;

import files.MatchesFile;
import files.TeamsFile;
import matches.Match;
import teams.Team;
import teams.types.*;

import java.util.*;

public class SundayHell implements IBoot {
    private final List<Team> teams;
    private final HashMap<String, Class<? extends Team>> teamTypes;
    private final Scanner scanner;
    // Files
    private final TeamsFile teamsFile;
    private final MatchesFile matchesFile;

    public SundayHell() {
        this.teamTypes = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.teamsFile = new TeamsFile();
        this.matchesFile = new MatchesFile();
        // Load available types & registered teams
        this.loadTeamTypes();
        this.teams = this.getTeamsFile().getRegisteredTeams(this.getTeamTypes());
    }

    // getters

    /**
     * Get teams file
     * @return :
     */
    private TeamsFile getTeamsFile() {
        return this.teamsFile;
    }

    /**
     * Get matches file
     * @return :
     */
    private MatchesFile getMatchesFile() {
        return this.matchesFile;
    }

    /**
     * Get teams
     * @return :
     */
    private List<Team> getTeams() {
        return this.teams;
    }

    /**
     * Get console scanner
     * @return :
     */
    private Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Get team types
     * @return :
     */
    private HashMap<String, Class<? extends Team>> getTeamTypes() {
        return this.teamTypes;
    }

    // getters (end section)

    /**
     * Let the user add a new team
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
                    this.getTeamsFile().registerTeam(type, name);
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
     * Let the user register a new match (teams & scores)
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
                    Match match = new Match(teamA, teamB, scoreA, scoreB);
                    match.updateChampionshipScores();
                    match.updateAverages();
                    // Save teams data
                    this.getTeamsFile().saveTeam(teamA);
                    this.getTeamsFile().saveTeam(teamB);
                    // Add it to the registered matches
                    this.getMatchesFile().registerMatch(match);
                    System.out.println("New match added successfully! Championship scores are updated!");
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
            System.out.println("3. Display teams information");
            System.out.println("4. Exit");
            String choice = this.getScanner().nextLine();

            switch (choice) {
                case "1" -> this.addNewTeam();
                case "2" -> this.addNewMatch();
                case "3" -> {
                    for (Team team: this.getTeams()) {
                        System.out.println(team.getName() + ": " + team.getChampionshipScore() + " points, " + team.getAverage() + " (average)");
                    }
                }
                case "4" -> launched = false;
                default -> System.out.println("Please choose 1, 2, 3 or 4.");
            }
        }

        this.stop();
    }

    @Override
    public void stop() {
        System.out.println("End of the program! Goodbye!");
        this.getScanner().close();
        this.getTeams().clear();
        this.getTeamTypes().clear();
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
