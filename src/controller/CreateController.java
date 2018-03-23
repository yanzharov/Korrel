package controller;

import bean.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import util.ChartDrawer;
import util.FileOpener;
import util.SceneSelector;
import util.SignalBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

public class CreateController implements Initializable{
  private ObservableList<Point> pointsData = FXCollections.observableArrayList();
  private double[] signalY;
  private double[] signalX;
  private int begin;
  private int end;
  private double amplitude;
  private String currentSignal;
  @FXML
  public TableView<Point> points;
  @FXML
  private TableColumn<Point, String> coordX;
  @FXML
  private TableColumn<Point, String> coordY;
  @FXML
  public TextField endTextField;
  @FXML
  public TextField beginTextField;
  @FXML
  public TextField amplitudeTextField;
  @FXML
  public LineChart constructedSignalChart;
  @FXML
  public TextField xAddField;
  @FXML
  public TextField yAddField;
  @FXML
  public ChoiceBox signals;

  public void createTable(ActionEvent actionEvent) {
    pointsData.clear();
    double amplitude=0;
    String beginStr=beginTextField.getCharacters().toString();
    String endStr=endTextField.getCharacters().toString();
    String amplitudeStr=amplitudeTextField.getCharacters().toString();
    if(!beginStr.matches("-?[0-9]+")||!endStr.matches("-?[0-9]+")||!amplitudeStr.matches("-?[0-9]+([0-9]+)?")){
      return;
    }
    begin=Integer.valueOf(beginTextField.getCharacters().toString());
    end=Integer.valueOf(endTextField.getCharacters().toString());
    amplitude=Double.valueOf(amplitudeTextField.getCharacters().toString());

    pointsData.addAll(SignalBuilder.buildSignal(signals.getSelectionModel().getSelectedItem().toString(),amplitude,begin,end));

    points.setItems(pointsData);
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    coordX.setCellValueFactory(new PropertyValueFactory<Point, String>("x"));
    coordY.setCellValueFactory(new PropertyValueFactory<Point, String>("y"));
    coordY.setCellFactory(TextFieldTableCell.forTableColumn());
    coordY.setOnEditCommit(
        new EventHandler<TableColumn.CellEditEvent<Point, String>>() {
          @Override
          public void handle(TableColumn.CellEditEvent<Point, String> t) {
            ((Point) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
            ).setY(t.getNewValue());
          }
        }
    );
    String[] signalsStr={"Прямоугольный видеоимпульс","Треугольный видеоимпульс","Прямой треугольный видеоимпульс",
        "Косинусоидальный сигнал","Синусоидальный сигнал","Последовательность прямоугольных видеоимпульсов"};
    signals.getItems().addAll(signalsStr);
    signals.getSelectionModel().select(0);
  }

  public void saveFile(ActionEvent actionEvent) {
    if(begin==end){
      return;
    }
    try {
      FileOpener.saveFile(pointsData, begin, end, 1);
    }
    catch (IOException exception){

    }
  }

  public void constructSignal(ActionEvent actionEvent) {
    if(begin==end){
      return;
    }
    signalY=new double[pointsData.size()];
    signalX=new double[pointsData.size()];
    int currentDiscret=0;
    for(Point point:pointsData){
      signalY[currentDiscret]=Double.valueOf(point.getY());
      signalX[currentDiscret]=Double.valueOf(point.getX());
      currentDiscret++;
    }
    ChartDrawer.drawSignal(constructedSignalChart,begin,end,signalY, signalX,1);
  }

  public void moveToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }

  public void addPoint(ActionEvent actionEvent) {
    int index=0;
    int prevIndex=0;
    String xCoord=xAddField.getCharacters().toString();
    String yCoords=yAddField.getCharacters().toString();
    if(!xCoord.matches("-?[0-9]+")||!yCoords.matches("-?[0-9]+([0-9]+)?")){
      return;
    }
    ListIterator<Point> iterator=pointsData.listIterator();
    if(Integer.valueOf(xCoord)>end){
      end++;
      pointsData.add(new Point(String.valueOf(end),yCoords));
      return;
    }
    if(Integer.valueOf(xCoord)<begin){
      begin--;
      pointsData.add(0,new Point(String.valueOf(begin),yCoords));
      return;
    }
    while(iterator.hasNext()){
      Point point=iterator.next();
      if(Integer.valueOf(point.getX())<Integer.valueOf(xCoord)){
        prevIndex=iterator.previousIndex();
      }
    }
    while(iterator.hasPrevious()){
      Point point=iterator.previous();
      if(point.getX().equals(xCoord)){
        index=iterator.nextIndex();
        break;
      }
    }
    if(index!=0) {
      pointsData.add(index + 1, new Point(xCoord, yCoords));
    }
    else{
      pointsData.add(prevIndex+1, new Point(xCoord, yCoords));
    }
  }

  public void deletePoint(ActionEvent actionEvent) {
    String xCoord=xAddField.getCharacters().toString();
    String yCoords=yAddField.getCharacters().toString();
    ListIterator<Point> iterator=pointsData.listIterator();
    while(iterator.hasNext()){
      Point point=iterator.next();
      if(point.getX().equals(xCoord) && point.getY().equals(yCoords)){
        iterator.remove();
      }
    }
  }
}
