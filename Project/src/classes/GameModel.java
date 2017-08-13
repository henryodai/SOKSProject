package classes;

import classes.BadPlayer;
import classes.Card;
import classes.CardPile;
import classes.DiscardPile;
import classes.Pile;
import classes.Player;
import classes.Setup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Game model that manages the whole game for the controller. It holds and 
 * displays relevant data for the view to display.
 * @author Guillaume
 */
public class GameModel
{

    /**
     * Constant minimum of players allowed for a game
     */
    public final static int MIN_PLAYERS = 2;

    /**
     * Constant maximum of players allowed for a game
     */
    public final static int MAX_PLAYERS = 4;

    /**
     * Array of the various player's types classes. it allows us to instanciate
     * them easily when chosing at random.
     */
    public final static Class[] PLAYER_TYPES =
    {
        BadPlayer.class
    };
    
    /**
     * Defines the hand size of all players for the potential amount of players
     * starting at two players. So index 0 is two players, index 1 is three 
     * and so on.
     */
    public final static int[] HAND_SIZE = {7, 5, 5};

    /**
     * The game's deck of cards.
     */
    private CardPile deck;

    /**
     * The game's discrad pile.
     */
    private DiscardPile discardPile;

    /**
     * Array of player in the current game, should be within the boundaries of
     * our constants.
     */
    private Player[] players;

    /**
     * The current leader of the game, set as null on initialization since all
     * players have the same amount of cards.
     */
    private Player leader = null;
    
    /**
     * The index of the currently playing player.
     */
    private int currentPlayerIndex = 0;
    
    /**
     * Informs the game if it is still running or not. Is set at false until 
     * the constructor runs.
     */
    private boolean isRunning = false;

    /**
     * Basic constructor for the class, it will initialize the deck, discard
     * pile and player array randomly. Allows for a normal game to be played.
     * @param numberOfPlayers
     * @throws java.lang.Exception will throw an exception if the number of
     * players is invalid or the defined players types cannot be instanciated.
     */
    public GameModel(int numberOfPlayers) throws Exception
    {
        //Make sure the received number of players is within our constant bounds
        if (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS)
        {
            //Throw a new exception stating that the number of player is invalid.
            //The string message will be displayed in the console when the game crashes.
            throw new IllegalArgumentException(
                    "The number of players must be between " + MIN_PLAYERS
                    + " and " + MAX_PLAYERS);
        }
        //Create a new array of players with our valid number
        players = new Player[numberOfPlayers];
        //Chose a random assortiment of player types for the game
        for (int i = 0; i < numberOfPlayers; i++)
        {
            Constructor constructor = PLAYER_TYPES[(int) (Math.random()
                    * PLAYER_TYPES.length)].getConstructor();
            players[i] = (Player) constructor.newInstance();
        }
        //Create a valid deck of 52 cards
        ArrayList<Card> setupDeck = new ArrayList<>(Pile.DECK_SIZE);
        for (String rank : Card.RANKS)
        {
            for (String suit : Card.SUITS)
            {
                setupDeck.add(new Card(suit, rank));
            }
        }
        //Shuffles the deck to give a feeling of randomness
        Collections.shuffle(setupDeck);
        //Give out each player's hand
        for (int i = 0; i < HAND_SIZE[numberOfPlayers - MIN_PLAYERS]; i++)
        {
            for (Player player : players)
            {
                player.addCardToHand(setupDeck.remove(0));
            }
        }
        //Set our internal deck
        deck = new CardPile(setupDeck.toArray(new Card[setupDeck.size()]));
        //Set our discard pile with the first card in the deck
        discardPile = new DiscardPile(deck.remove());
        //Set the game as running.
        isRunning = true;
    }

    /**
     * Initialize a game aoccridng to the base setup's values. It allows for 
     * quickly preparing a game according to predefined parameters.
     * @param baseSetup The basic setupd object to copy.
     */
    public GameModel(Setup baseSetup)
    {
        //Get our setups's values.
        players = baseSetup.getPlayers();
        deck = baseSetup.getDeck();
        discardPile = baseSetup.getDiscardPile();
        //Set the game as running.
        isRunning = true;
    }
    
    /**
     * Update method that is ran every time a player acts. It return the currently 
     * playing player's state for display purposes.
     * @return Returns the state of the player who played during this update.
     */
    public Player update()
    {
        if (!isRunning)
        {
            isRunning = false;
        }
        //for each update, make the currently playing player play
        Player currentPlayer = players[currentPlayerIndex];
        //Make this player plays and keep the game running if it did not win.
        isRunning = !currentPlayer.play(discardPile, deck, players);
        //increment the currently player
        currentPlayerIndex++;
        //Check if we got to the size of our player array, if yes, set as 0
        if (currentPlayerIndex >= players.length)
        {
            currentPlayerIndex = 0;
        }
        //Reevaluate who the leader is
        reevaluateLeader();
        //Return the player who played, he will be the winner if the game was won.
        return currentPlayer;
    }
    
    /**
     * Reevaluates who the leader player should be according to his hand's size 
     * and sets this player as the leader for itself and the game. Also sets all 
     * other players as not leader.
     * 
     * As a size effect it may set the leader as the first player in the list if
     * no leader existed before and no one is leading.
     */
    private void reevaluateLeader()
    {
        //Run through our players and set our leader
        Player localLeader = leader ==  null ? players[0] : leader;
        for (Player player : players)
        {
            player.setIsLeader(false);
            if (player.getSizeOfHand() < localLeader.getSizeOfHand())
            {
                localLeader = player;
            }
        }
        localLeader.setIsLeader(true);
        leader = localLeader;
    }

    /**
     * Returns the current state of the discard pile.
     * @return Returns the discard pile.
     */
    public DiscardPile getDiscardPile()
    {
        return discardPile;
    }
    
    /**
     * Get the current state of the deck.
     * @return The game's deck.
     */
    public CardPile getDeck()
    {
        return deck;
    }
    
    /**
     * Get who the currently leading player is.
     * @return The leading player.
     */
    public Player getLeader()
    {
        return leader;
    }
    
    /**
     * Get if the game is still running.
     * @return Returns the game's running state.
     */
    public boolean getIsRunning()
    {
        return isRunning;
    }
    
    /**
     * Return the currently playing player
     * @return The current player instance.
     */
    public Player getCurrentPlayer()
    {
        return players[currentPlayerIndex];
    }
    
    /**
     * Get the list of all current players.
     * @return Array of players.
     */
    public Player[] getPlayers()
    {
        return players;
    }
}
