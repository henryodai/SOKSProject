package classes;

/**
 * Defines an abstract version of any type of player in the game with its basic
 * mandatory behaviour.
 * @author Guillaume
 */
public abstract class Player
{
    /**
     * The player's current hand, represented by a always sorted hand object.
     */
	protected Hand hand;
    
    /**
     * Defines if this player is a leader.
     */
    protected boolean isLeader = false;
	
    /**
     * Basic constructor that sets the empty hand.
     */
    public Player() 
    {
        hand = new Hand();
    }
    
    /**
     * Overloaded constructor that sets the current hand as the given array of cards.
     * @param cards The array to set as the hand.
     */
    public Player(Card[] cards)
    {
        hand = new Hand(cards);
    }
    
    /**
     * Get if the current player is a leader.
     * @return If the player is a leader.
     */
    public boolean getIsLeader()
    {
        return isLeader;
    }
    
    /**
     * sets if this player is a leader.
     * @param isLeader If the player is a leader.
     */
    public void setIsLeader(boolean isLeader)
    {
        this.isLeader = isLeader;
    }
    
    /**
     * Add a card to the player's hand
     * @param card The card to add to the player's hand.
     */
    public void addCardToHand(Card card)
    {
        hand.add(card);
    }
    
    /**
     * Get the size of the current player's hand.
     * @return The size of the hand.
     */
	public int getSizeOfHand()
    {
		return hand.size();
	}
    
    /**
     * Get the current player's hand
     * @return The player's hand.
     */
    public Hand getHand()
    {
        return hand;
    }
    
    /**
     * Checks if the requested card is a valid move in the context of the given
     * discard pile.
     * @param discardPile The discard pile to use for our check.
     * @param card The card that should be played.
     * @return Return if the move is valid.
     */
    public boolean isMoveValid(DiscardPile discardPile, Card card)
    {
        if (card.rank.equals("8"))
        {
            return true;
        }
        else if (card.getSuit().equals(discardPile.top().getSuit()) || 
                 card.getRank() == discardPile.top().getRank())
        {
            return true;
        }
        return false;
    }
    
    /**
     * Check if any card in the player's hand can be player on the given
     * discard pile.
     * @param discardPile The game's discard pile.
     * @return Returns true if there is at least one card than can be played.
     */
    public boolean hasAnyMoveValid(DiscardPile discardPile)
    {
        //Use the stream method and a lambda operation to check if any cards is valid.
        //Is basically transforms the hand of cards into a stream using stream()
        //it the checks if any match within the given lambda. A lambda if a shorthand 
        //for a function, (card) -> something is the same as 
        //public boolean lambda(Card card) {something}
        return hand.list().stream().anyMatch((card) -> (isMoveValid(discardPile, card)));
    }
	
	/**
     * The abstract play method of the player, should be redefined to implement
     * specific playing behaviour.
     * 
     * As a side effect, the player plays a card to top of discard Pile, 
     * possibly taking zero or more cards from the top of the drawPile.
     * @param discardPile The game's discard pile.
     * @param drawPile The game's deck.
     * @param players The array of players in the game.
     * @return Return if the move was a winning move or not.
     */
	public abstract boolean play(DiscardPile discardPile, 
	                             CardPile drawPile, 
								 Player[] players);
    
    @Override
    public String toString()
    {
        StringBuilder text = new StringBuilder();
        text.append(getClass()).append(" {");
        hand.list().forEach((card) ->
        {
            text.append(card.toString()).append(", ");
        });
        text.append(getClass()).append("}");
        return text.toString();
    }
}