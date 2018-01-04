package main;

import controller.Controller;
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
        loadScene("../autokorrel.fxml","AUTO_KORREL_SCENE");
        loadScene("../crosskorrel.fxml","CROSS_KORREL_SCENE");
        loadScene("../chooseAuto.fxml","CHOOSE_AUTO_SCENE");
        loadScene("../chooseCross.fxml","CHOOSE_CROSS_SCENE");
        loadScene("../main.fxml","MAIN_SCENE");
        SceneSelector.chooseScene("MAIN_SCENE");
    }

    private void loadScene(String resource, String key) throws Exception{
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource(resource));
        Parent root = fxmlLoader.load();
        fxmlLoader.setController(Controller.getInstance());
        SceneSelector.addScene(key,new Scene(root, 1820, 1000));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
