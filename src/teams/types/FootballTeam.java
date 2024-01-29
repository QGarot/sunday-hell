package teams.types;

import teams.Team;
import teams.enums.WinState;

public class FootballTeam extends Team {
    private final static int winPoints = 3;
    private final static int drawPoints = 1;

    public FootballTeam() {
        this.setChampionshipScore(0);
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
