package teams.types;

import teams.Team;
import teams.enums.WinState;

public class VolleyTeam extends Team {

    public VolleyTeam(String name) {
        this.setChampionshipScore(0);
        this.setName(name);
    }

    public VolleyTeam(String name, int championShipScore) {
        this.setChampionshipScore(championShipScore);
        this.setName(name);
    }

    @Override
    public void updateChampionshipScore(WinState winState, int teamScore) {
        this.setChampionshipScore(this.getChampionshipScore() + teamScore);
    }
}
