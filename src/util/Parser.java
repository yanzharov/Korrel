package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Parser {

    public static void parseFile(File file){
        String path=file.getAbsolutePath();
        Properties properties=new Properties();

        try(FileInputStream fileInputStream=new FileInputStream(path)){
            properties.load(fileInputStream);
            SignalKeeper.setType(properties.getProperty("TYPE"));
            SignalKeeper.setAmplitude(Integer.valueOf(properties.getProperty("AMPLITUDE")));
            SignalKeeper.setBegin(Integer.valueOf(properties.getProperty("BEGIN")));
            SignalKeeper.setEnd(Integer.valueOf(properties.getProperty("END")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
