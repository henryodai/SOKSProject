package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Defines any player's hand state, it implements baheviour that ensure's 
 * the player's hand is sorted and is functionnal.
 * @author Guillaume
 */
public class Hand
{
    /**
     * The list of cards in the hand, will be sorted according to two parameters;
     * the current suit and the rank. All cards will be first sorted by their suits
     * an within these suite, cards will be sorted by their ranks (With 8 as firsts).
     */
    private ArrayList<Card> hand = new ArrayList<>();
    
    /**
     * Basic parameterless constructor, does nothing.
     */
    public Hand(){ }
    
    /**
     * Overloaded constructor that allows the hand to be preset to the
     * given array of cards.
     * @param cards Array of cards to set as this player's hand.
     */
    public Hand(Card[] cards)
    {
        //Create a new list of cards
        hand = new ArrayList<>(Arrays.asList(cards));
        //Sort the hand
        Collections.sort(hand);
    }
    
    /**
     * Add a card to the hand while making sure it is always sorted.
     * @param card The card to add to the hand.
     */
    public void add(Card card)
    {
        hand.add(card);
        Collections.sort(hand);
    }
    
    /**
     * Remove and returns the card at the specified index.
     * @param i The index at which to rmeove the card.
     * @return Return the removed card.
     * @throws IndexOutOfBoundsException Will throw an index out of bounds exception
     * with a relevan message if i was beyond the hand's size.
     */
    public Card remove(int i) throws IndexOutOfBoundsException
    {
        if (hand.isEmpty() || i >= hand.size())
        {
            throw new IndexOutOfBoundsException("The request index is beyond the" + 
                    "hand's size, the hand size was " + hand.size());
        }
        Card removed = hand.remove(i);
        Collections.sort(hand);
        return removed;
    }
    
    /**
     * Will try to remove the specified card in the hand, will return either 
     * the removed card or null if it wasn't found.
     * @param cardToRemove The card instance to rmeove form the list.
     * @return Will return the removed car or null if it wasn't found.
     */
    public Card remove(Card cardToRemove)
    {
        boolean wasRemoved = hand.remove(cardToRemove);
        Collections.sort(hand);
        return wasRemoved? cardToRemove : null;
    }
    
    /**
     * Will return the whole hand as an array list.
     * @return Return the whole hand.
     */
    public ArrayList<Card> list()
    {
        return hand;
    }
    
    /**
     * Return if the hand is empty or not.
     * @return If the hand is empty.
     */
    public boolean isEmpty()
    {
        return hand.isEmpty();
    }
    
    /**
     * Will return the hand's size.
     * @return Amount of cards in the hand.
     */
    public int size()
    {
        return hand.size();
    }
}
