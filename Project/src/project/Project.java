package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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