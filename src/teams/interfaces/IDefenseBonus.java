package teams.interfaces;

public interface IDefenseBonus {

    /**
     * Add specific defense bonus according to the team type and the opponent score
     */
    void addDefenseBonus(int score);
}
