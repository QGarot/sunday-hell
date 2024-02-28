package teams.types;

import teams.Team;
import teams.enums.WinState;

public class HandballTeam extends Team {
    private final static int winPoints = 2;
    private final static int drawPoints = 1;

    public HandballTeam(String name) {
        this.setChampionshipScore(0);
        this.setNbMatches(0);
        this.setAverage(0);
        this.setName(name);
    }

    public HandballTeam(String name, int championshipScore, int nbMatches, float average) {
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
