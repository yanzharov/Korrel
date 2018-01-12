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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
