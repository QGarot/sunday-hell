package manager;

/**
 * Define some features that will allow the user to interact with the standard input
 */
public interface Interaction {

    /**
     * Allow the user to add a new team
     */
    void addNewTeam();

    /**
     * Allow the user to register a new match (teams & scores)
     */
    void addNewMatch();
}
