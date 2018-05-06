package util;

import bean.SignalKeeper;
import bean.StrategyKeeper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartDrawer {

  public static void drawSignal(LineChart lineChart, SignalKeeper signalKeeper){
    if(signalKeeper.getSignal()==null){
      return;
    }
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();
    int currentDiscret=0;
    int begin=signalKeeper.getBegin();
    int end=signalKeeper.getEnd();
    double[] signalX=signalKeeper.getSignalX();
    double[] signalY=signalKeeper.getSignalY();

    for(int i=begin;i<end+1;i+=signalKeeper.getStep()) {
      if(!(signalX.length-1==currentDiscret)&&currentDiscret!=0&&signalX[currentDiscret]==signalX[currentDiscret-1]){
        i--;
      }
      series.getData().add(new XYChart.Data(i, signalY[currentDiscret]));
      currentDiscret++;
      if(i==end&&!(signalX.length-1<currentDiscret)&&signalX[currentDiscret]==signalX[currentDiscret-1]){
        i--;
      }
    }
    lineChart.getData().add(series);
  }

  public static void drawSignal(LineChart lineChart, int begin, int end, double[] signalY, double[] signalX, int step){
    if(signalY==null){
      return;
    }
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();
    int currentDiscret=0;
    for(int i=begin;i<end+1;i+=step) {
      if(!(signalX.length-1==currentDiscret)&&currentDiscret!=0&&signalX[currentDiscret]==signalX[currentDiscret-1]){
        i--;
      }
      series.getData().add(new XYChart.Data(i, signalY[currentDiscret]));
      currentDiscret++;
      if(i==end&&!(signalX.length-1<currentDiscret)&&signalX[currentDiscret]==signalX[currentDiscret-1]){
        i--;
      }
    }
    lineChart.getData().add(series);
  }

  public static void drawKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    if(signalKeeper1.getSignal()==null || signalKeeper2.getSignal()==null){
      return;
    }

    lineChart.getData().clear();

    int leftShift=(signalKeeper2.getEnd()-signalKeeper1.getBegin())/signalKeeper2.getStep();
    int begin=-(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=signalKeeper1.getEnd()-signalKeeper2.getBegin();

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

    for(int i=begin;i<end+1;i+=signalKeeper2.getStep()){
      if(shift==-leftShift){
        amplitude=0;
      }
      else {
        amplitude = sumSignal(signal1, signal2, shift, signalKeeper2.getSignal().length, (signalKeeper2.getBegin()-signalKeeper1.getBegin())/signalKeeper2.getStep());
      }
      shift++;
      series.getData().add(new XYChart.Data(i,amplitude*signalKeeper2.getStep()));
    }

    lineChart.getData().add(series);
  }

  public static void incrementKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, StrategyKeeper strategyKeeper, int step, boolean increment, boolean isAuto){
    if(signalKeeper1.getSignal()==null || signalKeeper2.getSignal()==null){
      return;
    }

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+signalKeeper1.getStep()) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      for(int i=0;i<step;i++) {
        XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
        int lastValue = (Integer) data.getXValue();
        int beginDifference = (isAuto) ? 0 : (strategyKeeper.getDefaultBegin() - signalKeeper1.getBegin()) / signalKeeper1.getStep();
        double amplitude = sumSignal(signalKeeper1.getSignal(), strategyKeeper.getSignal(), (lastValue + signalKeeper1.getStep()) / signalKeeper1.getStep(), signalKeeper2.getSignal().length, beginDifference);
        series.getData().add(new XYChart.Data(lastValue + signalKeeper1.getStep(), amplitude * signalKeeper1.getStep()));
      }
    }
    else if(increment==false && !(signalKeeper1End-signalKeeper1.getStep()<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        for(int i=0;i<step;i++) {
          moveSignalBack(strategyKeeper.getSignal());
        }
        series.getData().remove(series.getData().size()-step,series.getData().size());
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
