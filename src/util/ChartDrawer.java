package util;

import bean.SignalKeeper;
import bean.StrategyKeeper;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartDrawer {

  public static void drawSignal(LineChart lineChart, SignalKeeper signalKeeper){
    if(signalKeeper.getType()==null){
      return;
    }
    if(signalKeeper.getType().equals("Impuls")){
      drawImpuls(lineChart,signalKeeper);
    }
    if(signalKeeper.getType().equals("TreugImpuls")){
      drawTreugImpuls(lineChart,signalKeeper);
    }
  }

  public static void drawKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    if(signalKeeper1.getType()==null || signalKeeper2.getType()==null){
      return;
    }
    if(signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("Impuls")){
      drawKorrelImpulsImpuls(lineChart, signalKeeper1, signalKeeper2, step, strategyKeeper);
    }
    if(signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("TreugImpuls")){
      drawKorrelTreugImpulsTreugImpuls(lineChart, signalKeeper1, signalKeeper2, step, strategyKeeper);
    }
    if((signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("Impuls"))){
      drawKorrelTreugImpulsImpuls(lineChart, signalKeeper1, signalKeeper2, step, strategyKeeper);
    }
    if((signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("TreugImpuls"))){
      drawKorrelImpulsTreugImpuls(lineChart, signalKeeper1, signalKeeper2, step, strategyKeeper);
    }
  }

  public static void incrementKorrelSignal(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, boolean increment, boolean isAuto){
    if(signalKeeper1.getType()==null || signalKeeper2.getType()==null){
      return;
    }
    if(signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("Impuls")){
      incrementKorrelImpulsImpuls(lineChart, signalKeeper1, signalKeeper2, step, increment, isAuto);
    }
    if(signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("TreugImpuls")){
      incrementKorrelTreugImpulsTreugImpuls(lineChart, signalKeeper1, signalKeeper2, step, increment, isAuto);
    }
    if((signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("Impuls"))){
      incrementKorrelTreugImpulsImpuls(lineChart, signalKeeper1, signalKeeper2, step, increment, isAuto);
    }
    if((signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("TreugImpuls"))){
      incrementKorrelImpulsTreugImpuls(lineChart, signalKeeper1, signalKeeper2, step, increment, isAuto);
    }
  }

  private static void drawImpuls(LineChart lineChart, SignalKeeper signalKeeper){
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();

    series.getData().add(new XYChart.Data(signalKeeper.getBegin(),0));
    series.getData().add(new XYChart.Data(signalKeeper.getBegin(),signalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(signalKeeper.getEnd(),signalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(signalKeeper.getEnd(),0));

    lineChart.getData().add(series);
  }

  private static void drawTreugImpuls(LineChart lineChart, SignalKeeper signalKeeper){
    lineChart.getData().clear();
    XYChart.Series series = new XYChart.Series();

    series.getData().add(new XYChart.Data(signalKeeper.getBegin(),0));
    series.getData().add(new XYChart.Data(signalKeeper.getEnd(),signalKeeper.getAmplitude()));
    series.getData().add(new XYChart.Data(signalKeeper.getEnd(),0));

    lineChart.getData().add(series);
  }

  private static void drawKorrelImpulsImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    lineChart.getData().clear();

    int begin=-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());

    if(strategyKeeper.isStepStrategy()){
      end = 0;
    }

    XYChart.Series series = new XYChart.Series();

    for(int i=begin;i<end+1;i+=step){
      int amplitude=signalKeeper1.getAmplitude()*signalKeeper2.getAmplitude()*(signalKeeper2.getEnd()-signalKeeper2.getBegin()-Math.abs(i));
      series.getData().add(new XYChart.Data(i,amplitude));
    }

    lineChart.getData().add(series);
  }

  private static void drawKorrelTreugImpulsTreugImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    lineChart.getData().clear();

    int begin=-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(strategyKeeper.isStepStrategy()){
      end = 0;
    }

    XYChart.Series series = new XYChart.Series();
    double amplitude=0;
    for(int i=begin;i<end+1;i+=step){
      if(i<0) {
        amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()/(6*Math.pow(impulsDuration,2))*(2*Math.pow(impulsDuration,3)+i*Math.pow(impulsDuration,2)+Math.pow(i,3));
      }
      else{
        amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()/(6*Math.pow(impulsDuration,2))*(2*Math.pow(impulsDuration,3)-i*Math.pow(impulsDuration,2)-Math.pow(i,3));
      }
      series.getData().add(new XYChart.Data(i,amplitude));
    }

    lineChart.getData().add(series);
  }

  private static void drawKorrelTreugImpulsImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    lineChart.getData().clear();

    int begin=-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(strategyKeeper.isStepStrategy()){
      end = 0;
    }

    XYChart.Series series = new XYChart.Series();
    double amplitude=0;
    for(int i=begin;i<end+1;i+=step){
      if(i<0) {
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) + 2*i*impulsDuration + Math.pow(i, 2)) / (2 * impulsDuration));
      }
      else{
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - Math.pow(i, 2)) / (2 * impulsDuration));
      }
      series.getData().add(new XYChart.Data(i,amplitude));
    }

    lineChart.getData().add(series);
  }

  private static void drawKorrelImpulsTreugImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, StrategyKeeper strategyKeeper){
    lineChart.getData().clear();

    int begin=-Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
    int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(strategyKeeper.isStepStrategy()){
      end = 0;
    }

    XYChart.Series series = new XYChart.Series();
    double amplitude=0;
    for(int i=begin;i<end+1;i+=step){
      if(i>0) {
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - 2*i*impulsDuration + Math.pow(i, 2)) / (2 * impulsDuration));
      }
      else{
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - Math.pow(i, 2)) / (2 * impulsDuration));
      }
      series.getData().add(new XYChart.Data(i,amplitude));
    }

    lineChart.getData().add(series);
  }

  private static void incrementKorrelImpulsImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, boolean increment, boolean isAuto){

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+step) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
      int lastValue = (Integer) data.getXValue();
      int amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude() * (signalKeeper2.getEnd() - signalKeeper2.getBegin() - Math.abs(lastValue + step));
      series.getData().add(new XYChart.Data(lastValue + step, amplitude));
    }
    else if(increment==false && !(signalKeeper1End-step<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        series.getData().remove(series.getData().size() - 1);
      }
    }
  }

  private static void incrementKorrelTreugImpulsTreugImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, boolean increment, boolean isAuto){

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+step) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
      int lastValue = (Integer) data.getXValue();
      double amplitude=0;

      if(lastValue+step<0){
        amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()/(6*Math.pow(impulsDuration,2))*(2*Math.pow(impulsDuration,3)+(lastValue+step)*Math.pow(impulsDuration,2)+Math.pow((lastValue+step),3));
      }
      else{
        amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()/(6*Math.pow(impulsDuration,2))*(2*Math.pow(impulsDuration,3)-(lastValue+step)*Math.pow(impulsDuration,2)-Math.pow((lastValue+step),3));
      }

      series.getData().add(new XYChart.Data(lastValue + step, amplitude));
    }
    else if(increment==false && !(signalKeeper1End-step<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        series.getData().remove(series.getData().size() - 1);
      }
    }
  }

  private static void incrementKorrelTreugImpulsImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, boolean increment, boolean isAuto){

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+step) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
      int lastValue = (Integer) data.getXValue();
      double amplitude=0;

      if(lastValue+step<0) {
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) + 2*(lastValue+step)*impulsDuration + Math.pow(lastValue+step, 2)) / (2 * impulsDuration));
      }
      else{
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - Math.pow(lastValue+step, 2)) / (2 * impulsDuration));
      }

      series.getData().add(new XYChart.Data(lastValue + step, amplitude));
    }
    else if(increment==false && !(signalKeeper1End-step<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        series.getData().remove(series.getData().size() - 1);
      }
    }
  }

  private static void incrementKorrelImpulsTreugImpuls(LineChart lineChart, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2, int step, boolean increment, boolean isAuto){

    int signalKeeper1Begin=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultBegin():signalKeeper1.getBegin();
    int signalKeeper1End=(isAuto)?SceneSelector.getAutoStrategyKeeper().getDefaultEnd():signalKeeper1.getEnd();
    int impulsDuration=signalKeeper2.getEnd() - signalKeeper2.getBegin();

    if(increment==true && !(signalKeeper2.getEnd()<signalKeeper1Begin+step) && !(signalKeeper1End<signalKeeper2.getBegin())) {
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      XYChart.Data data = (XYChart.Data) series.getData().get(series.getData().size() - 1);
      int lastValue = (Integer) data.getXValue();
      double amplitude=0;

      if(lastValue+step>0) {
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - 2*(lastValue+step)*impulsDuration + Math.pow(lastValue+step, 2)) / (2 * impulsDuration));
      }
      else{
        amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow(impulsDuration, 2) - Math.pow(lastValue+step, 2)) / (2 * impulsDuration));
      }

      series.getData().add(new XYChart.Data(lastValue + step, amplitude));
    }
    else if(increment==false && !(signalKeeper1End-step<signalKeeper2.getBegin())){
      XYChart.Series series = (XYChart.Series) lineChart.getData().get(0);
      if(series.getData().size()!=1) {
        series.getData().remove(series.getData().size() - 1);
      }
    }
  }
}
