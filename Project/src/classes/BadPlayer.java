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
		discardPile.add(this.hand.remove(0));
		return this.hand.size() == 0;
	}
}