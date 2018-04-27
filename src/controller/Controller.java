package controller;

import bean.SignalKeeper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
  @FXML
  public NumberAxis autoChart1AxisX;
  @FXML
  public NumberAxis autoChart2AxisX;
  @FXML
  public NumberAxis autoChart3AxisX;
  @FXML
  public NumberAxis crossChart1AxisX;
  @FXML
  public NumberAxis crossChart2AxisX;
  @FXML
  public NumberAxis crossChart3AxisX;
  @FXML
  public CheckBox autoKorrelDeleteDiscretsCheckBox;
  @FXML
  public CheckBox crossKorrelDeleteDiscretsCheckBox;

  public void moveFromMainToChooseAuto(MouseEvent mouseEvent) {
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromMainToChooseCross(MouseEvent mouseEvent) {
    SceneSelector.chooseScene("CHOOSE_CROSS_SCENE");
  }

  public void moveFromAutoKorrelToChooseAuto(ActionEvent actionEvent) {
    SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().select(0);
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()) {
      SceneSelector.getController("AUTO_KORREL_SCENE").autoChart3AxisY.setAutoRanging(true);
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoChart2AxisX,SceneSelector.getAutoSignalKeeper());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
      SceneSelector.getAutoStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("CHOOSE_AUTO_SCENE");
  }

  public void moveFromCrossKorrelToChooseCross(ActionEvent actionEvent) {
    SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().select(0);
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()) {
      SceneSelector.getController("CROSS_KORREL_SCENE").crossChart3AxisY.setAutoRanging(true);
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart2AxisX,SceneSelector.getCrossSignalKeeper2());
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
    if(SceneSelector.getAutoSignalKeeper().isChanged()) {
      int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
      ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1, SceneSelector.getAutoSignalKeeper());
      ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2, SceneSelector.getAutoSignalKeeper());
      ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3, SceneSelector.getAutoSignalKeeper(), SceneSelector.getAutoSignalKeeper(), step, SceneSelector.getAutoStrategyKeeper());
      SceneSelector.getAutoSignalKeeper().setChanged(false);
      TooltipSetter.addTooltips(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3);
    }
    SceneSelector.chooseScene("AUTO_KORREL_SCENE");
  }

  public void moveFromChooseCrossToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromChooseCrossToCrossKorrel(ActionEvent actionEvent) {
    if(SceneSelector.getCrossSignalKeeper1().isChanged() || SceneSelector.getCrossSignalKeeper2().isChanged()) {
      int step = (int) SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
      ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1, SceneSelector.getCrossSignalKeeper1());
      ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2, SceneSelector.getCrossSignalKeeper2());
      ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3, SceneSelector.getCrossSignalKeeper1(), SceneSelector.getCrossSignalKeeper2(), step, SceneSelector.getCrossStrategyKeeper());
      SceneSelector.getCrossSignalKeeper1().setChanged(false);
      SceneSelector.getCrossSignalKeeper2().setChanged(false);
      TooltipSetter.addTooltips(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3);
    }
    SceneSelector.chooseScene("CROSS_KORREL_SCENE");
  }

  public void moveFromAutoKorrelToMain(ActionEvent actionEvent) {
    SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().select(0);
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()) {
      SceneSelector.getController("AUTO_KORREL_SCENE").autoChart3AxisY.setAutoRanging(true);
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoChart2AxisX,SceneSelector.getAutoSignalKeeper());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
      SceneSelector.getAutoStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void moveFromCrossKorrelToMain(ActionEvent actionEvent) {
    SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().select(0);
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()) {
      SceneSelector.getController("CROSS_KORREL_SCENE").crossChart3AxisY.setAutoRanging(true);
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart2AxisX,SceneSelector.getCrossSignalKeeper2());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(false);
      SceneSelector.getCrossStrategyKeeper().setStepStrategy(false);
      SceneSelector.getController("CROSS_KORREL_SCENE").crossCheckBox.setSelected(false);
    }
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void chooseAutoChart(ActionEvent actionEvent) {
    File file= FileOpener.openFile();
    if(file==null){
      return;
    }
    Parser.parseFile(file,SceneSelector.getAutoSignalKeeper());
    SceneSelector.getAutoSignalKeeper().setChanged(true);
    int difference=SceneSelector.getAutoSignalKeeper().getDuration();
    ComboBoxCreator.createComboBox(SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox, SceneSelector.getAutoSignalKeeper().getStep(),difference);
    ChartBuilder.buildChart(SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),true);
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_AUTO_SCENE").chooseAutoChart,SceneSelector.getAutoSignalKeeper());
  }

  public void chooseCrossChart1(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    if(file==null){
      return;
    }
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper1());
    SceneSelector.getCrossSignalKeeper1().setChanged(true);
    int difference1=SceneSelector.getCrossSignalKeeper1().getDuration();
    int difference2=SceneSelector.getCrossSignalKeeper2().getDuration();
    ComboBoxCreator.createComboBox(SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox, SceneSelector.getCrossSignalKeeper1().getStep(),SceneSelector.getCrossSignalKeeper2().getStep(),difference1,difference2);
    ChartBuilder.buildChart(SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),false);
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,SceneSelector.getCrossSignalKeeper1());
  }

  public void chooseCrossChart2(ActionEvent actionEvent){
    File file= FileOpener.openFile();
    if(file==null){
      return;
    }
    Parser.parseFile(file,SceneSelector.getCrossSignalKeeper2());
    SceneSelector.getCrossSignalKeeper2().setChanged(true);
    int difference1=SceneSelector.getCrossSignalKeeper1().getDuration();
    int difference2=SceneSelector.getCrossSignalKeeper2().getDuration();
    ComboBoxCreator.createComboBox(SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox, SceneSelector.getCrossSignalKeeper1().getStep(),SceneSelector.getCrossSignalKeeper2().getStep(),difference1, difference2);
    ChartBuilder.buildChart(SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),false);
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2,SceneSelector.getCrossSignalKeeper2());
  }

  public void autoChangeStrategy(ActionEvent actionEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getAutoStrategyKeeper().setStepStrategy(SceneSelector.getController("AUTO_KORREL_SCENE").autoCheckBox.isSelected());
    if(SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      SceneSelector.getController("AUTO_KORREL_SCENE").autoChart3AxisY.setAutoRanging(false);
      SceneSelector.getAutoStrategyKeeper().copySignal(SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper());
      SceneSelector.getAutoStrategyKeeper().setStepStrategyBegin(-SceneSelector.getAutoSignalKeeper().getDuration());
      SceneSelector.getAutoStrategyKeeper().setDefaultBegin(SceneSelector.getAutoSignalKeeper().getBegin());
      SceneSelector.getAutoStrategyKeeper().setDefaultEnd(SceneSelector.getAutoSignalKeeper().getEnd());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getBegin());
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()-SceneSelector.getAutoSignalKeeper().getDuration());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(true);
      SceneSelector.getAutoSignalKeeper().setChanged(true);
    }
    else{
      SceneSelector.getController("AUTO_KORREL_SCENE").autoChart3AxisY.setAutoRanging(true);
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoStrategyKeeper().getDefaultBegin());
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoChart2AxisX,SceneSelector.getAutoSignalKeeper());
      SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.setDisable(false);
      SceneSelector.getAutoSignalKeeper().setChanged(false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper());
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(),step, SceneSelector.getAutoStrategyKeeper());
    TooltipSetter.addTooltips(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3);
  }

  public void crossChangeStrategy(ActionEvent actionEvent) {
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();
    SceneSelector.getCrossStrategyKeeper().setStepStrategy(SceneSelector.getController("CROSS_KORREL_SCENE").crossCheckBox.isSelected());
    if(SceneSelector.getCrossStrategyKeeper().isStepStrategy()){
      SceneSelector.getController("CROSS_KORREL_SCENE").crossChart3AxisY.setAutoRanging(false);
      SceneSelector.getCrossStrategyKeeper().copySignal(SceneSelector.getCrossSignalKeeper1(), SceneSelector.getCrossSignalKeeper2());
      SceneSelector.getCrossStrategyKeeper().setStepStrategyBegin(-Math.abs(SceneSelector.getCrossSignalKeeper2().getEnd()-SceneSelector.getCrossSignalKeeper1().getBegin()));
      SceneSelector.getCrossStrategyKeeper().setDefaultBegin(SceneSelector.getCrossSignalKeeper2().getBegin());
      SceneSelector.getCrossStrategyKeeper().setDefaultEnd(SceneSelector.getCrossSignalKeeper2().getEnd());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossSignalKeeper1().getBegin());
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossSignalKeeper1().getBegin()-SceneSelector.getCrossSignalKeeper2().getDuration());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(true);
      SceneSelector.getCrossSignalKeeper1().setChanged(true);
      SceneSelector.getCrossSignalKeeper2().setChanged(true);
      ChartBuilder.moveChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart2AxisX, SceneSelector.getCrossSignalKeeper2());
    }
    else{
      SceneSelector.getController("CROSS_KORREL_SCENE").crossChart3AxisY.setAutoRanging(true);
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossStrategyKeeper().getDefaultBegin());
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossStrategyKeeper().getDefaultEnd());
      ChartBuilder.returnChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart2AxisX,SceneSelector.getCrossSignalKeeper2());
      SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.setDisable(false);
      SceneSelector.getCrossSignalKeeper1().setChanged(false);
      SceneSelector.getCrossSignalKeeper2().setChanged(false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2());
    ChartDrawer.drawKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),step, SceneSelector.getCrossStrategyKeeper());
    TooltipSetter.addTooltips(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3);
  }

  public void moveAutoSignal(KeyEvent keyEvent) {
    int step=(int)SceneSelector.getController("AUTO_KORREL_SCENE").autoComboBox.getSelectionModel().getSelectedItem();

    if(!SceneSelector.getAutoStrategyKeeper().isStepStrategy()){
      return;
    }
    ChartBuilder.moveChart(SceneSelector.getController("AUTO_KORREL_SCENE").autoChart2AxisX,SceneSelector.getAutoSignalKeeper());
    if(keyEvent.getCode().getName().equalsIgnoreCase("RIGHT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()+step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()+step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(), SceneSelector.getAutoStrategyKeeper(), step, true, true);
      TooltipSetter.addTooltip(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3, step);
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("LEFT")){
      SceneSelector.getAutoSignalKeeper().setBegin(SceneSelector.getAutoSignalKeeper().getBegin()-step);
      SceneSelector.getAutoSignalKeeper().setEnd(SceneSelector.getAutoSignalKeeper().getEnd()-step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3,SceneSelector.getAutoSignalKeeper(),SceneSelector.getAutoSignalKeeper(), SceneSelector.getAutoStrategyKeeper(), step, false, true);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2,SceneSelector.getAutoSignalKeeper());
  }

  public void moveCrossSignal(KeyEvent keyEvent) {
    int step=(int)SceneSelector.getController("CROSS_KORREL_SCENE").crossComboBox.getSelectionModel().getSelectedItem();

    if(!SceneSelector.getCrossStrategyKeeper().isStepStrategy()){
      return;
    }
    ChartBuilder.moveChart(SceneSelector.getController("CROSS_KORREL_SCENE").crossChart2AxisX,SceneSelector.getCrossSignalKeeper2());
    if(keyEvent.getCode().getName().equalsIgnoreCase("RIGHT")){
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossSignalKeeper2().getBegin()+step);
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossSignalKeeper2().getEnd()+step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(), SceneSelector.getCrossStrategyKeeper(), step, true, false);
      TooltipSetter.addTooltip(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3, step);
    }
    if(keyEvent.getCode().getName().equalsIgnoreCase("LEFT")){
      SceneSelector.getCrossSignalKeeper2().setBegin(SceneSelector.getCrossSignalKeeper2().getBegin()-step);
      SceneSelector.getCrossSignalKeeper2().setEnd(SceneSelector.getCrossSignalKeeper2().getEnd()-step);
      ChartDrawer.incrementKorrelSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3,SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(), SceneSelector.getCrossStrategyKeeper(), step, false, false);
    }
    ChartDrawer.drawSignal(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2,SceneSelector.getCrossSignalKeeper2());
  }

  public void deleteAutoKorrelDiscrets(ActionEvent actionEvent) {
    boolean createSymbols=true;
    if(SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelDeleteDiscretsCheckBox.isSelected()){
      createSymbols=false;
    }
    SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart1.setCreateSymbols(createSymbols);
    SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart2.setCreateSymbols(createSymbols);
    SceneSelector.getController("AUTO_KORREL_SCENE").autoKorrelChart3.setCreateSymbols(createSymbols);
  }

  public void deleteCrossKorrelDiscrets(ActionEvent actionEvent) {
    boolean createSymbols=true;
    if(SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelDeleteDiscretsCheckBox.isSelected()){
      createSymbols=false;
    }
    SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart1.setCreateSymbols(createSymbols);
    SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart2.setCreateSymbols(createSymbols);
    SceneSelector.getController("CROSS_KORREL_SCENE").crossKorrelChart3.setCreateSymbols(createSymbols);
  }

  public void createSignal(MouseEvent actionEvent) {
    SceneSelector.chooseScene("CREATE_SCENE");
  }

  public void changeSignals(ActionEvent actionEvent) {
    SignalKeeper signalKeeper1=SceneSelector.getCrossSignalKeeper1();
    SignalKeeper signalKeeper2=SceneSelector.getCrossSignalKeeper2();
    SceneSelector.setCrossSignalKeeper1(signalKeeper2);
    SceneSelector.setCrossSignalKeeper2(signalKeeper1);
    ChartBuilder.buildChart(SceneSelector.getCrossSignalKeeper1(),SceneSelector.getCrossSignalKeeper2(),false);
    SceneSelector.getCrossSignalKeeper1().setChanged(true);
    SceneSelector.getCrossSignalKeeper2().setChanged(true);
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart1,SceneSelector.getCrossSignalKeeper1());
    ChartDrawer.drawSignal(SceneSelector.getController("CHOOSE_CROSS_SCENE").chooseCrossChart2,SceneSelector.getCrossSignalKeeper2());
  }

  public void moveFromMainToHelpScene(MouseEvent mouseEvent) {
    SceneSelector.chooseScene("HELP_SCENE");
  }
}
