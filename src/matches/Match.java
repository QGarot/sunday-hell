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

    /**
     * Update the number of matches of each teams
     */
    public void updateNbMatches() {
        this.getTeamA().setNbMatches(this.getTeamA().getNbMatches() + 1);
        this.getTeamB().setNbMatches(this.getTeamB().getNbMatches() + 1);
    }

    /**
     * Update the averages of matches of each teams
     */
    public void updateAverages() {
        this.updateNbMatches();
        int n;

        // team A
        n = this.getTeamA().getNbMatches();
        this.getTeamA().setAverage((1.0f / n) * (this.getScoreA() - this.getScoreB() + (n - 1) * this.getTeamA().getAverage()));

        // team B
        n = this.getTeamB().getNbMatches();
        this.getTeamB().setAverage((1.0f / n) * (this.getScoreB() - this.getScoreA() + (n - 1) * this.getTeamB().getAverage()));
    }

    @Override
    public void updateChampionshipScores() {
        // Update scores
        if (this.getScoreA() > this.getScoreB()) {
            this.getTeamA().updateChampionshipScore(WinState.WIN, this.getScoreA());
            this.getTeamB().updateChampionshipScore(WinState.LOSE, this.getScoreB());
        } else if (this.getScoreA() == this.getScoreB()) {
            this.getTeamA().updateChampionshipScore(WinState.DRAW, this.getScoreA());
            this.getTeamB().updateChampionshipScore(WinState.DRAW, this.getScoreB());
        } else {
            this.getTeamA().updateChampionshipScore(WinState.LOSE, this.getScoreA());
            this.getTeamB().updateChampionshipScore(WinState.WIN, this.getScoreB());
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
