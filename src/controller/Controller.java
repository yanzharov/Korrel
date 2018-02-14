package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
  public ComboBox autoComboBox;
  @FXML
  public CheckBox autoCheckBox;
  @FXML
  public ComboBox crossComboBox;
  @FXML
  public CheckBox crossCheckBox;
  @FXML
  public NumberAxis autoChart3AxisY;
  @FXML
  public NumberAxis crossChart3AxisY;

  public void moveFromMainToChooseAuto(MouseEvent mouseEvent) {
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
    SceneSelector.chooseScene("CHOOSE_CROSS_SCENE");
  }

  public void moveFromAutoKorrelToChooseAuto(ActionEvent actionEvent) {
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()) {
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
      SceneSelector.getAutoStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromCrossKorrelToChooseCross(ActionEvent actionEvent) {
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()) {
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(false);
      SceneSelector.getCrossStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("CROSS_KORREL_SCENE").crossCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("CHOOSE_CROSS_SCENE");
  }

  public void moveFromChooseAutoToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseAutoToAutoKorrel(ActionEvent actionEvent) {
    AxisCreator.createAxis(SceneSelector.getController("AUTO_KORREL_SCENE").autoChart3AxisY,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper());
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1,SceneSelector.getAutoSignalKeeper(), step);
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper(), step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    AxisCreator.createAxis(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart3AxisY
        ,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2());
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1,SceneSelector.getCrossSignalKeeper1(),step);
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2(),step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),step, SceneSelector.getCrossStrategyKeeper());
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

  public void moveFromAutoKorrelToMain(ActionEvent actionEvent) {
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()) {
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
      SceneSelector.getAutoStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromCrossKorrelToMain(ActionEvent actionEvent) {
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()) {
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(false);
      SceneSelector.getCrossStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("CROSS_KORREL_SCENE").crossCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void chooseAutoChart(ActionEvent actionEvent) {
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getAutoSignalKeeper());
    ComboBoxCreator.createComboBox(SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox, SceneSelector.getAutoSignalKeeper().getStep());
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart,SceneSelector.getAutoSignalKeeper(), step);
  }

  public void chooseCrossChart1(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper1());
    int difference=Math.abs(SceneSelector.getCrossSignalKeeper2().getEnd()-SceneSelector.getCrossSignalKeeper1().getEnd());
    ComboBoxCreator.createComboBox(SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox, SceneSelector.getCrossSignalKeeper1().getStep(),SceneSelector.getCrossSignalKeeper2().getStep(),difference);
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,SceneSelector.getCrossSignalKeeper1(), step);
  }

  public void chooseCrossChart2(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper2());
    int difference=Math.abs(SceneSelector.getCrossSignalKeeper2().getEnd()-SceneSelector.getCrossSignalKeeper1().getEnd());
    ComboBoxCreator.createComboBox(SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox, SceneSelector.getCrossSignalKeeper1().getStep(),SceneSelector.getCrossSignalKeeper2().getStep(),difference);
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2,SceneSelector.getCrossSignalKeeper2(), step);
  }

  public void chooseStepAuto(Event mouseEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getAutoSignalKeeper().changeOriginSignal(step);
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1,SceneSelector.getAutoSignalKeeper(), step);
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper(), step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
  }

  public void chooseCrossStep(Event mouseEvent) {
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getCrossSignalKeeper1().changeOriginSignal(step);
    SceneSelector.getCrossSignalKeeper2().changeOriginSignal(step);
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1,SceneSelector.getCrossSignalKeeper1(), step);
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2(), step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),step, SceneSelector.getCrossStrategyKeeper());
  }

  public void autoChangeStrategy(ActionEvent actionEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getAutoStrategyKeeper().setStepStrategy(SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.isSelected());
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      SceneSelector.getAutoStrategyKeeper().copySignal(SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(), step);
      SceneSelector.getAutoStrategyKeeper().setDefaultBegin(SceneSelector.getAutoSignalKeeper().getBegin());
      SceneSelector.getAutoStrategyKeeper().setDefaultEnd(SceneSelector.getAutoSignalKeeper().getEnd());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(true);
    }
    else{
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper(), step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
  }

  public void crossChangeStrategy(ActionEvent actionEvent) {
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getCrossStrategyKeeper().setStepStrategy(SceneSelector.getController("CROSS_KORREL_SCENE").crossCheckBox.isSelected());
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()){
      SceneSelector.getCrossStrategyKeeper().copySignal(SceneSelector.getCrossSignalKeeper1(), SceneSelector.getCrossSignalKeeper2(), step);
      SceneSelector.getCrossStrategyKeeper().setDefaultBegin(SceneSelector.getCrossSignalKeeper2().getBegin());
      SceneSelector.getCrossStrategyKeeper().setDefaultEnd(SceneSelector.getCrossSignalKeeper2().getEnd());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(true);
    }
    else{
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2(), step);
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),step, SceneSelector.getCrossStrategyKeeper());
  }

  public void moveAutoSignal(KeyEvent keyEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();

    if(!SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      return;
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("RIGHT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()+step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()+step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(), SceneSelector.getAutoStrategyKeeper(), step, true, true);
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("LEFT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()-step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()-step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(), SceneSelector.getAutoStrategyKeeper(), step, false, true);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper(), step);
  }

  public void moveCrossSignal(KeyEvent keyEvent) {
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();

    if(!SceneSelector.getCrossStrategyKeeper().isStepStrategy()){
      return;
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("RIGHT")){
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossSignalKeeper2().getBegin()+step);
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossSignalKeeper2().getEnd()+step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(), SceneSelector.getCrossStrategyKeeper(), step, true, false);
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("LEFT")){
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossSignalKeeper2().getBegin()-step);
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossSignalKeeper2().getEnd()-step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(), SceneSelector.getCrossStrategyKeeper(), step, false, false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2(), step);
  }
}
