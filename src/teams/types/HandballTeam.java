package teams.types;

import teams.Team;
import teams.enums.WinState;

public class HandballTeam extends Team {
    public final static int winPoints = 2;
    public final static int drawPoints = 1;

    public HandballTeam() {
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
