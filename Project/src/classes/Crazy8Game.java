package classes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Crazy8Game{

	public static void main(String[] args)
    {
        GameModel model;
        try
        {
            model = new GameModel(3);System.out.println("draw pile is : " + model.getDeck());
            boolean running = true;
            Player winner = null;
            while(running)
            {
                System.out.println("player " + model.getCurrentPlayer());
                System.out.println("draw pile    : " + model.getDeck().top());
                System.out.println("discard pile : " + model.getDiscardPile().top());
                winner = model.update();
                running = model.getIsRunning();
                System.out.println("draw pile    : " + model.getDeck().top());
                System.out.println("discard pile : " + model.getDiscardPile().top());
            }
            System.out.println("winner is player " + winner);
        }
        catch (Exception ex)
        {
            Logger.getLogger(Crazy8Game.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}