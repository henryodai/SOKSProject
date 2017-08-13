package classes;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class BadPlayer extends Player
{

    public BadPlayer()
    {
        super();
    }
    
	public BadPlayer(Card[] cards)
    {
        super(cards);
    }
 
    /* play a card */ 
    @Override
	public boolean play(DiscardPile discardPile, CardPile drawPile, Player[] players)
	{
        //First, make sure we have any move valid
        if (!hasAnyMoveValid(discardPile))
        {
            //Draw a card
            hand.add(drawPile.remove());
        }
        else
        {
            
            //Simply play the first valid card
            for (Card card : hand.list())
            {
                //Loop through each card to check if any is valid
                if (isMoveValid(discardPile, card))
                {
                    //Discard the first
                    discardPile.add(card);
                    //check if its an eight
                    if (card.rank.equals("8"))
                    {
                        //if yes, we have to also play another card of a random suit
                        discardPile.add(new Card(Card.SUITS[(int)(Math.random() 
                                * Card.SUITS.length)], "8"));
                    }
                    hand.remove(card);
                    break;
                }
            }
        }
        return hand.isEmpty();
	}
}