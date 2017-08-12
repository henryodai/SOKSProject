package classes;

/**
 * Defines a predefined setup for any game, it allows to wuickly setupe or game
 * with predefined values.
 * @author Guillaume
 */
public class Setup
{
    /**
     * A list of predefined players for the game.
     */
    private final Player[] players;
    
    /**
     * A preset deck for the game.
     */
    private final CardPile deck;
    
    /**
     * A preset discard pile for the game.
     */
    private final DiscardPile discardPile;
    
    /**
     * The class' constructor that sets all internal attributes.
     * @param players The predefined array of players.
     * @param deck The predefined deck.
     * @param discardPile The predefined discard pile.
     */
    public Setup(Player[] players, CardPile deck, DiscardPile discardPile)
    {
        this.players = players;
        this.deck = deck;
        this.discardPile = discardPile;
    }
    
    /**
     * The the predefined array of players.
     * @return The predefined array of players
     */
    public Player[] getPlayers()
    {
        return players;
    }
    
    /**
     * Get the predefiend deck.
     * @return The predefined deck
     */
    public CardPile getDeck()
    {
        return deck;
    }
    
    /**
     * get the predefined discard pile.
     * @return The predefined discard pile
     */
    public DiscardPile getDiscardPile()
    {
        return discardPile;
    }
}
