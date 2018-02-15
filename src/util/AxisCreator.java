package util;

import bean.SignalKeeper;
import javafx.scene.chart.NumberAxis;

public class AxisCreator {
  public static void createAxis(NumberAxis numberAxis, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2){
    double amplitude=0;
    if(signalKeeper1.getType()==null || signalKeeper2.getType()==null){
      return;
    }
    if(signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("Impuls")){
      amplitude=signalKeeper1.getAmplitude()*signalKeeper2.getAmplitude()*(signalKeeper2.getEnd()-signalKeeper2.getBegin());
    }
    if(signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("TreugImpuls")){
      amplitude = signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()/(6*Math.pow((signalKeeper2.getEnd()-signalKeeper2.getBegin()),2))*(2*Math.pow((signalKeeper2.getEnd()-signalKeeper2.getBegin()),3));
    }
    if((signalKeeper1.getType().equals("TreugImpuls")&&signalKeeper2.getType().equals("Impuls"))){
      amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow((signalKeeper2.getEnd()-signalKeeper2.getBegin()), 2)) / (2 * (signalKeeper2.getEnd()-signalKeeper2.getBegin())));
    }
    if((signalKeeper1.getType().equals("Impuls")&&signalKeeper2.getType().equals("TreugImpuls"))){
      amplitude = ((signalKeeper1.getAmplitude() * signalKeeper2.getAmplitude()) * (Math.pow((signalKeeper2.getEnd()-signalKeeper2.getBegin()), 2)) / (2 * (signalKeeper2.getEnd()-signalKeeper2.getBegin())));
    }
    if(signalKeeper1.getType().equals("PeriodSignal")&&signalKeeper2.getType().equals("PeriodSignal")){
      amplitude=signalKeeper1.getAmplitude()*signalKeeper2.getAmplitude()*(signalKeeper2.getEnd()-signalKeeper2.getBegin());
    }
    if(signalKeeper1.getType().equals("ImpulsQueue")&&signalKeeper2.getType().equals("ImpulsQueue")){
      amplitude=1000;
    }
    numberAxis.setUpperBound(amplitude+2*amplitude/5);
    numberAxis.setLowerBound(-(amplitude+2*amplitude/5));
    numberAxis.setTickUnit(amplitude/5);
  }
}
