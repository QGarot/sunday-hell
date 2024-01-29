package teams;

import teams.enums.WinState;

public abstract class Team {

    private int championshipScore;

    /**
     * Update the championship score according to the win state and the type of the team.
     * @param winState: 2 if the team wins, 1 if it draws, 0 if it loses.
     * @param teamScore: the championship score update can depend on the team score (int)
     */
    public abstract void updateChampionshipScore(WinState winState, int teamScore);

    /**
     * Get the championship score
     * @return the score to get
     */
    public int getChampionshipScore() {
        return this.championshipScore;
    }

    /**
     * Set a new championship score
     * @param score: new score to set
     */
    public void setChampionshipScore(int score) {
        this.championshipScore = score;
    }
}
