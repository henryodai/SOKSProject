package project;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

class Browser extends Region
{
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final ProjectApp app = new ProjectApp();
    
    public Browser()
    {
        //apply the styles
        getStyleClass().add("browser");
        // process page loading
        JSObject win = (JSObject) webEngine.executeScript("window");
        win.setMember("app", app);
        //Load after ready too
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            switch (newState) {
                //Doing this in any state other than SUCCEEDED doesn't seem to do anything.
                case SUCCEEDED:
                    JSObject jsobj = (JSObject) webEngine.executeScript("window");
                    jsobj.setMember("app", app);
                    break;
            }
        });
        // load the web page
        webEngine.load(getClass().getResource("html/index.html").toString());
        //add the web view to the scene
        getChildren().add(browser);
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

