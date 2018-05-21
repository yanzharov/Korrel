package controller;

import bean.Point;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import util.SceneSelector;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;

public class HelpController implements Initializable{
  private static final String absolutePath=new File("").getAbsolutePath();
  private File[] files;
  @FXML
  Pagination pagination;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    File selectedDirectory = new File(absolutePath+"/help/");

    if (selectedDirectory != null) {
      FilenameFilter filterPng = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return name.toLowerCase().endsWith(".png");
        }
      };
      files = selectedDirectory.listFiles(filterPng);
      pagination.setPageFactory(new Callback<Integer, Node>() {
        @Override
        public Node call(Integer pageIndex) {
          return createPage(pageIndex);
        }
      });
    }
  }

  private VBox createPage(int index){
    ImageView imageView = new ImageView();
    File file = files[index];
    try {
      BufferedImage bufferedImage = ImageIO.read(file);
      Image image = SwingFXUtils.toFXImage(bufferedImage, null);
      imageView.setImage(image);
      imageView.setFitWidth(SceneSelector.getScenes().get("HELP_SCENE").getWidth());
      imageView.setFitHeight(SceneSelector.getScenes().get("HELP_SCENE").getHeight());
      imageView.setPreserveRatio(true);
      imageView.setSmooth(true);
      imageView.setCache(true);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    VBox pageBox = new VBox();
    pageBox.getChildren().add(imageView);
    return pageBox;
  }

  public void moveFromHelpToMainScene(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }
}
