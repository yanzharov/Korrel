package util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {
  public static void drawImpuls(LineChart lineChart){
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();

    series.getData().add(new XYChart.Data(""+SignalKeeper.getBegin()+"",0));
    series.getData().add(new XYChart.Data(""+SignalKeeper.getBegin()+"",SignalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(""+SignalKeeper.getEnd()+"",SignalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(""+SignalKeeper.getEnd()+"",0));

    lineChart.getData().add(series);
  }
}
