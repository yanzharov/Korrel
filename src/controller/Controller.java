package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import util.*;

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
  @FXML
  public Slider autoSlider;
  @FXML
  public CheckBox autoCheckBox;

  public void moveFromMainToChooseAuto(MouseEvent mouseEvent) {
      ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart,SceneSelector.getAutoSignalKeeper());
      SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,SceneSelector.getCrossSignalKeeper1());
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2, SceneSelector.getCrossSignalKeeper2());
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
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoSlider.getValue();
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1,SceneSelector.getAutoSignalKeeper());
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper());
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1,SceneSelector.getCrossSignalKeeper1());
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2());
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),5, SceneSelector.getCrossStrategyKeeper());
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

  public void chooseAutoChart(ActionEvent actionEvent) {
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getAutoSignalKeeper());
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart,SceneSelector.getAutoSignalKeeper());
    SliderCreator.createSlider(SceneSelector.getController("AUTO_KORREL_SCENE").autoSlider,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper());
  }

  public void chooseCrossChart1(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper1());
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,SceneSelector.getCrossSignalKeeper1());
  }

  public void chooseCrossChart2(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper2());
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2,SceneSelector.getCrossSignalKeeper2());
  }

  public void chooseStepAuto(MouseEvent mouseEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoSlider.getValue();
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
  }

  public void autoChangeStrategy(ActionEvent actionEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoSlider.getValue();
    SceneSelector.getAutoStrategyKeeper().setStepStrategy(SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.isSelected());
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      SceneSelector.getAutoStrategyKeeper().setDefaultBegin(SceneSelector.getAutoSignalKeeper().getBegin());
      SceneSelector.getAutoStrategyKeeper().setDefaultEnd(SceneSelector.getAutoSignalKeeper().getEnd());
      autoSlider.setDisable(true);
      ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
    }
    else{
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      autoSlider.setDisable(false);
      ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper());
      ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
    }
  }

  public void moveAutoSignal(KeyEvent keyEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoSlider.getValue();

    if(!SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      return;
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("RIGHT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()+step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()+step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, true, true);
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("LEFT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()-step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()-step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, false, true);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper());
  }
}
