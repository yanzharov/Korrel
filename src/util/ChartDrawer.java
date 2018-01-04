package util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {
  public static void drawChart(LineChart lineChart, double amplitude, int period, int step){
    XYChart.Series series = new XYChart.Series();
    series.getData().add(new XYChart.Data(1, 23));
    series.getData().add(new XYChart.Data(2, 14));
    series.getData().add(new XYChart.Data(3, 15));
    series.getData().add(new XYChart.Data(4, 24));
    series.getData().add(new XYChart.Data(5, 34));
    series.getData().add(new XYChart.Data(6, 36));
    series.getData().add(new XYChart.Data(7, 22));
    series.getData().add(new XYChart.Data(8, 45));
    series.getData().add(new XYChart.Data(9, 43));
    series.getData().add(new XYChart.Data(10, 17));
    series.getData().add(new XYChart.Data(11, 29));
    series.getData().add(new XYChart.Data(12, 25));

    lineChart.getData().add(series);
  }
}
