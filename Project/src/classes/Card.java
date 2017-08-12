package classes;

import java.util.Arrays;

public class Card implements Comparable<Card>
{
    /**
     * Constant array of possible cards ranks for a standard deck.
     */
    public final static String[] RANKS =
    {
        "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "Jack", "Queen", "King", "Ace"
    };
    
    /**
     * Coinstant array of possible cards suits for a standrd deck.
     */
    public final static String[] SUITS =
    {
        "Diamonds", "Clubs", "Hearts", "Spades"
    };

    /**
     * The card's suit as a stirng within the SUITS array.
     */
    protected String suit;
    
    /**
     * The card's rank as a string within the RANKS array.
     */
    protected String rank;
    
    /**
     * the card's score to check the player's points.
     */
    protected int score;

    /**
     * creates a card with specified suit and rank
     *
     * @param suit is the suit of the card (must be a string from Card.SUITS)
     * @param rank is the rank of the card (must be a string from Card.RANKS)
     */
    public Card(String suit, String rank)
    {
        this.suit = suit;
        this.rank = rank;
        //Get the cards score thanks to the switch.
        switch (rank)
        {
            //For all cases of Jack, Queen or King
            case "Jack" :
            case "Queen" : 
            case "King" :
            {
                //Since we don't break for either, all three will enter this code.
                score = 10;
                break;
            }
            case "Ace" :
            {
                score = 1;
                break;
            }
            case "8" :
            {
                score = 50;
                break;
            }
            case "2" : 
            case "4" :
            {
                score = 25;
                break;
            }
            case "7" :
            {
                score = 20;
                break;
            }
            default :
            {
                //If not any of those values, get the cards number as its score.
                score = Integer.valueOf(rank);
            }
        }
    }

    /**
     * the numerical representation of the rank of the current card
     * <p>
     * ranks have the numerical values 2 = 2, 3 = 3, ..., 10 = 10 Jack = 11,
     * Queen = 12, King = 13, Ace = 14
     *
     * @return the numerical rank of this card, will be bigger than the 
     * RANKS array length if not found.
     */
    public int getRank()
    {
        return Arrays.binarySearch(RANKS, this.rank) + 2;
    }

    /**
     * the string representation of the rank of the current card
     *
     * @return the string representation of the rank of this card (must be a
     * string from Card.RANKS)
     */
    public String getRankString()
    {
        return this.rank;
    }

    /**
     * the suit of the current card
     *
     * @return the suit of this card (must be a string from Card.SUITS)
     */
    public String getSuit()
    {
        return this.suit;
    }
    
    /**
     * The suit index if the suits array of the current card.
     * @return The suits index of the current card, will be bigger than the 
     * SUITS array length if not found.
     */
    public int getSuitValue()
    {
        return Arrays.binarySearch(SUITS, this.suit);
    }

    /**
     * Overriden comparing method that compares two cards according to their 
     * suits and values.
     * 
     * A card is bigger than another is it has the same suit but a bigger rank, 
     * the same suit and an eigth or a bigger suit.
     * @param other The other card to compare with.
     * @return Will return 0 if both are equals, bigger than 0 if the current 
     * card is bigger than the other and less than 0 if the current card is 
     * smaller than the other.
     */
    @Override
    public int compareTo(Card other)
    {
        //Check if both suits are equal
        if (suit.equals(other.suit))
        {
            if (rank.equals(other.rank))
            {
                return 0;
            }
            else if (rank.equals("8"))
            {
                return 100;
            }
            else if (other.rank.equals("8"))
            {
                return -100;
            }
            return getRank() - other.getRank();
        }
        else
        {
            return getSuitValue() - other.getSuitValue();
        }
    }

    @Override
    public final String toString()
    {
        return this.rank + " of " + this.suit;
    }

}
