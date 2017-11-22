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
        Parent chooseAutoRoot = FXMLLoader.load(getClass().getResource("../chooseAuto.fxml"));
        SceneSelector.addScene("CHOOSE_AUTO_SCENE",new Scene(chooseAutoRoot, 300, 275));
        Parent chooseCrossRoot = FXMLLoader.load(getClass().getResource("../chooseCross.fxml"));
        SceneSelector.addScene("CHOOSE_CROSS_SCENE",new Scene(chooseCrossRoot, 300, 275));
        Parent mainRoot = FXMLLoader.load(getClass().getResource("../main.fxml"));
        SceneSelector.addScene("MAIN_SCENE",new Scene(mainRoot, 300, 275));
        SceneSelector.chooseScene("AUTO_KORREL_SCENE");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
