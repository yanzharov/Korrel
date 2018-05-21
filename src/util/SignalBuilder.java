package util;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import bean.Point;

import java.util.ArrayList;
import java.util.List;

public class SignalBuilder {
  public static List<Point> buildSignal(String signalType, double amplitude, int begin, int end, int freq){
    List<Point> points=new ArrayList<>();
    double value=0;
    int pointZero=end-(end-begin)/2;
    int impulsPeriod=(Math.abs(end-begin)/(freq*2));

    if(signalType.equals("Прямоугольный видеоимпульс")){
      points.add(new Point(""+begin+"",""+0+""));
      for(int i=begin;i<end+1;i++){
        points.add(new Point(""+i+"",""+amplitude+""));
      }
      points.add(new Point(""+end+"",""+0+""));
    }

    if(signalType.equals("Треугольный видеоимпульс")){
      for(int i=begin;i<end+1;i++){
        if(i<pointZero){
          value=(1+(2*(double)(i-pointZero)/(end-begin)))*amplitude;
        }
        else{
          value=(1-(2*(double)(i-pointZero)/(end-begin)))*amplitude;
        }
        points.add(new Point(""+i+"",""+value+""));
      }
    }

    if(signalType.equals("Прямой треугольный видеоимпульс")){
      for(int i=begin;i<end+1;i++){
        value=(1+(2*(double)i/(end-begin)))*amplitude/2;
        points.add(new Point(""+i+"",""+value+""));
      }
      points.add(new Point(""+end+"",""+0+""));
    }

    if(signalType.equals("Косинусоидальный сигнал")){
      for(int i=begin;i<end+1;i++){
        value=amplitude*cos(2*Math.PI*i*freq/(end-begin));
        points.add(new Point(""+i+"",""+value+""));
      }
    }

    if(signalType.equals("Синусоидальный сигнал")){
      for(int i=begin;i<end+1;i++){
        value=amplitude*sin(2*Math.PI*i*freq/(end-begin));
        points.add(new Point(""+i+"",""+value+""));
      }
    }

    if(signalType.equals("Последовательность прямоугольных видеоимпульсов при s(t)>0")){
      int impulsCounter=0;
      for(int i=0;i<(impulsPeriod/2);i++){
        points.add(new Point("" + (i+begin) + "", "" + value + ""));
      }
      impulsCounter=impulsPeriod-1;
      for(int i=begin+impulsPeriod/2;i<end+1;i++){
        points.add(new Point(""+i+"",""+value+""));
        impulsCounter++;
        if(impulsCounter==impulsPeriod){
          if(value>amplitude-1){
            value=0;
          }
          else{
            value=amplitude;
          }
          points.add(new Point(""+i+"",""+value+""));
          impulsCounter=0;
        }
      }
    }

    if(signalType.equals("Последовательность прямоугольных видеоимпульсов при s(t)>0 и s(t)<0")){
      int impulsCounter=0;
      value=-amplitude;
      for(int i=0;i<(impulsPeriod/2);i++){
        points.add(new Point("" + (i+begin) + "", "" + value + ""));
      }
      impulsCounter=impulsPeriod-1;
      for(int i=begin+impulsPeriod/2;i<end+1;i++){
        points.add(new Point(""+i+"",""+value+""));
        impulsCounter++;
        if(impulsCounter==impulsPeriod){
          if(value>amplitude-1){
            value=-amplitude;
          }
          else{
            value=amplitude;
          }
          points.add(new Point(""+i+"",""+value+""));
          impulsCounter=0;
        }
      }
    }

    return points;
  }
}
