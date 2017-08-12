package classes;

/**
 * Basic discard pile that holds all cards that were discarded.
 * @author Guillaume
 */
public class DiscardPile extends Pile
{
    /**
     * Basic no arguments constructor
     */
    public DiscardPile(){}
    
    /**
     * Overloaded constructor that allows you to initialize this pile with a 
     * specific set of cards.
     * @param cards The set of cards to initialize the pile with.
     */
    public DiscardPile(Card[] cards)
    {
        super(cards);
    }
    
    /**
     * Overloaded constructor that allows you to initialize this pile with only
     * one card on top of it.
     * @param topCard The card to put on the top of the discard pile.
     */
    public DiscardPile(Card topCard)
    {
        super(new Card[]{topCard});
    }
}
