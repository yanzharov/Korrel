package util;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import bean.Point;

import java.util.ArrayList;
import java.util.List;

public class SignalBuilder {
  public static List<Point> buildSignal(String signalType, double amplitude, int begin, int end){
    List<Point> points=new ArrayList<>();
    double value;
    int pointZero=end-(end-begin)/2;
    int impulsCounter=-2;

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
        value=amplitude*cos(2*Math.PI*i*3/(end-begin));
        points.add(new Point(""+i+"",""+value+""));
      }
    }

    if(signalType.equals("Синусоидальный сигнал")){
      for(int i=begin;i<end+1;i++){
        value=amplitude*sin(2*Math.PI*i*3/(end-begin));
        points.add(new Point(""+i+"",""+value+""));
      }
    }

    if(signalType.equals("Последовательность прямоугольных видеоимпульсов")){
      for(int i=begin;i<end+1;i++){
        if(impulsCounter==0){
          value=0;
          points.add(new Point(""+i+"",""+value+""));
        }
        if(impulsCounter<0){
          value=0;
        }
        else{
          value=amplitude;
        }
        impulsCounter++;
        points.add(new Point(""+i+"",""+value+""));

        if(impulsCounter==3){
          value=0;
          points.add(new Point(""+i+"",""+value+""));
          impulsCounter=-2;
        }
      }
    }

    return points;
  }
}
