package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.SceneSelector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        SceneSelector.setPrimaryStage(primaryStage);
        Parent autoKorrelRoot = FXMLLoader.load(getClass().getResource("../autokorrel.fxml"));
        SceneSelector.addScene("AUTO_KORREL_SCENE",new Scene(autoKorrelRoot, 300, 275));
        Parent crossKorrelRoot = FXMLLoader.load(getClass().getResource("../crosskorrel.fxml"));
        SceneSelector.addScene("CROSS_KORREL_SCENE",new Scene(crossKorrelRoot, 300, 275));
        SceneSelector.chooseScene("CROSS_KORREL_SCENE");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
