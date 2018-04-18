package util;

import bean.SignalKeeper;
import com.sun.deploy.util.ArrayUtil;

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
            double signal[][]=parseSignal(signalString);
            signalKeeper.setSignalY(copyArray(signal[1]));
            double[] realSignal=removeDublicats(signal[1], signal[0]);
            signalKeeper.setSignal(realSignal);
            signalKeeper.setOriginSignal(realSignal);
            signalKeeper.setSignalX(signal[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[][] parseSignal(String signalString){
        String[] signalArrayString=signalString.split(",");
        double signalY[]=new double[signalArrayString.length];
        double signalX[]=new double[signalArrayString.length];

        for(int i=0;i<signalArrayString.length;i++){
            String currentString=signalArrayString[i];
            currentString=currentString.substring(1,currentString.length()-1);
            String[] stringValues=currentString.split(";");
            signalY[i]=Double.valueOf(stringValues[1]);
            signalX[i]=Integer.valueOf(stringValues[0]);
        }

        double[][] totalSignal={signalX,signalY};

        return totalSignal;
    }

    private static double[] removeDublicats(double[] dublicateSignal, double[] signalX){
        int dublicateCounts=0;
        for(int i=0;i<signalX.length;i++){
            if(i==0){
                continue;
            }
            if(signalX[i]==signalX[i-1]){
                if(dublicateSignal[i-dublicateCounts]>dublicateSignal[i-dublicateCounts-1] && dublicateSignal[i-dublicateCounts-1]!=0){
                    dublicateSignal[i-dublicateCounts-1]=dublicateSignal[i-dublicateCounts];
                }
                shiftArray(dublicateSignal, i-dublicateCounts);
                dublicateCounts++;
            }
        }
        double[] realSignal=new double[dublicateSignal.length-dublicateCounts];
        for(int i=0;i<realSignal.length;i++){
            realSignal[i]=dublicateSignal[i];
        }
        return realSignal;
    }

    private static void shiftArray(double[] dublicateSignal, int index){
        for(int i=index;i<dublicateSignal.length;i++){
            if(i==dublicateSignal.length-1){
                continue;
            }
            dublicateSignal[i]=dublicateSignal[i+1];
        }
    }

    private static double[] copyArray(double[] signal){
        double[] newSignal=new double[signal.length];
        for(int i=0;i<signal.length;i++){
            newSignal[i]=signal[i];
        }
        return newSignal;
    }
}
