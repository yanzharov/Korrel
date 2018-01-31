package util;

import bean.SignalKeeper;
import javafx.scene.control.Slider;

public class SliderCreator {

    public static void createSlider(Slider slider, SignalKeeper signalKeeper1, SignalKeeper signalKeeper2){
        int begin=Math.abs(signalKeeper2.getEnd()-signalKeeper1.getBegin());
        int end=Math.abs(signalKeeper1.getEnd()-signalKeeper2.getBegin());

        slider.setMax(10);
        slider.setMin(5);
        slider.setBlockIncrement(5);
        slider.setMajorTickUnit(5);
        slider.setValue(5);
    }
}
