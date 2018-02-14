package util;

import javafx.scene.control.ComboBox;

public class ComboBoxCreator {
  private static int[] options={1,5,10};
  public static void createComboBox(ComboBox comboBox, int step){
    comboBox.getItems().clear();
    comboBox.getItems().add(step);
    int visibleRowCount=1;
    for(int i=0;i<options.length;i++){
      if(options[i]>step){
        comboBox.getItems().add(options[i]);
        visibleRowCount++;
      }
    }
    comboBox.setVisibleRowCount(visibleRowCount);
    comboBox.getSelectionModel().select(0);
  }
  public static void createComboBox(ComboBox comboBox, int step1, int step2, int difference){
    comboBox.getItems().clear();
    int length=(difference%10==0)?options.length:options.length-1;
    int step=step1>step2?step1:step2;
    comboBox.getItems().add(step);
    int visibleRowCount=1;
    for(int i=0;i<length;i++){
      if(options[i]>step){
        comboBox.getItems().add(options[i]);
        visibleRowCount++;
      }
    }
    comboBox.setVisibleRowCount(visibleRowCount);
    comboBox.getSelectionModel().select(0);
  }
}
