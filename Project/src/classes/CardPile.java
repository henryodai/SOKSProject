
package classes;

/**
 * Basic pile of card that should represent any deck of cards.
 * @author Guillaume
 */
public class CardPile extends Pile
{
    /**
     * Basic no arguments constructor
     */
    public CardPile(){}
    
    /**
     * Overloaded constructor that allows you to initialize this pile with a 
     * specific set of cards.
     * @param cards The set fo cards to initialize the pile with.
     */
    public CardPile(Card[] cards)
    {
        super(cards);
    }
}
