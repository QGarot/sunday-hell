package manager;

import matches.Match;
import teams.Team;
import teams.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SundayHellManager implements Interaction {
    private final List<Team> teams;
    private final List<Match> matches;
    private final HashMap<String, Class<? extends Team>> teamTypes;
    private final Scanner scanner;

    public SundayHellManager() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.teamTypes = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.loadTeamTypes();
    }

    @Override
    public void addNewTeam() {
        try {
            System.out.println("Type of the new team (foot, futsal, handball, rugby or volley): ");
            String type = this.getScanner().nextLine();
            System.out.println("Name of the new team: ");
            String name = this.getScanner().nextLine();

            if (this.getTeamTypes().containsKey(type)) {
                if (this.getTeamByName(name) == null) {
                    Class<? extends Team> teamClass = this.getTeamTypes().get(type);
                    this.getTeams().add(teamClass.getDeclaredConstructor(String.class).newInstance(name));
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

    @Override
    public void addNewMatch() {
        try {
            // Team A
            System.out.println("Name of the team A: ");
            String nameA = this.getScanner().nextLine();
            System.out.println("Score of the team A: ");
            int scoreA = this.getScanner().nextInt();
            this.getScanner().nextLine();

            // Team B
            System.out.println("Name of the team B: ");
            String nameB = this.getScanner().nextLine();
            System.out.println("Score of the team B: ");
            int scoreB = this.getScanner().nextInt();
            this.getScanner().nextLine();

            // check validity
            Team teamA = this.getTeamByName(nameA);
            Team teamB = this.getTeamByName(nameB);
            if (teamB != null && teamA != null) {
                if (teamA.getClass() == teamB.getClass()) {
                    Match match = new Match(teamA, teamB, scoreA, scoreB);
                    match.updateChampionshipScores();
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

    public HashMap<String, Class<? extends Team>> getTeamTypes() {
        return this.teamTypes;
    }

    /**
     * Load all team types
     */
    public void loadTeamTypes() {
        this.getTeamTypes().put("foot", FootballTeam.class);
        this.getTeamTypes().put("futsal", FutsalTeam.class);
        this.getTeamTypes().put("handball", HandballTeam.class);
        this.getTeamTypes().put("rugby", RugbyTeam.class);
        this.getTeamTypes().put("volley", VolleyTeam.class);
    }

    public List<Match> getMatches() {
        return this.matches;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Get a team by its name
     * @param name: name of the team searched
     * @return team with the given name. null if it was not found.
     */
    public Team getTeamByName(String name) {
        for (Team team: this.getTeams()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }
}
