package util;

import bean.SignalKeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void parseFile(File file, SignalKeeper signalKeeper){
        String path=file.getAbsolutePath();
        Properties properties=new Properties();

        try(FileInputStream fileInputStream=new FileInputStream(path)){
            properties.load(fileInputStream);
            signalKeeper.setBegin(Integer.valueOf(properties.getProperty("BEGIN")));
            signalKeeper.setEnd(Integer.valueOf(properties.getProperty("END")));
            signalKeeper.setStep(Integer.valueOf(properties.getProperty("STEP")));
            signalKeeper.setDuration(signalKeeper.getEnd()-signalKeeper.getBegin());
            String signalString=properties.getProperty("SIGNAL");
            double signal[]=parseSignal(signalString, signalKeeper.getBegin(), signalKeeper.getEnd(), signalKeeper.getStep());
            signalKeeper.setSignal(signal);
            signalKeeper.setOriginSignal(signal);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[] parseSignal(String signalString, int begin, int end, int step){
        double signal[]=new double[((end-begin)/step)+1];
        String[] signalArrayString=signalString.split(",");
        for(int i=0;i<signal.length;i++){
            signal[i]=Double.valueOf(signalArrayString[i]);
        }
        return signal;
    }
}
