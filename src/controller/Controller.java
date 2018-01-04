package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.input.MouseEvent;
import util.ChartDrawer;
import util.SceneSelector;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{

  @FXML
  public LineChart chooseAutoChart;
  private static Controller instance=new Controller();

  public void moveFromMainToChooseAuto(MouseEvent mouseEvent) {
    ChartDrawer.drawChart(chooseAutoChart,5,30,5);
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
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
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

  public static Controller getInstance() {
    return instance;
  }
}
