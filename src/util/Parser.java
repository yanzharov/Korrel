package util;

import bean.SignalKeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parser {

    public static void parseFile(File file, SignalKeeper signalKeeper){
        String path=file.getAbsolutePath();
        Properties properties=new Properties();

        try(FileInputStream fileInputStream=new FileInputStream(path)){
            properties.load(fileInputStream);
            signalKeeper.setType(properties.getProperty("TYPE"));
            signalKeeper.setAmplitude(Integer.valueOf(properties.getProperty("AMPLITUDE")));
            signalKeeper.setBegin(Integer.valueOf(properties.getProperty("BEGIN")));
            signalKeeper.setEnd(Integer.valueOf(properties.getProperty("END")));
            signalKeeper.setDuration(signalKeeper.getEnd()-signalKeeper.getBegin());
            double signal[]=new double[((signalKeeper.getEnd()-signalKeeper.getBegin())/5)+1];
            for(int i=0;i<signal.length;i++){
                if(signalKeeper.getType().equals("TreugImpuls")) {
                    signal[i] = 5 * (1 + ((double) (10 * (i - 3)) / 30));
                }
                if(signalKeeper.getType().equals("Impuls")){
                    signal[i] = 10;
                }
            }
            signalKeeper.setSignal(signal);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
