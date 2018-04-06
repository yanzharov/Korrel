package util;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;

import java.util.List;

public class TooltipSetter {
  public static void addTooltips(LineChart lineChart){
    XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
    List<XYChart.Data> dataList=series.getData();
    for(XYChart.Data data:dataList){
      Node node = data.getNode();
      Tooltip tooltip = new Tooltip('('+data.getXValue().toString()+';'+data.getYValue().toString()+')');
      Tooltip.install(node, tooltip);
    }
  }
  public static void addTooltip(LineChart lineChart, int step){
    XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
    List<XYChart.Data> dataList=series.getData().subList(series.getData().size()-step,series.getData().size());
    for(XYChart.Data data:dataList) {
      Node node = data.getNode();
      Tooltip tooltip = new Tooltip('(' + data.getXValue().toString() + ';' + data.getYValue().toString() + ')');
      Tooltip.install(node, tooltip);
    }
  }
}
