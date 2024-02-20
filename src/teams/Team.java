package teams;

import teams.enums.WinState;

public abstract class Team {

    private int championshipScore;
    private String name;
    private int nbMatches;
    private float average;

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

    /**
     * Get the name
     * @return name: name of the team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set a new name
     * @param name: new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the number of played matches
     * @return nbMatches: number of played matches
     */
    public int getNbMatches() {
        return this.nbMatches;
    }

    /**
     * Set a new number of matches
     * @param n: the value to set
     */
    public void setNbMatches(int n) {
        this.nbMatches = n;
    }

    /**
     * Get the average of points
     * @return average:
     */
    public float getAverage() {
        return this.average;
    }

    /**
     * Set a new average
     * @param average: the value to set
     */
    public void setAverage(float average) {
        this.average = average;
    }
}
