package teams.types;

import teams.Team;
import teams.enums.WinState;
import teams.interfaces.IAttackBonus;
import teams.interfaces.IDefenseBonus;

public class RugbyTeam extends Team implements IAttackBonus, IDefenseBonus {
    private final static int winPoints = 3;
    private final static int drawPoints = 1;
    // Bonus
    private final static int attackBonus = 1;
    private final static int defenseBonus = 1;
    private final static int minScoreForAttackBonus = 30;
    private final static int maxOppScoreForDefenseBonus = 10;

    public RugbyTeam(String name) {
        this.setChampionshipScore(0);
        this.setNbMatches(0);
        this.setAverage(0);
        this.setName(name);
    }

    public RugbyTeam(String name, int championshipScore, int nbMatches, float average) {
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

    @Override
    public void addAttackBonus(int score) {
        if (score > minScoreForAttackBonus) {
            this.setChampionshipScore(this.getChampionshipScore() + attackBonus);
        }
    }

    @Override
    public void addDefenseBonus(int score) {
        if (score < maxOppScoreForDefenseBonus) {
            this.setChampionshipScore(this.getChampionshipScore() + defenseBonus);
        }
    }
}
