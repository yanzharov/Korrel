package util;

import bean.SignalKeeper;
import bean.StrategyKeeper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {

  public static void drawSignal(LineChart lineChart, SignalKeeper signalKeeper, int step){
    if(signalKeeper.getSignal()==null){
      return;
    }
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();
    double signal[]=signalKeeper.getSignal();
    int currentDiscret=0;
    for(int i=signalKeeper.getBegin();i<signalKeeper.getEnd()+1;i+=step) {
      series.getData().add(new XYChart.Data(i, signal[currentDiscret++]));
    }
    lineChart.getData().add(series);
  }

  public static void drawSignal(LineChart lineChart, int begin, int end, double[] signal, int step){
    if(signal==null){
      return;
    }
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();
    int currentDiscret=0;
    for(int i=begin;i<end+1;i+=step) {
      series.getData().add(new XYChart.Data(i, signal[currentDiscret++]));
    }
    lineChart.getData().add(series);
  }

  public static void drawKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    if(signalKeeper1.getSignal()==null || signalKeeper2.getSignal()==null){
      return;
    }

    lineChart.getData().clear();

    int leftShift=(signalKeeper2.getEnd()-signalKeeper1.getBegin())/step;
    int begin=-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());

    double signal1[]=signalKeeper1.getSignal();
    double signal2[]=new double[signal1.length+(signalKeeper2.getSignal().length-1)*2];
    for(int i=0;i<signalKeeper2.getSignal().length;i++){
      signal2[i]=signalKeeper2.getSignal()[i];
    }

    if(strategyKeeper.isStepStrategy()){
      begin=end=strategyKeeper.getStepStrategyBegin();
    }

    XYChart.Series series = new XYChart.Series();
    double amplitude=0;
    int shift=-leftShift;

    for(int i=begin;i<end+1;i+=step){
      if(shift==-leftShift){
        amplitude=0;
      }
      else {
        amplitude = sumSignal(signal1, signal2, shift, signalKeeper2.getSignal().length, (signalKeeper2.getBegin()-signalKeeper1.getBegin())/step);
      }
      shift++;
      series.getData().add(new XYChart.Data(i,amplitude*step));
    }

    lineChart.getData().add(series);
  }

  public static void incrementKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, StrategyKeeper strategyKeeper, int step, boolean increment, boolean isAuto){
    if(signalKeeper1.getSignal()==null || signalKeeper2.getSignal()==null){
      return;
    }

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+step) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
      int lastValue = (Integer) data.getXValue();
      int beginDifference=(isAuto)?0:(strategyKeeper.getDefaultBegin()-signalKeeper1.getBegin())/step;
      double amplitude = sumSignal(signalKeeper1.getSignal(), strategyKeeper.getSignal(), (lastValue+step)/step, signalKeeper2.getSignal().length, beginDifference);
      series.getData().add(new XYChart.Data(lastValue + step, amplitude*step));
    }
    else if(increment==false && !(signalKeeper1End-step<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        moveSignalBack(strategyKeeper.getSignal());
        series.getData().remove(series.getData().size() - 1);
      }
    }

  }

  private static double sumSignal(double[] signal1, double[] signal2, int shift, int originLength, int beginDifference){
    for(int i=signal2.length-2;i>-1;i--){
      signal2[i+1]=signal2[i];
    }
    signal2[0]=0;
    double result=0;

    shift += beginDifference;
    if (shift < 0) {
      shift = 0;
    }
    if(shift>signal1.length-1){
      shift=signal1.length-1;
    }
    for (int i = 1+shift; i < signal1.length; i++) {
      result += signal1[i] * signal2[i + originLength-1];
    }

    return result;
  }

  private static void moveSignalBack(double[] signal){
    for(int i=0;i<signal.length-1;i++){
      signal[i]=signal[i+1];
    }
    signal[signal.length-1]=0;
  }
}
