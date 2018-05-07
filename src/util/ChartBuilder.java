package util;

import bean.SignalKeeper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class ChartBuilder {
  public static void buildChart(SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, boolean isAuto){
    if(isAuto){
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart1AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart1AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart2AxisX.setLowerBound(signalKeeper1.getShiftBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart2AxisX.setUpperBound(signalKeeper1.getShiftEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart3AxisX.setLowerBound(-signalKeeper1.getDuration()*3/2-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart3AxisX.setUpperBound(signalKeeper1.getDuration()*3/2+5);
    }
    else{
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart1AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart1AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart2AxisX.setLowerBound(signalKeeper2.getShiftBegin()-signalKeeper2.getDuration()-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart2AxisX.setUpperBound(signalKeeper2.getShiftEnd()+signalKeeper2.getDuration()+5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart3AxisX.setLowerBound(-(signalKeeper2.getEnd()-signalKeeper1.getBegin())-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin())/2-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart3AxisX.setUpperBound(signalKeeper1.getEnd()-signalKeeper2.getBegin()+Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin())/2+5);
    }
  }

  public static void moveChart(NumberAxis axis, SignalKeeper signalKeeper){
    if(!(signalKeeper.getBegin()<axis.getLowerBound() || signalKeeper.getEnd()>axis.getUpperBound())){
      return;
    }
    axis.setLowerBound(signalKeeper.getBegin()-signalKeeper.getDuration()-5);
    axis.setUpperBound(signalKeeper.getEnd()+signalKeeper.getDuration()+5);
    signalKeeper.setMoved(true);
  }

  public static void returnChart(NumberAxis axis, SignalKeeper signalKeeper){
    if(!signalKeeper.isMoved()){
      return;
    }
    axis.setLowerBound(signalKeeper.getBegin()-signalKeeper.getDuration()-5);
    axis.setUpperBound(signalKeeper.getEnd()+signalKeeper.getDuration()+5);
  }
}
