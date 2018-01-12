package util;

import bean.SignalKeeper;
import controller.Controller;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneSelector {
    private static Stage primaryStage;
    private static Map<String, Scene> scenes = new HashMap<String, Scene>();
    private static Map<String, Controller> controllers=new HashMap<>();
    private static SignalKeeper autoSignalKeeper=new SignalKeeper();
    private static SignalKeeper crossSignalKeeper1=new SignalKeeper();
    private static SignalKeeper crossSignalKeeper2=new SignalKeeper();

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

    public static void addController(String name, Controller controller){
        controllers.put(name,controller);
    }

    public static Controller getController(String name){
        return controllers.get(name);
    }

    public static SignalKeeper getAutoSignalKeeper() {
        return autoSignalKeeper;
    }

    public static void setAutoSignalKeeper(SignalKeeper autoSignalKeeper) {
        SceneSelector.autoSignalKeeper = autoSignalKeeper;
    }

    public static SignalKeeper getCrossSignalKeeper1() {
        return crossSignalKeeper1;
    }

    public static void setCrossSignalKeeper1(SignalKeeper crossSignalKeeper1) {
        SceneSelector.crossSignalKeeper1 = crossSignalKeeper1;
    }

    public static SignalKeeper getCrossSignalKeeper2() {
        return crossSignalKeeper2;
    }

    public static void setCrossSignalKeeper2(SignalKeeper crossSignalKeeper2) {
        SceneSelector.crossSignalKeeper2 = crossSignalKeeper2;
    }
}
