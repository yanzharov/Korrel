package util;

import bean.SignalKeeper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {
  public static void drawImpuls(LineChart lineChart, SignalKeeper signalKeeper){
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();

    series.getData().add(new XYChart.Data(""+ signalKeeper.getBegin()+"",0));
    series.getData().add(new XYChart.Data(""+signalKeeper.getBegin()+"",signalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(""+signalKeeper.getEnd()+"",signalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(""+signalKeeper.getEnd()+"",0));

    lineChart.getData().add(series);
  }
}
