package matches;

import teams.Team;

public interface IMatch {
    /**
     * Update the championship score of each team
     */
    void updateChampionshipScores();

    /**
     * Get the team A
     * @return the team A (Team)
     */
    Team getTeamA();

    /**
     * Get the team B
     * @return the team B (Team)
     */
    Team getTeamB();

    /**
     * Get the team A score
     * @return the score (int)
     */
    int getScoreA();

    /**
     * Get the team B score
     * @return the score (int)
     */
    int getScoreB();
}
