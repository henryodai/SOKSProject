package classes;

import java.util.Stack;

/**
 * Standard pile class that defines the basic behaviour of a pile of cards. Extends 
 * to implement specific pile behaviour.
 * @author Guillaume
 */
public abstract class Pile
{
    /**
     * Defines a constant deck size to allow the whole game to be played with that size
     */
    public static final int DECK_SIZE = 52;
    
    /**
     * The actual pile of cards contained in the pile object.
     */
    protected Stack<Card> cards = new Stack<>();

    /**
     * Basic no arguments constructor
     */
    public Pile(){}
    
    /**
     * Overloaded constructor that allows you to initialize this pile with a 
     * specific set of cards.
     * @param cards The set fo cards to initialize the pile with.
     */
    public Pile(Card[] cards)
    {
        for (Card card : cards)
        {
            this.cards.push(card);
        }
    }
    
    /**
     * Get the top card on the pile without removing it.
     * @return The Card object for the top card on the pile, returns null if the 
     * stack is empty.
     */
    public Card top()
    {
        if (cards.isEmpty())
        {
            return null;
        }
        return cards.peek();
    }

    /**
     * Add a card on the pile of cards.
     * @param card The card to add on the pile.
     */
    public void add(Card card)
    {
        cards.push(card);
    }
    
    /**
     * Remove and return the top card of the pile.
     * @return The Card object for the top card on the pile, returns null if the 
     * stack is empty.
     */
    public Card remove()
    {
        if (cards.isEmpty())
        {
            return null;
        }
        return cards.pop();
    }

    /**
     * Empties the pile of cards.
     */
    public void empty()
    {
        cards.empty();
    }
}
