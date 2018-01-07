package util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileOpener {

    public static File openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор файла");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Properties files (*.properties)", "*.properties");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(SceneSelector.getPrimaryStage());
        return file;
    }
}
