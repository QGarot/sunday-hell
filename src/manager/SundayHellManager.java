package manager;

import matches.Match;
import teams.Team;
import teams.types.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SundayHellManager implements Interaction {
    private final List<Team> teams;
    private final List<Match> matches;
    private final Scanner scanner;

    public SundayHellManager() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    @Override
    public void addNewTeam() {
        try {
            System.out.println("Type of the new team (foot, futsal, handball, rugby or volley): ");
            String type = this.getScanner().nextLine();
            System.out.println("Name of the new team: ");
            String name = this.getScanner().nextLine();

            switch (type) {
                case "foot" -> this.getTeams().add(new FootballTeam(name));
                case "futsal" -> this.getTeams().add(new FutsalTeam(name));
                case "handball" -> this.getTeams().add(new HandballTeam(name));
                case "rugby" -> this.getTeams().add(new RugbyTeam(name));
                case "volley" -> this.getTeams().add(new VolleyTeam(name));
                default -> System.out.println("Please choose one of these types: (foot, futsal, handball, rugby or volley)");
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
                    System.out.println("Debug: " + teamA.getClass());
                    System.out.println("OK!");
                    // TODO: create match and add it to the collection
                } else {
                    System.out.println("This two teams can not face!");
                }
            } else {
                System.out.println("One of the team given is not registered!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public Team getTeamByName(String name) {
        for (Team team: this.getTeams()) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }
}
