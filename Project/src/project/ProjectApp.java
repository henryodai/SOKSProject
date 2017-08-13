/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import classes.Card;
import classes.GameModel;
import classes.Player;
import javafx.application.Platform;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Guillaume
 */
public class ProjectApp
{
    private GameModel model;
    
    public ProjectApp()
    {
    }
    
    public void makeGame(String option)
    {
        try
        {
            switch(option)
            {
                case "2":
                {
                    model = new GameModel(2);
                    break;
                }
                case "3":
                {
                    model = new GameModel(3);
                    break;
                }
                case "4":
                {
                    model = new GameModel(4);
                    break;
                }
                default:
                {
                    model = new GameModel(4);
                }
            }
        }
        catch (Exception ex)
        {
        }
    }
    
    public void exit()
    {
        Platform.exit();
    }

    public String getPlayers()
    {
        JSONObject Json = new JSONObject();
        JSONArray players = new JSONArray();
        for (Player player : model.getPlayers())
        {
            JSONObject JsonPlayer = new JSONObject();
            JsonPlayer.put("info", player.toString());
            JsonPlayer.put("isleader", player.getIsLeader());
            JsonPlayer.put("iscurrent", player.equals(model.getCurrentPlayer()));
            JSONArray cards = new JSONArray();
            for (Card card : player.getHand().list())
            {
                JSONObject JsonCard = new JSONObject();
                JsonCard.put("suit", card.getSuit());
                JsonCard.put("rank", card.getRankString());
                JsonCard.put("image", card.getRankString().toLowerCase() 
                        + "_of_" + card.getSuit().toLowerCase() + ".png");
                cards.add(JsonCard);
            }
            JsonPlayer.put("cards", cards);
            players.add(JsonPlayer);
        }
        Json.put("players", players);
        return Json.toString();
    }

    public String getStatus()
    {
        JSONObject Json = new JSONObject();
        JSONObject Jsondiscard = new JSONObject();
        JSONObject Jsondeck = new JSONObject();
        Card discardCard = model.getDiscardPile().top();
        Card deckCard = model.getDeck().top();
        Jsondiscard.put("suit", discardCard.getSuit());
        Jsondiscard.put("rank", discardCard.getRankString());
        Jsondiscard.put("image", discardCard.getRankString().toLowerCase() 
                    + "_of_" + discardCard.getSuit().toLowerCase() + ".png");
        Jsondeck.put("suit", deckCard.getSuit());
        Jsondeck.put("rank", deckCard.getRankString());
        Jsondeck.put("image", deckCard.getRankString().toLowerCase() 
                    + "_of_" + deckCard.getSuit().toLowerCase() + ".png");
        Json.put("discard", Jsondiscard);
        Json.put("deck", Jsondeck);
        return Json.toString();
    }

    public boolean update()
    {
        model.update();
        return model.getIsRunning();
    }
}
