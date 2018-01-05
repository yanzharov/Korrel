package util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {
  public static void drawChart(LineChart lineChart, double amplitude, int period, int step){
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();

    series.getData().add(new XYChart.Data(""+-period/2+"",0));

    for(int i=-period/2;i<period/2;i+=step){
      series.getData().add(new XYChart.Data(""+i+"",amplitude*Math.cos(i)));
    }

    series.getData().add(new XYChart.Data(""+period/2+"",amplitude));
    series.getData().add(new XYChart.Data(""+period/2+"",0));

    lineChart.getData().add(series);
  }
}
