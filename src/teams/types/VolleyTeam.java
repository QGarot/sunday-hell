package teams.types;

import teams.Team;
import teams.enums.WinState;

public class VolleyTeam extends Team {

    public VolleyTeam() {
        this.setChampionshipScore(0);
    }

    @Override
    public void updateChampionshipScore(WinState winState, int teamScore) {
        this.setChampionshipScore(this.getChampionshipScore() + teamScore);
    }
}
