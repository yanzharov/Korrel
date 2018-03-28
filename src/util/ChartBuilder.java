package util;

import bean.SignalKeeper;

public class ChartBuilder {
  public static void buildChart(SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, boolean isAuto){
    if(isAuto){
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart1AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart1AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart2AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart2AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart3AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("AUTO_KORREL_SCENE")).autoChart3AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
    }
    else{
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart1AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper1.getDuration()-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart1AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper1.getDuration()+5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart2AxisX.setLowerBound(signalKeeper2.getBegin()-signalKeeper2.getDuration()-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart2AxisX.setUpperBound(signalKeeper2.getEnd()+signalKeeper2.getDuration()+5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart3AxisX.setLowerBound(signalKeeper1.getBegin()-signalKeeper2.getDuration()-5);
      SceneSelector.getController(("CROSS_KORREL_SCENE")).crossChart3AxisX.setUpperBound(signalKeeper1.getEnd()+signalKeeper2.getDuration()+5);
    }
  }
}
