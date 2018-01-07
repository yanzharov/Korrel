package util;

public class CrossSignalKeeper {
    private static String type;
    private static int amplitude;
    private static int begin;
    private static int end;

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        CrossSignalKeeper.type = type;
    }

    public static int getAmplitude() {
        return amplitude;
    }

    public static void setAmplitude(int amplitude) {
        CrossSignalKeeper.amplitude = amplitude;
    }

    public static int getBegin() {
        return begin;
    }

    public static void setBegin(int begin) {
        CrossSignalKeeper.begin = begin;
    }

    public static int getEnd() {
        return end;
    }

    public static void setEnd(int end) {
        CrossSignalKeeper.end = end;
    }
}
