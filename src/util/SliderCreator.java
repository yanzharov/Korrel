package util;

import bean.SignalKeeper;
import javafx.scene.control.Slider;

public class SliderCreator {

    public static void createSlider(Slider slider, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2){
        int begin=Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
        int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());

        slider.setMax((begin+end)/2-((begin+end)/2)%5);
        slider.setMin(5*Math.pow(10,(begin+end)/1000));
        slider.setBlockIncrement(5*Math.pow(10,(begin+end)/1000));
        slider.setMajorTickUnit(5*Math.pow(10,(begin+end)/1000));
        slider.setValue(5*Math.pow(10,(begin+end)/1000));
    }
}
