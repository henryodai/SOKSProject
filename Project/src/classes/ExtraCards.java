package classes;

/**
 *
 * @author Guillaume
 */
public class ExtraCards extends Player
{
    private int extraCards = 0;
    
    public ExtraCards()
    {
        super();
    }
    
    @Override
    public boolean play(DiscardPile discardPile, CardPile drawPile, Player[] players)
    {
        //First, make sure we have any move valid
        if (!hasAnyMoveValid(discardPile))
        {
            //Draw a card
            hand.add(drawPile.remove());
            //return
            return false;
        }
        Player nextPlayer = getNextPlayer(players);
        //Check if the next player is dangerous, i.e. it has only one card remaining.
        boolean nextPlayerIsDangerous = nextPlayer.hand.size() <= 1;
        if (nextPlayerIsDangerous)
        {
            //Check if we have a power card
            if (!hand.ContainsPowerCard())
            {
                //If not, draw a card
                hand.add(drawPile.remove());
            }
            else
            {
                //If yes, play it to hinder the opponent
                for (Card card : hand.list())
                {
                    //check if its an eight
                    if (card.rank.equals("8"))
                    {
                        //Discard the first
                        discardPile.add(card);
                        //if yes, we have to also play another card of a random suit
                        discardPile.add(new Card(Card.SUITS[(int)(Math.random() 
                                * Card.SUITS.length)], "8"));
                        hand.remove(card);
                        extraCards = Math.max(extraCards - 1, 0);
                        break;
                    }
                }
            }
        }
        //Check if we have no power cards
        else if (extraCards < 1)
        {
            //Always take at least one extra cards at the beginning
            hand.add(drawPile.remove());
            extraCards++;
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
                    extraCards = Math.max(extraCards - 1, 0);
                    break;
                }
            }
        }
        //return if we won.
        return hand.isEmpty();
    }
}
