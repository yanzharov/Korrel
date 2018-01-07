package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import util.ChartDrawer;
import util.FileOpener;
import util.Parser;
import util.SceneSelector;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
  @FXML
  public LineChart chooseAutoChart;
  @FXML
  public LineChart autoKorrelChart1;
  @FXML
  public LineChart autoKorrelChart3;
  @FXML
  public LineChart autoKorrelChart2;
  @FXML
  public LineChart chooseCrossChart1;
  @FXML
  public LineChart chooseCrossChart2;
  @FXML
  public LineChart crossKorrelChart1;
  @FXML
  public LineChart crossKorrelChart2;
  @FXML
  public LineChart crossKorrelChart3;

  public void moveFromMainToChooseAuto(MouseEvent mouseEvent) {
      ChartDrawer.drawImpuls(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart);
      SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
    ChartDrawer.drawImpuls(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1);
    ChartDrawer.drawImpuls(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2);
    SceneSelector.chooseScene("CHOOSE_CROSS_SCENE");
  }

  public void moveFromAutoKorrelToChooseAuto(ActionEvent actionEvent) {
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromCrossKorrelToChooseCross(ActionEvent actionEvent) {
    SceneSelector.chooseScene("CHOOSE_CROSS_SCENE");
  }

  public void moveFromChooseAutoToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseAutoToAutoKorrel(ActionEvent actionEvent) {
    ChartDrawer.drawImpuls(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1);
    ChartDrawer.drawImpuls(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2);
    ChartDrawer.drawImpuls(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3);
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    ChartDrawer.drawImpuls(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1);
    ChartDrawer.drawImpuls(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2);
    ChartDrawer.drawImpuls(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3);
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

  public void chooseAutoChart(ActionEvent actionEvent) {
    File file= FileOpener.openFile();
    Parser.parseFile(file);
    ChartDrawer.drawImpuls(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart);
  }
}
