package util;

import bean.Point;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOpener {
    private static final FileChooser fileChooser = new FileChooser();
    private static final String absolutePath=new File("").getAbsolutePath();
    private static final File propertyFile=new File(absolutePath);
    static{
        fileChooser.setTitle("Выбор файла");
        fileChooser.setInitialDirectory(propertyFile);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Properties files (*.properties)", "*.properties");
        fileChooser.getExtensionFilters().add(extFilter);
    }
    public static File openFile(){
        File file = fileChooser.showOpenDialog(SceneSelector.getPrimaryStage());
        return file;
    }
    public static void saveFile(List<Point> points,int begin,int end,int step) throws  IOException{
        File file = fileChooser.showSaveDialog(SceneSelector.getPrimaryStage());
        if(file!=null){
            FileWriter writer = null;
            writer = new FileWriter(file);
            writer.write("BEGIN="+begin+"\n");
            writer.write("END="+end+"\n");
            writer.write("STEP="+step+"\n");
            writer.write("SIGNAL=");
            for(Point point : points) {
                String X = point.getX();
                String Y = point.getY();
                writer.write("("+X+";"+Y+"),");
            }
            writer.close();
        }
    }
}
