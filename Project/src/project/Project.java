package project;

import classes.Card;
import classes.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Guillaume
 */
public class Project extends Application
{

    private Scene scene;

    @Override
    public void start(Stage stage)
    {
        // create the scene
        stage.setTitle("Crazy 8");
        stage.setMaximized(true);
        scene = new Scene(new Browser(), 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("html/index.css").toString());
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

class Browser extends Region
{

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    GameModel model;
    
    public Browser()
    {
        try
        {
            //Create the game model
            model = new GameModel(4);
            //apply the styles
            getStyleClass().add("browser");

            // process page loading
            webEngine.getLoadWorker().stateProperty().addListener((ObservableValue
                    <? extends State> ov, State oldState, State newState) ->
            {
                if (newState == State.SUCCEEDED)
                {
                    JSObject win = (JSObject) webEngine.executeScript("window");
                    win.setMember("app", new JavaApp());
                }
            });

            // load the web page
            webEngine.load(getClass().getResource("html/index.html").toString());
            //add the web view to the scene
            getChildren().add(browser);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    // JavaScript interface object
    public class JavaApp
    {
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
                    JsonCard.put("rank", card.getSuit());
                    JsonCard.put("suit", card.getRankString());
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
            Jsondiscard.put("rank", discardCard.getSuit());
            Jsondiscard.put("suit", discardCard.getRankString());
            Jsondiscard.put("image", discardCard.getRankString().toLowerCase() 
                        + "_of_" + discardCard.getSuit().toLowerCase() + ".png");
            Jsondeck.put("rank", deckCard.getSuit());
            Jsondeck.put("suit", deckCard.getRankString());
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

    private Node createSpacer()
    {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override
    protected void layoutChildren()
    {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height)
    {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width)
    {
        return 500;
    }
}
