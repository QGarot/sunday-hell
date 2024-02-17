package teams.types;

import teams.Team;
import teams.enums.WinState;
import teams.interfaces.IAttackBonus;

public class FutsalTeam extends Team implements IAttackBonus {
    private final static int winPoints = 3;
    private final static int drawPoints = 1;
    // Bonus
    private final static int attackBonus = 1;
    private final static int minScoreForBonus = 5;

    public FutsalTeam(String name) {
        this.setChampionshipScore(0);
        this.setName(name);
    }

    public FutsalTeam(String name, int championshipScore) {
        this.setChampionshipScore(championshipScore);
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

    @Override
    public void addAttackBonus(int score) {
        if (score >= minScoreForBonus) {
            this.setChampionshipScore(this.getChampionshipScore() + attackBonus);
        }
    }
}
