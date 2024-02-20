package teams.types;

import teams.Team;
import teams.enums.WinState;

public class FootballTeam extends Team {
    private final static int winPoints = 3;
    private final static int drawPoints = 1;

    public FootballTeam(String name) {
        this.setChampionshipScore(0);
        this.setNbMatches(0);
        this.setAverage(0);
        this.setName(name);
    }

    public FootballTeam(String name, int championshipScore, int nbMatches, float average) {
        this.setChampionshipScore(championshipScore);
        this.setNbMatches(nbMatches);
        this.setAverage(average);
        this.setName(name);
    }

    @Override
    public void updateChampionshipScore(WinState winState, int teamScore) {
        if (winState == WinState.WIN) {
            this.setChampionshipScore(this.getChampionshipScore() + winPoints);
        } else if (winState == WinState.DRAW) {
            this.setChampionshipScore(this.getChampionshipScore() + drawPoints);
        }
    }
}
