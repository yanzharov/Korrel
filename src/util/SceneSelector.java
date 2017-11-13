package util;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneSelector {
    private static Stage primaryStage;
    private static Map<String, Scene> scenes = new HashMap<String, Scene>();

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStageArg) {
        primaryStage = primaryStageArg;
    }

    public static Map<String, Scene> getScenes() {
        return scenes;
    }

    public static void addScene(String key, Scene value) {
        scenes.put(key, value);
    }

    public static void chooseScene(String key) {
        primaryStage.setScene(scenes.get(key));
        primaryStage.show();
    }
}
