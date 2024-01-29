package matches;

import teams.Team;
import teams.enums.WinState;
import teams.interfaces.IAttackBonus;
import teams.interfaces.IDefenseBonus;

public class Match implements IMatch {
    private final Team teamA;
    private final Team teamB;
    private final int scoreA;
    private final int scoreB;

    public Match(Team teamA, Team teamB, int scoreA, int scoreB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreA = scoreA;
        this.scoreB = scoreB;
    }

    @Override
    public void updateChampionshipScores() {
        // Update scores
        if (this.getScoreA() > this.getScoreB()) {
            this.getTeamA().updateChampionshipScore(WinState.WIN);
            this.getTeamB().updateChampionshipScore(WinState.LOSE);
        } else if (this.getScoreA() == this.getScoreB()) {
            this.getTeamA().updateChampionshipScore(WinState.DRAW);
            this.getTeamB().updateChampionshipScore(WinState.DRAW);
        } else {
            this.getTeamA().updateChampionshipScore(WinState.LOSE);
            this.getTeamB().updateChampionshipScore(WinState.WIN);
        }

        // Bonus
        // - For team A
        if (this.getTeamA() instanceof IAttackBonus) {
            ((IAttackBonus) this.getTeamA()).addAttackBonus(this.getScoreA());
        }
        if (this.getTeamA() instanceof IDefenseBonus) {
            ((IDefenseBonus) this.getTeamA()).addDefenseBonus(this.getScoreB());
        }
        // - For team B
        if (this.getTeamB() instanceof IAttackBonus) {
            ((IAttackBonus) this.getTeamB()).addAttackBonus(this.getScoreB());
        }
        if (this.getTeamB() instanceof IDefenseBonus) {
            ((IDefenseBonus) this.getTeamB()).addDefenseBonus(this.getScoreA());
        }
    }

    @Override
    public Team getTeamA() {
        return this.teamA;
    }

    @Override
    public Team getTeamB() {
        return this.teamB;
    }

    @Override
    public int getScoreA() {
        return this.scoreA;
    }

    @Override
    public int getScoreB() {
        return this.scoreB;
    }
}
