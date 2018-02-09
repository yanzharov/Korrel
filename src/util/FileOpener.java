package util;

import javafx.stage.FileChooser;

import java.io.File;

public class FileOpener {

    public static File openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор файла");
        String absolutePath=new File("").getAbsolutePath();
        File propertyFile=new File(absolutePath);
        fileChooser.setInitialDirectory(propertyFile);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Properties files (*.properties)", "*.properties");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(SceneSelector.getPrimaryStage());
        return file;
    }
}
