package controller;

import bean.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateController implements Initializable{
  private ObservableList<Point> pointsData = FXCollections.observableArrayList();
  private double[] signal;
  private int begin;
  private int end;
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
  public LineChart constructedSignalChart;

  public void createTable(ActionEvent actionEvent) {
    pointsData.clear();
    begin=Integer.valueOf(beginTextField.getCharacters().toString());
    end=Integer.valueOf(endTextField.getCharacters().toString());

    for(int i=begin;i<end+1;i++){
      pointsData.add(new Point(""+i+"",""+10+""));
    }

    signal=new double[pointsData.size()];
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
  }

  public void saveFile(ActionEvent actionEvent) {
    try {
      FileOpener.saveFile(pointsData, begin, end, 1);
    }
    catch (IOException exception){

    }
  }

  public void constructSignal(ActionEvent actionEvent) {
    int currentDiscret=0;
    for(Point point:pointsData){
      signal[currentDiscret]=Double.valueOf(point.getY());
      currentDiscret++;
    }
    ChartDrawer.drawSignal(constructedSignalChart,begin,end,signal,1);
  }

  public void moveToMain(ActionEvent actionEvent) {
    SceneSelector.chooseScene("MAIN_SCENE");
  }
}
