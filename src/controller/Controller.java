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
      ChartDrawer.drawChart(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart,5,30,5);
      SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
    ChartDrawer.drawChart(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,5,30,5);
    ChartDrawer.drawChart(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2,5,30,5);
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
    ChartDrawer.drawChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1,5,30,5);
    ChartDrawer.drawChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,5,30,5);
    ChartDrawer.drawChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,5,30,5);
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    ChartDrawer.drawChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1,5,30,5);
    ChartDrawer.drawChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,5,30,5);
    ChartDrawer.drawChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,5,30,5);
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

}
