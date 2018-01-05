package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.input.MouseEvent;
import util.ChartDrawer;
import util.SceneSelector;

public class EditChooseAutoController {
  @FXML
  public LineChart chooseAutoChart;

  public void editChooseAutoChart() {
    ChartDrawer.drawChart(chooseAutoChart,5,30,5);
  }

  public void moveFromChooseAutoToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseAutoToAutoKorrel(ActionEvent actionEvent) {
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }
}
