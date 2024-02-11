import matches.IMatch;
import matches.Match;
import teams.types.FootballTeam;

public class Main {
    public static void main(String[] args) {
        // TODO: set of tests
        FootballTeam footTeam1 = new FootballTeam();
        footTeam1.setName("PSG");
        FootballTeam footTeam2 = new FootballTeam();
        footTeam2.setName("OM");

        IMatch match1 = new Match(footTeam1, footTeam2, 5, 2);
        match1.displayInformation();
        System.out.println();
        IMatch match2 = new Match(footTeam1, footTeam2, 0, 0);
        match2.displayInformation();
    }
}